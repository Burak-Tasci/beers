package com.tsci.beers.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.tsci.beers.databinding.ItemBeerBinding
import com.tsci.beers.ui.model.BeerUiModel
import com.tsci.beers.ui.viewholder.BeerItemViewHolder

/**
 * Created by tasci on 26.06.2023.
 */
class BeerListAdapter: ListAdapter<BeerUiModel, BeerItemViewHolder>(
    DIFF_CALLBACK
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerItemViewHolder =
        BeerItemViewHolder(
            ItemBeerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: BeerItemViewHolder, position: Int) = holder.bind(getItem(position))



    private companion object{
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<BeerUiModel>() {
            override fun areItemsTheSame(oldItem: BeerUiModel, newItem: BeerUiModel): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: BeerUiModel, newItem: BeerUiModel): Boolean =
                oldItem == newItem
        }
    }
}