package com.playlist.video.player.data.repository.datasource.playlist.remote

import com.github.feed.sample.data.BaseLocalDataSourceTest
import com.github.feed.sample.data.model.Filter
import io.objectbox.kotlin.boxFor
import org.junit.Assert.assertEquals
import org.junit.Test

class LocalFilterDataSourceTest : BaseLocalDataSourceTest() {

    @Test
    fun testSaveAndGetFilter() {
        // Arrange
        val filterBox = boxStore.boxFor(Filter::class)

        // Act
        val id = filterBox.put(Filter(tag = "testTag", checked = true))

        // Assert
        val filterFromBox = filterBox.get(id)
        assertEquals("testTag", filterFromBox.tag)
        assertEquals(true, filterFromBox.checked)
    }
}