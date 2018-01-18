package com.playlist.video.player.ui.eventlist

import com.github.feed.sample.ui.BasePresenterTest
import com.github.feed.sample.ui.eventlist.EventViewModel
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.BeforeClass
import org.junit.Test
import org.mockito.Mockito.verify

class EventListPresenterTest : BasePresenterTest<EventListContract.View, EventListPresenter, EventViewModel>() {

    override var view: EventListContract.View = mock()

    override fun createPresenter() = EventListPresenter()

    companion object {
        @JvmStatic
        @BeforeClass
        fun setupClass() {
            RxAndroidPlugins.setInitMainThreadSchedulerHandler { _ -> Schedulers.trampoline() }
        }
    }

    @Test
    fun getEvents_successful() {
        // Arrange
        setViewModel(EventViewModel(EventViewModel.Config.SUCCESSFUL))

        // Act
        // presenter.onActive() is called automatically

        // Assert
        verify(view).showEvents(any())
    }

    @Test
    fun getEvents_failureLimitExceeded() {
        // Arrange
        setViewModel(EventViewModel(EventViewModel.Config.RATE_LIMIT_EXCEEDED))

        // Act
        // presenter.onActive() is called automatically

        // Assert
        verify(view).showLimitErrorMessage()
    }

    @Test(expected = RuntimeException::class)
    fun getEvents_failureGenericError() {
        // Arrange
        setViewModel(EventViewModel(EventViewModel.Config.GENERIC_ERROR))

        // Act
        // presenter.onActive() is called automatically

        // Assert
        verify(view).showGenericError()
    }
}
