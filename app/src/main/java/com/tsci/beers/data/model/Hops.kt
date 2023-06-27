package com.tsci.beers.data.model

import com.google.gson.annotations.SerializedName


data class Hops(

    @SerializedName("name")
    var name: String? = null,
    @SerializedName("amount")
    var amount: Amount? = Amount(),
    @SerializedName("add")
    var add: String? = null,
    @SerializedName("attribute")
    var attribute: String? = null

)