package com.commandiron.cryptocrazybycommand.repo

import com.commandiron.cryptocrazybycommand.model.CryptoDetails
import com.commandiron.cryptocrazybycommand.model.CryptoDetailsItem
import com.commandiron.cryptocrazybycommand.model.CryptoList
import com.commandiron.cryptocrazybycommand.service.CryptoAPI
import com.commandiron.cryptocrazybycommand.util.Constants
import com.commandiron.cryptocrazybycommand.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class CryptoRepository @Inject constructor(
    private val api: CryptoAPI
) {

    suspend fun getCryptoList() : Resource<CryptoList>{

        val response = try {
            api.getCryptoList(Constants.API_KEY)

        }catch (e: Exception){
            return Resource.Error("Error in getCryptoList")
        }
        return Resource.Success(response)
    }

    suspend fun getCryptoDetails(ids: String): Resource<CryptoDetails>{

        val response = try {
            api.getCryptoDetails(Constants.API_KEY,ids,Constants.CALL_ATTRIBUTES)
        }catch (e: Exception){
            return Resource.Error("Error in getCryptoDetails")
        }
        return Resource.Success(response)
    }

}
