package ru.maksewsha.filmviewtt.presentation.viewmodels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.maksewsha.filmviewtt.data.network.RetrofitService
import ru.maksewsha.filmviewtt.data.repository.NetworkRepository
import ru.maksewsha.filmviewtt.domain.usecases.GetAllReviewsCase
import ru.maksewsha.filmviewtt.presentation.viewmodels.ReviewsViewModel
import javax.inject.Inject

class ReviewsViewModelFactory: ViewModelProvider.Factory {

    @Inject
    lateinit var getAllReviewsCase: GetAllReviewsCase

    override fun <T : ViewModel> create(modelClass: Class<T>) = ReviewsViewModel(getAllReviewsCase) as T

}