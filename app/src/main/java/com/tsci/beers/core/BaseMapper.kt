package com.tsci.beers.core

import com.tsci.beers.domain.IMapper

/**
 * Created by tasci on 2.07.2023.
 */
abstract class BaseMapper<Input, Output>: IMapper<Input, Output> {



    protected companion object{
        const val UNKNOWN_VALUE = "Unknown Value"
        const val UNKNOWN_ID = 0
    }
}