package com.tsci.beers.data.model

import com.google.gson.annotations.SerializedName


data class Fermentation(

    @SerializedName("temp")
    var temp: Temp? = Temp()

)