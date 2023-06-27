package com.tsci.beers.data.repository

import com.tsci.beers.data.Resource
import com.tsci.beers.data.model.BeerResponse

/**
 * Created by tasci on 25.06.2023.
 */
interface IBeerRepository {

    suspend fun getAllBeers(): Resource<List<BeerResponse>>
}