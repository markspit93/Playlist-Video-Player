package com.playlist.video.player.di

import com.playlist.video.player.data.api.ServiceGenerator
import com.playlist.video.player.data.repository.datasource.playlist.remote.PlaylistService
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjectionModule

@Module(includes = arrayOf(AndroidInjectionModule::class))
class AppModule {

    @Provides
    fun providePlayListService(serviceGenerator: ServiceGenerator): PlaylistService
            = serviceGenerator.create(PlaylistService.ENDPOINT, PlaylistService::class.java)
}
