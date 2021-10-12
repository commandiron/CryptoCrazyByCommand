package com.commandiron.cryptocrazybycommand.service

import com.commandiron.cryptocrazybycommand.model.CryptoDetails
import com.commandiron.cryptocrazybycommand.model.CryptoDetailsItem
import com.commandiron.cryptocrazybycommand.model.CryptoList
import com.commandiron.cryptocrazybycommand.util.Resource
import retrofit2.http.GET
import retrofit2.http.Query

interface CryptoAPI {

    //https://api.nomics.com/v1/prices?key=721ae1a8f97d2cd58c496867f791f1f4e4524e12

    @GET("prices")
    suspend fun getCryptoList(
        @Query("key") key: String
    ): CryptoList

    //https://api.nomics.com/v1/currencies?key=721ae1a8f97d2cd58c496867f791f1f4e4524e12&ids=BTC&attributes=id,name,logo_url

    @GET("currencies")
    suspend fun getCryptoDetails(
        @Query("key") key: String,
        @Query("ids") ids: String,
        @Query("attributes") attributes: String,
    ): CryptoDetails
}