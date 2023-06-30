package com.tsci.beers.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.tsci.beers.databinding.ItemBeerBinding
import com.tsci.beers.ui.model.BeerItemUiModel
import com.tsci.beers.ui.viewholder.BeerItemViewHolder

/**
 * Created by tasci on 26.06.2023.
 */
class BeerListAdapter(
    private val onItemClick: (id: Int) -> Unit
) : ListAdapter<BeerItemUiModel, BeerItemViewHolder>(
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

    override fun onBindViewHolder(holder: BeerItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.setOnItemClickListener {
            onItemClick(item.id)
        }
    }


    private companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<BeerItemUiModel>() {
            override fun areItemsTheSame(oldItem: BeerItemUiModel, newItem: BeerItemUiModel): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: BeerItemUiModel, newItem: BeerItemUiModel): Boolean =
                oldItem == newItem
        }
    }
}