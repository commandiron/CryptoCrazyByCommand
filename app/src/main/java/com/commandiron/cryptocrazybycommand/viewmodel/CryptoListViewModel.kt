package com.commandiron.cryptocrazybycommand.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.commandiron.cryptocrazybycommand.model.CryptoListItem
import com.commandiron.cryptocrazybycommand.repo.CryptoRepository
import com.commandiron.cryptocrazybycommand.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptoListViewModel @Inject constructor(
    private val cryptoRepository: CryptoRepository
): ViewModel() {

    var cryptoList = mutableStateOf<List<CryptoListItem>>(listOf())
    var errorMessage = mutableStateOf("")
    var isLoading = mutableStateOf(false)

    private var initialCryptoList = listOf<CryptoListItem>()
    private var isSearchStarting = true

    init {
        getCryptoList()
    }

    fun searchCryptoList(query: String){
        val listToSearch = if (isSearchStarting){
            cryptoList.value
        }else{
            initialCryptoList
        }

        viewModelScope.launch(Dispatchers.Default) {
            if(query.isEmpty()){
                cryptoList.value = initialCryptoList
                isSearchStarting = true
                return@launch
            }

            val results = listToSearch.filter {
                it.currency.contains(query.trim(),ignoreCase = true)
            }
            if(isSearchStarting){
                initialCryptoList = cryptoList.value
                isSearchStarting = false
            }

            cryptoList.value = results
        }
    }

    fun getCryptoList() {
        viewModelScope.launch {
            isLoading.value = true
            val result = cryptoRepository.getCryptoList()
            when(result){
                is Resource.Success ->{
                    val cryptoItems = result.data!!.mapIndexed { index, cryptoListItem ->
                        CryptoListItem(cryptoListItem.currency,cryptoListItem.price)
                    } as List<CryptoListItem>

                    cryptoList.value += cryptoItems
                    errorMessage.value = ""
                    isLoading.value = false
                }
                is Resource.Error ->{
                    errorMessage.value = result.message!!
                    isLoading.value = false
                }
            }
        }
    }
}