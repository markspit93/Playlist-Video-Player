package com.playlist.video.player.ext

import android.widget.ImageView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.playlist.video.player.R
import com.playlist.video.player.ui.common.GlideApp

fun ImageView.loadImage(url: String?) {
    GlideApp.with(context)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .placeholder(R.drawable.ic_movies_grey_24dp)
            .transition(withCrossFade())
            .into(this)
}
