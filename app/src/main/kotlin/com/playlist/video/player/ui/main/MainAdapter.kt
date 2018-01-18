package com.playlist.video.player.ui.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.playlist.video.player.R
import com.playlist.video.player.data.model.Video

class MainAdapter(private val videoClick: (Int) -> Unit) : RecyclerView.Adapter<MainViewHolder>() {

    private val items: MutableList<Video> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_video, parent, false), videoClick)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bindItem(items[position])
    }

    override fun getItemCount() = items.size

    fun addItems(videos: List<Video>) {
        items.addAll(videos)
        notifyDataSetChanged()
    }
}
