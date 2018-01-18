package com.playlist.video.player.data.repository.datasource.event.remote

import com.github.feed.sample.data.EVENT_CREATE
import com.github.feed.sample.data.model.Actor
import com.github.feed.sample.data.model.Event
import com.github.feed.sample.data.model.Repository
import com.github.feed.sample.data.repository.datasource.event.remote.RemoteEventDataSourceBehaviour.EventConfig.*
import io.reactivex.Observable
import io.reactivex.Observable.just
import okhttp3.MediaType
import okhttp3.ResponseBody
import retrofit2.Response
import java.net.HttpURLConnection

object RemoteEventDataSourceBehaviour {

    private val testActor = Actor("testUsername", "")

    private val testRepository = Repository(1, "testRepo", "www.test.com")

    private val testEvents = listOf(
            Event("1", EVENT_CREATE, "1", true, com.playlist.video.player.data.repository.datasource.event.remote.RemoteEventDataSourceBehaviour.testActor, com.playlist.video.player.data.repository.datasource.event.remote.RemoteEventDataSourceBehaviour.testRepository, null)
    )

    enum class EventConfig {
        NONE,
        SUCCESSFUL,
        RATE_LIMIT_EXCEEDED
    }

    var eventsConfig: com.playlist.video.player.data.repository.datasource.event.remote.RemoteEventDataSourceBehaviour.EventConfig = NONE

    fun runGetEventsConfig(): Observable<Response<List<Event>>> =
            when (com.playlist.video.player.data.repository.datasource.event.remote.RemoteEventDataSourceBehaviour.eventsConfig) {
                SUCCESSFUL -> just(Response.success(com.playlist.video.player.data.repository.datasource.event.remote.RemoteEventDataSourceBehaviour.testEvents))
                RATE_LIMIT_EXCEEDED -> just(Response.error(HttpURLConnection.HTTP_FORBIDDEN, ResponseBody.create(
                        MediaType.parse("application/json"),""
                )))
                else -> just(Response.success(listOf()))
            }
}
