package com.playlist.video.player.di

import com.playlist.video.player.App
import com.playlist.video.player.di.android.ActivityProvider
import com.playlist.video.player.di.android.FragmentProvider
import com.playlist.video.player.di.viewmodel.ViewModelsModule
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityProvider::class,
        FragmentProvider::class,
        ViewModelsModule::class))
interface AppComponent {

    fun inject(app: App)
}
