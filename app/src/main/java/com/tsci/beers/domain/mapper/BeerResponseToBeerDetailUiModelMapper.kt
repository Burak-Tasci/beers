package com.tsci.beers.domain.mapper

import com.tsci.beers.core.BaseMapper
import com.tsci.beers.data.model.BeerResponse
import com.tsci.beers.ui.model.BeerDetailUiModel
import javax.inject.Inject

/**
 * Created by tasci on 2.07.2023.
 */
class BeerResponseToBeerDetailUiModelMapper @Inject constructor(): BaseMapper<BeerResponse, BeerDetailUiModel>() {

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