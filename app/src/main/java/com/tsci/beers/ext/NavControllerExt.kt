package com.tsci.beers.ext

import androidx.annotation.IdRes
import androidx.navigation.NavController
import androidx.navigation.NavDirections

/**
 * Created by tasci on 28.06.2023.
 */

fun NavController.safeNavigation(@IdRes id: Int){
    runCatching {
        navigate(id)
    }.logOnFailure()
}

fun NavController.safeNavigation(action: NavDirections){
    runCatching {
        navigate(action)
    }.logOnFailure()
}