package com.tsci.beers.domain.use_case

import com.tsci.beers.core.NoParamBaseUseCase
import com.tsci.beers.data.Resource
import com.tsci.beers.data.repository.BeerRepository
import com.tsci.beers.domain.Mappers.beerResponseModelToDomainModel
import com.tsci.beers.ui.model.BeerUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by tasci on 25.06.2023.
 */
class GetAllBeersUseCase(
    private val repository: BeerRepository
): NoParamBaseUseCase<List<BeerUiModel>>(){


    override fun execute(): Flow<Resource<List<BeerUiModel>>> = flow {
        emit(Resource.Loading(true))
        val response = repository.getAllBeers()
        response.onSuccess {
            val domainModel = it.map { model -> beerResponseModelToDomainModel.map(model) }
            emit(Resource.Success(domainModel))
        }.onError  {
            emit(Resource.Error(it))
        }.finally {
            emit(Resource.Loading(false))
        }
    }
}