package com.playlist.video.player.data.repository.datasource.playlist

import com.playlist.video.player.data.model.Video
import io.reactivex.Observable
import retrofit2.Response

interface PlaylistDataSource {

    fun getPlaylist(): Observable<Response<List<Video>>>
}
