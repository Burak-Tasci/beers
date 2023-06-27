package com.tsci.beers.data.model

import com.google.gson.annotations.SerializedName


data class Method(

    @SerializedName("mash_temp")
    var mashTemp: ArrayList<MashTemp> = arrayListOf(),
    @SerializedName("fermentation")
    var fermentation: Fermentation? = Fermentation(),
    @SerializedName("twist")
    var twist: String? = null

)