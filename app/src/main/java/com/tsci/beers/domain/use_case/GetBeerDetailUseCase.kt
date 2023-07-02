package com.tsci.beers.domain.use_case

import com.tsci.beers.core.BaseUseCase
import com.tsci.beers.data.Resource
import com.tsci.beers.data.model.BeerResponse
import com.tsci.beers.data.repository.BeerRepository
import com.tsci.beers.domain.IMapper
import com.tsci.beers.ui.model.BeerDetailUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by tasci on 28.06.2023.
 */
class GetBeerDetailUseCase(
    private val repository: BeerRepository
) : BaseUseCase<Int, BeerDetailUiModel>() {

    override fun execute(input: Int): Flow<Resource<BeerDetailUiModel>> = flow {
        emit(Resource.Loading(true))
        val response = repository.getBeerDetail(input)
        response.onSuccess {
            val uiModel = beerResponseModelToBeerDetailUiModel.map(it)
            emit(Resource.Success(uiModel))
        }.onError {
            emit(Resource.Error(it))
        }.finally {
            emit(Resource.Loading(false))
        }
    }

    private companion object{

        const val UNKNOWN_VALUE = "Unknown Value"

        val beerResponseModelToBeerDetailUiModel = object :
            IMapper<BeerResponse, BeerDetailUiModel> {
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
}