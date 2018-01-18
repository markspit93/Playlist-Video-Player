package com.playlist.video.player.data

import io.objectbox.BoxStore
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.io.File

@RunWith(MockitoJUnitRunner::class)
abstract class BaseLocalDataSourceTest {

    open lateinit var boxStore: BoxStore

    @Before
    fun setup() {
        val tempFile = File.createTempFile("object-store-test", "").apply { delete() }
        boxStore = com.playlist.video.player.data.model.MyObjectBox.builder().directory(tempFile).build()
    }

    @After
    fun tearDown() {
        boxStore.close()
        boxStore.deleteAllFiles()
    }
}
