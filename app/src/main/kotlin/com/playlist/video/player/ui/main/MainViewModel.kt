package com.playlist.video.player.ui.main

import android.arch.lifecycle.ViewModel
import com.playlist.video.player.data.model.Video
import com.playlist.video.player.data.repository.PlaylistRepository
import com.playlist.video.player.ext.lazyAndroid
import javax.inject.Inject

class MainViewModel @Inject constructor(private val playlistRepository: PlaylistRepository) : ViewModel() {

    val filterList: List<Video>? by lazyAndroid { playlistRepository.getPlaylist().body() }
}
