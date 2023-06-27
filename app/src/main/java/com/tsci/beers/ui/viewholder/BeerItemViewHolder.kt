package com.tsci.beers.ui.viewholder

import com.tsci.beers.core.BaseViewHolder
import com.tsci.beers.databinding.ItemBeerBinding
import com.tsci.beers.ext.setUrl
import com.tsci.beers.ui.model.BeerUiModel

/**
 * Created by tasci on 26.06.2023.
 */
class BeerItemViewHolder(
    private val binding: ItemBeerBinding
): BaseViewHolder<BeerUiModel>(binding) {
    override fun bind(item: BeerUiModel) {
        binding.imageViewBeerImage.setUrl(item.imageUrl)
        binding.textViewBeerName.text = item.name
        binding.textViewTagLine.text = item.tagLine
        binding.textViewTagLine.isSelected = true
    }
}