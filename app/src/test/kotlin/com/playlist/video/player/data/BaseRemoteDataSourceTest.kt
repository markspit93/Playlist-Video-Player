package com.playlist.video.player.data

import com.github.feed.sample.data.api.ServiceGenerator
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import okhttp3.mockwebserver.SocketPolicy
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
abstract class BaseRemoteDataSourceTest<in SERVICE : Any, DATASOURCE : Any>(private val serviceClass: Class<SERVICE>) {

    private val serviceGenerator = ServiceGenerator()
    private lateinit var mockWebServer: MockWebServer
    protected lateinit var remoteDataSource: DATASOURCE

    @Before
    fun setup() {
        mockWebServer = MockWebServer().apply { start(0) }

        serviceGenerator.create(mockWebServer.url("/").toString(), serviceClass).apply {
            remoteDataSource = createDataSource(this)
        }
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    abstract fun createDataSource(service: SERVICE): DATASOURCE

    protected fun mockResponse(httpCode: Int, filePath: String) {
        mockWebServer.enqueue(MockResponse()
                .setResponseCode(httpCode)
                .setBody(javaClass.classLoader.getResource("mock-api-responses/datasource/$filePath.json").readText(Charsets.UTF_8)))
    }

    protected fun mockServerDisconnection() {
        mockWebServer.enqueue(MockResponse().setSocketPolicy(SocketPolicy.DISCONNECT_AT_START))
    }

    protected fun takeRequest(assertion: RecordedRequest.() -> Unit): RecordedRequest {
        return mockWebServer.takeRequest().apply { assertion() }
    }
}
