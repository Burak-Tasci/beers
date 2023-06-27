package com.tsci.beers.ext

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.tsci.beers.R

/**
 * Created by tasci on 25.06.2023.
 */


fun AppCompatActivity.findNavController(
    @IdRes navigationId: Int = R.id.nav_host_fragment
): NavController{
    val navHostFragment = supportFragmentManager.findFragmentById(navigationId) as NavHostFragment
    return navHostFragment.navController
}