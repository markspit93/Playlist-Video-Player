package com.playlist.video.player.flow.details

import android.support.test.filters.SmallTest
import android.support.test.runner.AndroidJUnit4
import com.playlist.video.player.R
import com.github.feed.sample.ui.details.DetailsActivity
import com.github.feed.sample.ui.main.MainActivity
import com.github.feed.sample.util.clickView
import com.github.feed.sample.util.nextOpenActivityIs
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith

@SmallTest
@RunWith(AndroidJUnit4::class)
class DetailsScreenTest : com.playlist.video.player.BaseInstrumentationTest<MainActivity>(MainActivity::class.java) {

    companion object {
        @JvmStatic
        @BeforeClass
        fun setupClass() {
            com.playlist.video.player.data.repository.datasource.event.remote.RemoteEventDataSourceBehaviour.eventsConfig = com.playlist.video.player.data.repository.datasource.event.remote.RemoteEventDataSourceBehaviour.EventConfig.SUCCESSFUL
        }
    }

    @Test
    fun showEventDetails() {
        // Arrange

        // Act
        clickView(com.playlist.video.player.R.id.cardEvent)

        // Assert
        nextOpenActivityIs<DetailsActivity>()
    }
}