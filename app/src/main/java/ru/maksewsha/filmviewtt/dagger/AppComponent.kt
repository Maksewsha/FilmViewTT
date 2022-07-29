package ru.maksewsha.filmviewtt.dagger

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.Component
import ru.maksewsha.filmviewtt.MainActivity
import ru.maksewsha.filmviewtt.data.network.RetrofitService
import ru.maksewsha.filmviewtt.data.repository.NetworkRepository
import ru.maksewsha.filmviewtt.domain.usecases.GetAllReviewsCase
import ru.maksewsha.filmviewtt.presentation.elements.ReviewsViewAdapter
import ru.maksewsha.filmviewtt.presentation.fragments.ReviewsFragment
import ru.maksewsha.filmviewtt.presentation.viewmodels.ReviewsViewModel
import ru.maksewsha.filmviewtt.presentation.viewmodels.factories.ReviewsViewModelFactory
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class, PresentationModule::class, DomainModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)
    fun inject(viewModelFactory: ReviewsViewModelFactory)
    fun inject(viewModel: ReviewsViewModel)
    fun inject(reviewsFragment: ReviewsFragment)
    val networkRepository: NetworkRepository
    val viewModelFactory: ReviewsViewModelFactory
    val retrofitService: RetrofitService
    val getAllUseCase: GetAllReviewsCase
    val linearLayoutManager: LinearLayoutManager
    val reviewsViewAdapter: ReviewsViewAdapter
    val reviewsFragment: ReviewsFragment
}

