package com.playlist.video.player.di.android

import com.playlist.video.player.di.scopes.PerActivity
import com.playlist.video.player.ui.details.VideoPlayerActivity
import com.playlist.video.player.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityProvider {

    @PerActivity
    @ContributesAndroidInjector
    abstract fun provideMainActivityInjector(): MainActivity

    @PerActivity
    @ContributesAndroidInjector
    abstract fun provideVideoPlayerActivityInjector(): VideoPlayerActivity
}
