package com.playlist.video.player.data.repository.datasource.event.remote

import com.github.feed.sample.data.BaseRemoteDataSourceTest
import org.junit.Test
import java.net.HttpURLConnection.HTTP_FORBIDDEN
import java.net.HttpURLConnection.HTTP_OK

class RemoteEventDataSourceTest : BaseRemoteDataSourceTest<EventService, RemoteEventDataSource>(EventService::class.java) {

    override fun createDataSource(service: EventService) = RemoteEventDataSource(service)

    @Test
    fun getEvents_successful() {
        // Arrange
        mockResponse(HTTP_OK, "event/getEvents_successful")

        // Act
        val testObserver = remoteDataSource.getEvents().test()

        // Assert
        testObserver
                .assertNoErrors()
                .assertValue({ value -> requireNotNull(value.code() == HTTP_OK) })
                .assertValue({ value -> requireNotNull(value.body()).isNotEmpty() })
    }

    @Test
    fun getEvents_failureRateLimitExceeded() {
        // Arrange
        mockResponse(HTTP_FORBIDDEN, "event/getEvents_failureRateLimitExceeded")

        // Act
        val testObserver = remoteDataSource.getEvents().test()

        // Assert
        testObserver
                .assertNoErrors()
                .assertValue({ value -> requireNotNull(value.code() == HTTP_FORBIDDEN) })
                .assertValue({ value -> value.body() == null })
    }
}
