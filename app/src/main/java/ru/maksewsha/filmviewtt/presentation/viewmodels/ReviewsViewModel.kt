package ru.maksewsha.filmviewtt.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import ru.maksewsha.filmviewtt.domain.usecases.GetAllReviewsCase
import ru.maksewsha.filmviewtt.presentation.model.ReviewPresentation
import ru.maksewsha.filmviewtt.presentation.model.entity.ReviewPresentationEntity
import ru.maksewsha.filmviewtt.presentation.utils.PresentationMapper
import javax.inject.Inject

class ReviewsViewModel(private val getAllReviewsCase: GetAllReviewsCase): ViewModel() {

    @Inject
    lateinit var mapper: PresentationMapper

    private val _reviews = MutableLiveData<List<ReviewPresentation>>()
    val reviews = _reviews as LiveData<List<ReviewPresentation>>

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage = _errorMessage as LiveData<String>

    private var isLoading = false
    private var totalPages = 10000
    private var isLastPage = false

    fun toggleLoading(){
        isLoading = !isLoading
    }

    private var currentPage = 0

    private fun switchToNextPage(){
        currentPage += 20
        if (currentPage == totalPages) isLastPage = true
    }

    fun isLastPage() = isLastPage

    fun isLoading() = isLoading

    fun loadNextPage(){
        viewModelScope.launch(Dispatchers.IO){
            when(val result = mapper.mapToEntity(getAllReviewsCase.execute(currentPage))){
                is ReviewPresentationEntity.Success -> {
                    _reviews.postValue(result.data.results)
                    Log.d("MyTag", result.data.results.toString())
                }
                is ReviewPresentationEntity.Fail -> {
                    _errorMessage.postValue(result.errorMessage)
                    Log.d("MyTag", result.errorMessage)
                }
            }
        }
        switchToNextPage()
    }
}