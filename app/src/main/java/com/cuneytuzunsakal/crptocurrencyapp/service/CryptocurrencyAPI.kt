package com.cuneytuzunsakal.crptocurrencyapp.service

import com.cuneytuzunsakal.crptocurrencyapp.model.CryptocurrencyResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface CryptocurrencyAPI {
    @GET("ticker")
    fun getData(): Observable<CryptocurrencyResponse>
}
