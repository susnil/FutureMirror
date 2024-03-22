package pl.mobilespot.futuremirror.core

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import pl.mobilespot.futuremirror.BuildConfig
import timber.log.Timber

@HiltAndroidApp
class FMApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}