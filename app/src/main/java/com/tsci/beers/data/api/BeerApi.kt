package com.tsci.beers.data.api

import com.tsci.beers.data.model.BeerResponse
import retrofit2.http.GET

/**
 * Created by tasci on 25.06.2023.
 */
interface BeerApi {


    @GET("beers")
    suspend fun getAllBeers(): List<BeerResponse>
}