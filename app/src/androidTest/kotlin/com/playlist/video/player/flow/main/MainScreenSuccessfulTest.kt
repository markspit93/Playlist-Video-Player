package com.playlist.video.player.flow.main

import android.support.test.filters.SmallTest
import android.support.test.runner.AndroidJUnit4
import com.playlist.video.player.R
import com.playlist.video.player.data.repository.datasource.event.remote.RemoteEventDataSourceBehaviour.EventConfig.SUCCESSFUL
import com.github.feed.sample.ui.main.MainActivity
import com.github.feed.sample.util.clickView
import com.github.feed.sample.util.doesNotExist
import com.github.feed.sample.util.viewIsVisible
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith

@SmallTest
@RunWith(AndroidJUnit4::class)
class MainScreenSuccessfulTest : com.playlist.video.player.BaseInstrumentationTest<MainActivity>(MainActivity::class.java) {

    companion object {
        @JvmStatic
        @BeforeClass
        fun setupClass() {
            com.playlist.video.player.data.repository.datasource.event.remote.RemoteEventDataSourceBehaviour.eventsConfig = SUCCESSFUL
        }
    }

    @Test
    fun loadEvents() {
        // Arrange

        // Act

        // Assert
        viewIsVisible(com.playlist.video.player.R.id.eventRecyclerView)
    }

    @Test
    fun filterEvents() {
        // Arrange

        // Act
        clickView(com.playlist.video.player.R.id.menu_item_filter)
        clickView(com.playlist.video.player.R.id.action_filter_create)

        // Assert
        doesNotExist(com.playlist.video.player.R.id.eventRecyclerView)
    }
}
