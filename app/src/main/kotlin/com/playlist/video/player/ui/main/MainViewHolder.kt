package com.playlist.video.player.ui.main

import android.support.v7.widget.RecyclerView
import android.view.View
import com.playlist.video.player.data.model.Video
import com.playlist.video.player.ext.loadImage
import kotlinx.android.synthetic.main.item_video.view.*

class MainViewHolder(itemView: View,
                     private val func: (position: Int) -> Unit) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    fun bindItem(video: Video) {
        itemView.imgThumbnail.loadImage(video.thumbnailUrl)

        itemView.txtTitle.text = video.title
        itemView.txtDuration.text = video.duration.toString()

        // TODO: TIME FORMATTING

        itemView.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        func(adapterPosition)
    }
}
