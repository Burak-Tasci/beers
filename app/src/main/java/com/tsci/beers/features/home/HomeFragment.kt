package com.tsci.beers.features.home

import android.view.LayoutInflater
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.tsci.beers.MainViewModel
import com.tsci.beers.R
import com.tsci.beers.core.BaseFragment
import com.tsci.beers.databinding.FragmentHomeBinding
import com.tsci.beers.ext.safeNavigation
import com.tsci.beers.ui.adapter.BeerListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    override val bindingInflater: (LayoutInflater) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    private val viewModel by viewModels<HomeViewModel>()

    private val sharedViewModel by activityViewModels<MainViewModel>()

    private var beerListAdapter = BeerListAdapter(::onItemClick)

    override fun setupUi() {
        setToolbarTitle(R.string.app_name)
        setToolbarSubtitle(R.string.home_title)
        initAdapter()
        observeEvents()
    }

    private fun initAdapter() {
        binding.recyclerViewBeers.adapter = beerListAdapter
    }

    private fun observeEvents() {
        lifecycleScope.launch {
            viewModel.events.collect { event ->
                when (event) {
                    is HomeViewModel.UiEvent.Beers -> beerListAdapter.submitList(event.beers)
                    is HomeViewModel.UiEvent.Error -> sharedViewModel.onError(event.errorModel)
                    is HomeViewModel.UiEvent.Loading -> sharedViewModel.onLoading(event.isLoading)
                }
            }
        }
    }

    private fun onItemClick(id: Int){
        val action = HomeFragmentDirections.toDetailFragment(id)
        findNavController().safeNavigation(action)
    }

}