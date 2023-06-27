package com.tsci.beers.ext

import android.widget.ImageView
import com.squareup.picasso.Picasso

/**
 * Created by tasci on 26.06.2023.
 */


fun ImageView.setUrl(url: String?){
    val safeUrl = url?.let {
        if (it.trim().isEmpty()) {
            null
        } else {
            it
        }
    }
    Picasso.get().load(safeUrl).into(this)
}