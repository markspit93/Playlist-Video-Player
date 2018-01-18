package com.playlist.video.player.data.repository

import com.playlist.video.player.data.repository.datasource.playlist.PlaylistDataSource
import com.playlist.video.player.data.repository.datasource.playlist.remote.RemotePlaylistDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlaylistRepository @Inject constructor(private val remotePlaylistDataSource: RemotePlaylistDataSource) : PlaylistDataSource {

    override fun getPlaylist() = remotePlaylistDataSource.getPlaylist()
}
