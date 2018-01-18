package com.playlist.video.player.flow.main

import android.support.test.filters.SmallTest
import android.support.test.runner.AndroidJUnit4
import com.playlist.video.player.R
import com.github.feed.sample.ui.main.MainActivity
import com.github.feed.sample.util.toastIsShown
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith

@SmallTest
@RunWith(AndroidJUnit4::class)
class MainScreenFailureTest : com.playlist.video.player.BaseInstrumentationTest<MainActivity>(MainActivity::class.java) {

    companion object {
        @JvmStatic
        @BeforeClass
        fun setupClass() {
            com.playlist.video.player.data.repository.datasource.event.remote.RemoteEventDataSourceBehaviour.eventsConfig = com.playlist.video.player.data.repository.datasource.event.remote.RemoteEventDataSourceBehaviour.EventConfig.RATE_LIMIT_EXCEEDED
        }
    }

    @Test
    fun showLimiExceededError() {
        // Arrange

        // Act

        // Assert
        toastIsShown(testRule.activity, com.playlist.video.player.R.string.error_rate_limit)
    }
}
