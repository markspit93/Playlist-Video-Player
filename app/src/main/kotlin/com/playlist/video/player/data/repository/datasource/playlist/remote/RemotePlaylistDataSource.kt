package com.playlist.video.player.data.repository.datasource.playlist.remote

import com.playlist.video.player.data.repository.datasource.playlist.PlaylistDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemotePlaylistDataSource @Inject constructor(private val playlistService: PlaylistService) : PlaylistDataSource {

    override fun getPlaylist() = playlistService.getPlaylist()
}
