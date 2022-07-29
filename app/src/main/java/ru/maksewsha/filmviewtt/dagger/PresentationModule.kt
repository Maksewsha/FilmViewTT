package ru.maksewsha.filmviewtt.dagger

import android.app.Application
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.Module
import dagger.Provides
import ru.maksewsha.filmviewtt.R
import ru.maksewsha.filmviewtt.presentation.elements.ReviewsViewAdapter
import ru.maksewsha.filmviewtt.presentation.fragments.ReviewsFragment
import ru.maksewsha.filmviewtt.presentation.utils.PresentationMapper
import ru.maksewsha.filmviewtt.presentation.viewmodels.factories.ReviewsViewModelFactory
import javax.inject.Singleton

@Module
class PresentationModule(private val application: Application) {
    @Singleton
    @Provides
    fun providesViewModelFactory() = ReviewsViewModelFactory()

    @Singleton
    @Provides
    fun providesReviewsFragment() = ReviewsFragment()

    @Provides
    fun providePresentationMapper() = PresentationMapper()

    @Singleton
    @Provides
    fun provideLinearLayoutManager() = LinearLayoutManager(application.applicationContext, LinearLayoutManager.VERTICAL, false)

    @Singleton
    @Provides
    fun provideReviewsViewAdapter() = ReviewsViewAdapter(application.applicationContext)
}