package ru.maksewsha.filmviewtt

import android.app.Activity
import android.app.Application
import android.os.Bundle
import ru.maksewsha.filmviewtt.dagger.AppComponent
import ru.maksewsha.filmviewtt.dagger.DaggerAppComponent
import ru.maksewsha.filmviewtt.dagger.PresentationModule


class App: Application() {
    lateinit var appComponent: AppComponent


    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().presentationModule(PresentationModule(this)).build()
    }
}