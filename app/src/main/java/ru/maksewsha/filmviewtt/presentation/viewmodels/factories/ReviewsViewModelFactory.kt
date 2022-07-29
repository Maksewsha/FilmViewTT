package ru.maksewsha.filmviewtt.presentation.viewmodels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.maksewsha.filmviewtt.data.network.RetrofitService
import ru.maksewsha.filmviewtt.data.repository.NetworkRepository
import ru.maksewsha.filmviewtt.domain.usecases.GetAllReviewsCase
import ru.maksewsha.filmviewtt.presentation.viewmodels.ReviewsViewModel

class ReviewsViewModelFactory: ViewModelProvider.Factory {

    private val retrofitService by lazy {
        RetrofitService.getInstance()
    }

    private val networkRepository by lazy{
        NetworkRepository(retrofitService)
    }

    private val getAllReviewsCase by lazy{
        GetAllReviewsCase(networkRepository)
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>) = ReviewsViewModel(getAllReviewsCase) as T
}