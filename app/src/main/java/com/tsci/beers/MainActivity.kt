package com.tsci.beers

import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import com.google.android.material.snackbar.Snackbar
import com.tsci.beers.core.BaseActivity
import com.tsci.beers.databinding.ActivityMainBinding
import com.tsci.beers.ext.findNavController
import com.tsci.beers.ext.safeNavigation
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private lateinit var navController: NavController

    private val viewModel by viewModels<MainViewModel>()

    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    override fun setupUi() {

        navController = findNavController()

        observeStates()
    }

    private fun observeStates() {

        lifecycleScope.launch {
            launch {
                viewModel.errorModel.collect { errorModel ->
                    val message: String =
                        errorModel.message.takeIf { it.isNotBlank() } ?: return@collect

                    Snackbar.make(this@MainActivity, binding.viewTop, message, Snackbar.LENGTH_LONG)
                        .show()
                }
            }
            launch {
                viewModel.isLoading.collect { isLoading ->
                    val isCurrentDestinationLoadingDialog = navController.currentDestination?.id == R.id.loadingDialog

                    when{
                        isLoading && !isCurrentDestinationLoadingDialog ->
                            navController.safeNavigation(R.id.loadingDialog)
                        !isLoading && isCurrentDestinationLoadingDialog ->
                            navController.navigateUp()
                    }
                }
            }


        }
    }
}
