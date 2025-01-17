package com.holycode.github

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(url: String?) {
    if (url.isNullOrEmpty()) return

    Glide.with(this)
        .load(url)
        .into(this)
}