package com.playlist.video.player.data.repository.datasource.event.remote

import com.github.feed.sample.data.repository.datasource.event.EventDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteEventDataSource @Inject constructor(private val service: com.playlist.video.player.data.repository.datasource.event.remote.EventService) : EventDataSource {

    override fun getEvents() = com.playlist.video.player.data.repository.datasource.event.remote.RemoteEventDataSourceBehaviour.runGetEventsConfig()
}
