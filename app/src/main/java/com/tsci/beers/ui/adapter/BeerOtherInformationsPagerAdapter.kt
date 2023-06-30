package com.tsci.beers.ui.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.tsci.beers.features.beer_other_informations.BeerOtherInformationsFragment

/**
 * Created by tasci on 30.06.2023.
 */
class BeerOtherInformationsPagerAdapter(
    private val description: String,
    private val brewerTips: String,
    private val foodPairing: String,
    fragment: Fragment
) : FragmentStateAdapter(fragment) {


    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            INDEX_DESCRIPTION -> BeerOtherInformationsFragment.newInstance(description)
            INDEX_BREWER_TIPS -> BeerOtherInformationsFragment.newInstance(brewerTips)
            INDEX_FOOD_PAIRING -> BeerOtherInformationsFragment.newInstance(foodPairing, true)
            else -> BeerOtherInformationsFragment.newInstance("")
        }
    }


    companion object {
        const val INDEX_DESCRIPTION = 0
        const val INDEX_BREWER_TIPS = 1
        const val INDEX_FOOD_PAIRING = 2
    }

}