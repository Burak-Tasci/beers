package com.tsci.beers.domain

import com.tsci.beers.data.model.BeerResponse
import com.tsci.beers.ui.model.BeerUiModel

/**
 * Created by tasci on 25.06.2023.
 */
object Mappers {

    private const val UNKNOWN_VALUE = "Unknown Value"
    private const val UNKNOWN_ID = 0

    val beerResponseModelToDomainModel = object : IMapper<BeerResponse, BeerUiModel> {
        override fun map(input: BeerResponse): BeerUiModel {

            return BeerUiModel(
                id = input.id ?: UNKNOWN_ID,
                name = input.name ?: UNKNOWN_VALUE,
                imageUrl = input.imageUrl ?: UNKNOWN_VALUE,
                tagLine = input.tagline ?: UNKNOWN_VALUE
            )
        }
    }
}