package com.playlist.video.player

import android.app.Activity
import android.app.Application
import com.facebook.stetho.Stetho
import com.playlist.video.player.di.AppModule
import com.playlist.video.player.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class App : Application(), HasActivityInjector {

    @Inject lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        initDagger()
        initStetho()
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityInjector
    }

    private fun initDagger() {
        DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .build()
                .inject(this)
    }

    private fun initStetho() {
        if (BuildConfig.DEBUG_MODE) {
            Stetho.initialize(Stetho.newInitializerBuilder(this)
                    .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                    .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                    .build())
        }
    }
}
