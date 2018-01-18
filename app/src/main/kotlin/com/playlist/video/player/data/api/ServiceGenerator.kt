package com.playlist.video.player.data.api

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.playlist.video.player.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ServiceGenerator @Inject constructor() {

    fun <T> create(endpoint: String, serviceClass: Class<T>): T {
        val httpClient = OkHttpClient.Builder()
        httpClient.readTimeout(20, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG_MODE) {
            httpClient.addNetworkInterceptor(StethoInterceptor())
        }

        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(endpoint)
                .client(httpClient.build())
                .build()
                .create(serviceClass)
    }
}
