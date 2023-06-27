package com.tsci.beers.data.model

import com.google.gson.annotations.SerializedName


data class Temp(

    @SerializedName("value")
    var value: Double? = null,
    @SerializedName("unit")
    var unit: String? = null

)