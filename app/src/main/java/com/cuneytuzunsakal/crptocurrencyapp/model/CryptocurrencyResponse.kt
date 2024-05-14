package com.cuneytuzunsakal.crptocurrencyapp.model

import com.google.gson.annotations.SerializedName

data class CryptocurrencyResponse(
    @SerializedName("data")
    val data: List<CryptocurrencyModel>
)
