package com.tsci.beers.di

import com.tsci.beers.data.NetworkManager
import com.tsci.beers.data.api.BeerApi
import com.tsci.beers.data.repository.BeerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by tasci on 25.06.2023.
 */
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providesBeerRepository(networkManager: NetworkManager, beerApi: BeerApi) =
        BeerRepository(networkManager, beerApi)
}