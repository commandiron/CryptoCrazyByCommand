package com.commandiron.cryptocrazybycommand.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.commandiron.cryptocrazybycommand.model.CryptoDetails
import com.commandiron.cryptocrazybycommand.model.CryptoDetailsItem
import com.commandiron.cryptocrazybycommand.model.CryptoListItem
import com.commandiron.cryptocrazybycommand.repo.CryptoRepository
import com.commandiron.cryptocrazybycommand.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject


@HiltViewModel
class CryptoDetailsViewModel @Inject constructor(
    private val cryptoRepository: CryptoRepository
): ViewModel() {

    suspend fun getCryptoDetails(ids: String): Resource<CryptoDetails>{
        return cryptoRepository.getCryptoDetails(ids)
    }
}