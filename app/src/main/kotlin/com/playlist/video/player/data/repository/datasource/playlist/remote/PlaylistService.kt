package com.playlist.video.player.data.repository.datasource.playlist.remote

import com.playlist.video.player.BuildConfig
import com.playlist.video.player.data.model.Video
import retrofit2.Response
import retrofit2.http.GET

interface PlaylistService {

    companion object {
        const val ENDPOINT = BuildConfig.PLAYLIST_ENDPOINT
    }

    @GET("/playlist.json")
    fun getPlaylist(): Response<List<Video>>
}
