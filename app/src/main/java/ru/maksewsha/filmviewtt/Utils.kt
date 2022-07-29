package ru.maksewsha.filmviewtt

import android.content.Context
import ru.maksewsha.filmviewtt.dagger.AppComponent

val Context.appComponent: AppComponent
get() = when(this){
    is App -> appComponent
    else -> this.applicationContext.appComponent
}