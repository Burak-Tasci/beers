package com.tsci.beers.core

import com.tsci.beers.data.Resource
import kotlinx.coroutines.flow.Flow

/**
 * Created by tasci on 25.06.2023.
 */
abstract class BaseUseCase<Input, Output> {

    abstract fun execute(input: Input): Flow<Resource<Output>>
}