package com.playlist.video.player.ui.eventlist

import android.arch.lifecycle.ViewModel
import com.github.feed.sample.data.EVENT_CREATE
import com.github.feed.sample.data.model.Actor
import com.github.feed.sample.data.model.Event
import com.github.feed.sample.data.model.EventsResponse
import com.github.feed.sample.data.model.Repository
import com.github.feed.sample.ui.eventlist.EventViewModel.Config.*
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers

class EventViewModel(private val config: Config) : ViewModel() {

    private val testActor = Actor("testUsername", "")
    private val testRepository = Repository(1, "testRepo", "www.test.com")
    private val testEvent = Event("1", EVENT_CREATE, "1", true, testActor, testRepository, null)

    enum class Config {
        NONE,
        SUCCESSFUL,
        RATE_LIMIT_EXCEEDED,
        GENERIC_ERROR
    }

    fun getEvents(): Flowable<EventsResponse> {
        val flowable = Flowable.just(when (config) {
            SUCCESSFUL -> EventsResponse.Success(listOf(testEvent))
            RATE_LIMIT_EXCEEDED -> EventsResponse.RateLimitExceeded
            GENERIC_ERROR -> throw RuntimeException()
            else -> EventsResponse.Success(listOf())
        })

        return flowable.observeOn(AndroidSchedulers.mainThread())
    }
}
