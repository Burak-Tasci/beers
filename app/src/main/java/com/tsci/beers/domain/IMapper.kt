package com.tsci.beers.domain

/**
 * Created by tasci on 25.06.2023.
 */
interface IMapper<Input, Output> {
    fun map(input: Input): Output
}