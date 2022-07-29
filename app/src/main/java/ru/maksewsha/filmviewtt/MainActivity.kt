package ru.maksewsha.filmviewtt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import ru.maksewsha.filmviewtt.presentation.fragments.ReviewsFragment
import ru.maksewsha.filmviewtt.presentation.fragments.SplashFragment
import ru.maksewsha.filmviewtt.presentation.model.ReviewPresentation
import ru.maksewsha.filmviewtt.presentation.viewmodels.ReviewsViewModel
import ru.maksewsha.filmviewtt.presentation.viewmodels.factories.ReviewsViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ReviewsViewModelFactory

    @Inject
    lateinit var reviewsFragment: ReviewsFragment

    val reviewsViewModel by viewModels<ReviewsViewModel>{
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        appComponent.inject(this)
        appComponent.inject(viewModelFactory)
        appComponent.inject(reviewsViewModel)
        appComponent.inject(reviewsFragment)

        setSupportActionBar(findViewById(R.id.custom_toolbar))

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, SplashFragment(), ReviewsFragment::class.java.canonicalName)
            .commit()

        reviewsViewModel.toggleLoading()
        reviewsViewModel.loadNextPage()

        reviewsViewModel.reviews.observe(this, object: Observer<List<ReviewPresentation>>{
            override fun onChanged(t: List<ReviewPresentation>?) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container_view, reviewsFragment, ReviewsFragment::class.java.canonicalName)
                    .commit()
            }
        })

        reviewsViewModel.errorMessage.observe(this, object: Observer<String>{
            override fun onChanged(t: String?) {
                Snackbar.make((this as View), t!!, Snackbar.LENGTH_SHORT).show()
            }

        })
    }
}