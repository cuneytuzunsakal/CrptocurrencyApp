package com.cuneytuzunsakal.crptocurrencyapp.model

import com.google.gson.annotations.SerializedName

data class CryptocurrencyModel(
    @SerializedName("pairNormalized")
    val currency: String,
    @SerializedName("last")
    val price: Double,
    @SerializedName("daily")
    val daily: Double,
    @SerializedName("dailyPercent")
    val dailyPercent: Double
)
