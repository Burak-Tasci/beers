package com.tsci.beers.features.detail

import android.view.LayoutInflater
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayoutMediator
import com.tsci.beers.MainViewModel
import com.tsci.beers.R
import com.tsci.beers.core.BaseFragment
import com.tsci.beers.databinding.FragmentDetailBinding
import com.tsci.beers.ext.extractBulletPoints
import com.tsci.beers.ext.setUrl
import com.tsci.beers.ui.adapter.BeerOtherInformationsPagerAdapter
import com.tsci.beers.ui.model.BeerDetailUiModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>(R.layout.fragment_detail) {

    override val bindingInflater: (LayoutInflater) -> FragmentDetailBinding
        get() = FragmentDetailBinding::inflate

    private val viewModel: DetailViewModel by viewModels()

    private val sharedViewModel by activityViewModels<MainViewModel>()

    private val args by navArgs<DetailFragmentArgs>()

    override fun setupUi() {
        initArguments()
        setToolbarTitle(R.string.app_name)
        showBackButton()
        observeUiEvents()
    }

    private fun initArguments() {
        val beerId = args.id
        viewModel.getBeerDetail(beerId)
    }

    private fun observeUiEvents() {
        lifecycleScope.launch {
            viewModel.events.collect { event ->
                when (event) {
                    is DetailViewModel.UiEvent.DetailModel -> setupDetailModel(event.detailModel)
                    is DetailViewModel.UiEvent.Error -> sharedViewModel.onError(event.errorModel)
                    is DetailViewModel.UiEvent.Loading -> sharedViewModel.onLoading(event.isLoading)
                }
            }
        }
    }

    private fun setupDetailModel(detailModel: BeerDetailUiModel) {
        detailModel.apply {
            setToolbarSubtitle(name)
            binding.imageViewBeerImage.setUrl(imageUrl)
            binding.textViewYeast.text = yeast
            binding.textViewFirstBrew.text = getString(R.string.detail_first_brew_at, date)
            setupBeerOtherInformationsAdapter(
                description,
                brewerTips,
                foodPairing.extractBulletPoints("sans-serif")
            )
            attachTabLayoutMediator()
        }

    }

    private fun setupBeerOtherInformationsAdapter(
        description: String,
        brewerTips: String,
        foodPairing: String
    ) {
        val adapter = BeerOtherInformationsPagerAdapter(
            description,
            brewerTips,
            foodPairing,
            this
        )
        binding.viewPagerOtherInformations.adapter = adapter
    }

    private fun attachTabLayoutMediator() {
        TabLayoutMediator(
            binding.tabLayoutOtherInformations,
            binding.viewPagerOtherInformations
        ) { tab, position ->
            val title = when (position) {
                BeerOtherInformationsPagerAdapter.INDEX_DESCRIPTION -> getString(R.string.detail_tab_description)
                BeerOtherInformationsPagerAdapter.INDEX_BREWER_TIPS -> getString(R.string.detail_tab_brewer_tips)
                BeerOtherInformationsPagerAdapter.INDEX_FOOD_PAIRING -> getString(R.string.detail_tab_food_pairing)
                else -> ""
            }
            tab.text = title
        }.attach()
    }


}