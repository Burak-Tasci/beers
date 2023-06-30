package com.tsci.beers.domain.use_case

import com.tsci.beers.core.NoParamBaseUseCase
import com.tsci.beers.data.Resource
import com.tsci.beers.data.repository.BeerRepository
import com.tsci.beers.domain.Mappers.beerResponseModelToBeerItemUiModel
import com.tsci.beers.ui.model.BeerItemUiModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by tasci on 25.06.2023.
 */
class GetAllBeersUseCase(
    private val repository: BeerRepository
): NoParamBaseUseCase<List<BeerItemUiModel>>(){


    override fun execute(): Flow<Resource<List<BeerItemUiModel>>> = flow {
        emit(Resource.Loading(true))
        delay(2000L)
        val response = repository.getAllBeers()
        response.onSuccess {
            val domainModel = it.map { model -> beerResponseModelToBeerItemUiModel.map(model) }
            emit(Resource.Success(domainModel))
        }.onError  {
            emit(Resource.Error(it))
        }.finally {
            emit(Resource.Loading(false))
        }
    }
}