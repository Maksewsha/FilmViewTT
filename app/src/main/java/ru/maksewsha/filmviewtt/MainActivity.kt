package ru.maksewsha.filmviewtt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProvider
import ru.maksewsha.filmviewtt.presentation.fragments.ReviewsFragment
import ru.maksewsha.filmviewtt.presentation.viewmodels.ReviewsViewModel
import ru.maksewsha.filmviewtt.presentation.viewmodels.factories.ReviewsViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var reviewsViewModel: ReviewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        reviewsViewModel = ViewModelProvider(viewModelStore, ReviewsViewModelFactory()).get(ReviewsViewModel::class.java)

        reviewsViewModel.toggleLoading()
        reviewsViewModel.loadNextPage()

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, ReviewsFragment(), ReviewsFragment::class.java.canonicalName)
            .commit()
    }
}