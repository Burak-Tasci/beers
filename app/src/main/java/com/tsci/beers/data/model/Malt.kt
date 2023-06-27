package com.tsci.beers.data.model

import com.google.gson.annotations.SerializedName


data class Malt(

    @SerializedName("name")
    var name: String? = null,
    @SerializedName("amount")
    var amount: Amount? = Amount()

)