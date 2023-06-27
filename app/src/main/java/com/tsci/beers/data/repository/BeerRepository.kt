package com.tsci.beers.data.repository

import com.tsci.beers.data.NetworkManager
import com.tsci.beers.data.Resource
import com.tsci.beers.data.api.BeerApi
import com.tsci.beers.data.model.BeerResponse

/**
 * Created by tasci on 25.06.2023.
 */

class BeerRepository(
    private val networkManager: NetworkManager,
    private val beerApi: BeerApi
): IBeerRepository {

    override suspend fun getAllBeers(): Resource<List<BeerResponse>> {
        return networkManager.apiCall {
            beerApi.getAllBeers()
        }
    }
}


