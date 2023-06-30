package com.tsci.beers.domain

import com.tsci.beers.data.model.BeerResponse
import com.tsci.beers.ui.model.BeerDetailUiModel
import com.tsci.beers.ui.model.BeerItemUiModel

/**
 * Created by tasci on 25.06.2023.
 */
object Mappers {

    private const val UNKNOWN_VALUE = "Unknown Value"
    private const val UNKNOWN_ID = 0

    val beerResponseModelToBeerItemUiModel = object : IMapper<BeerResponse, BeerItemUiModel> {
        override fun map(input: BeerResponse): BeerItemUiModel = BeerItemUiModel(
            id = input.id ?: UNKNOWN_ID,
            name = input.name ?: UNKNOWN_VALUE,
            imageUrl = input.imageUrl ?: UNKNOWN_VALUE,
            tagLine = input.tagline ?: UNKNOWN_VALUE
        )
    }

    val beerResponseModelToBeerDetailUiModel = object : IMapper<BeerResponse, BeerDetailUiModel> {
        override fun map(input: BeerResponse): BeerDetailUiModel = BeerDetailUiModel(
            name = input.name ?: UNKNOWN_VALUE,
            imageUrl = input.imageUrl ?: UNKNOWN_VALUE,
            date = input.firstBrewed ?: UNKNOWN_VALUE,
            foodPairing = input.foodPairing,
            brewerTips = input.brewersTips ?: UNKNOWN_VALUE,
            description = input.description ?: UNKNOWN_VALUE,
            yeast = input.ingredients?.yeast ?: UNKNOWN_VALUE
        )

    }
}