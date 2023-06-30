package com.tsci.beers.di

import com.tsci.beers.data.repository.BeerRepository
import com.tsci.beers.domain.use_case.GetAllBeersUseCase
import com.tsci.beers.domain.use_case.GetBeerDetailUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

/**
 * Created by tasci on 25.06.2023.
 */

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {

    @ViewModelScoped
    @Provides
    fun providesGetAllBeersUseCase(beerRepository: BeerRepository) = GetAllBeersUseCase(
        beerRepository
    )
    @ViewModelScoped
    @Provides
    fun providesGetBeerDetailUseCase(beerRepository: BeerRepository) = GetBeerDetailUseCase(
        beerRepository
    )
}