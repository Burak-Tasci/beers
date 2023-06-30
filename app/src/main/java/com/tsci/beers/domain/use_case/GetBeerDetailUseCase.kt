package com.tsci.beers.domain.use_case

import com.tsci.beers.core.BaseUseCase
import com.tsci.beers.data.Resource
import com.tsci.beers.data.repository.BeerRepository
import com.tsci.beers.domain.Mappers
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
            val uiModel = Mappers.beerResponseModelToBeerDetailUiModel.map(it)
            emit(Resource.Success(uiModel))
        }.onError {
            emit(Resource.Error(it))
        }.finally {
            emit(Resource.Loading(false))
        }
    }
}