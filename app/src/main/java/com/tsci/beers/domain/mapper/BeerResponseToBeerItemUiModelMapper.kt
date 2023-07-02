package com.tsci.beers.domain.mapper

import com.tsci.beers.core.BaseMapper
import com.tsci.beers.data.model.BeerResponse
import com.tsci.beers.ui.model.BeerItemUiModel
import javax.inject.Inject

/**
 * Created by tasci on 2.07.2023.
 */
class BeerResponseToBeerItemUiModelMapper @Inject constructor(): BaseMapper<BeerResponse, BeerItemUiModel>() {

    override fun map(input: BeerResponse): BeerItemUiModel = BeerItemUiModel(
        id = input.id ?: UNKNOWN_ID,
        name = input.name ?: UNKNOWN_VALUE,
        imageUrl = input.imageUrl ?: UNKNOWN_VALUE,
        tagLine = input.tagline ?: UNKNOWN_VALUE
    )
}