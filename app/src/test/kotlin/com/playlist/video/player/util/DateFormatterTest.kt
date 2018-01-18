package com.playlist.video.player.util

import android.content.Context
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DateFormatterTest {

    @Mock
    lateinit var mockContext: Context

    @Test
    fun testDateFormatting() {
        assertThat(DateUtils.formatDate(mockContext, "2000-01-01T01:10:10Z"), `is`("Jan 01, 01:10AM"))
    }
}

