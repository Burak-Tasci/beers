package com.tsci.beers.core

import com.tsci.beers.data.Resource
import kotlinx.coroutines.flow.Flow

/**
 * Created by tasci on 25.06.2023.
 */
abstract class NoParamBaseUseCase <Output> {
    abstract fun execute(): Flow<Resource<Output>>
}
