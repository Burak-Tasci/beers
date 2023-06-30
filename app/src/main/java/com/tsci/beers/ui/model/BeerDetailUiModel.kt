package com.tsci.beers.ui.model

/**
 * Created by tasci on 28.06.2023.
 */
data class BeerDetailUiModel(
    val name: String, //
    val date: String, //
    val imageUrl: String, //
    val description: String,
    val foodPairing: List<String>,
    val brewerTips: String,
    val yeast: String, //
)