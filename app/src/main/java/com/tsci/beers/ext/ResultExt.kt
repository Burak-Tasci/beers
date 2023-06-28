package com.tsci.beers.ext

import com.tsci.beers.util.LogHelper.log

/**
 * Created by tasci on 28.06.2023.
 */


fun <T> Result<T>.logOnFailure() {
    onFailure {
        exceptionOrNull()?.log()
    }
}