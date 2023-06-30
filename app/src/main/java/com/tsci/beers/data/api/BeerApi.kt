package com.tsci.beers.data.api

import com.tsci.beers.data.model.BeerResponse
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by tasci on 25.06.2023.
 */
interface BeerApi {


    @GET(ENDPOINT_BEERS)
    suspend fun getAllBeers(): List<BeerResponse>

    @GET(ENDPOINT_BEER_DETAIL)
    suspend fun getBeerDetail(@Path(QUERY_ID) id:Int): List<BeerResponse>


    companion object{
        private const val QUERY_ID = "id"

        private const val ENDPOINT_BEERS = "beers"
        private const val ENDPOINT_BEER_DETAIL = "beers/{$QUERY_ID}"

    }
}