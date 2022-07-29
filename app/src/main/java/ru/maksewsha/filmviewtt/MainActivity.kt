package ru.maksewsha.filmviewtt

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBar
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


        val actionBar: ActionBar? = supportActionBar
        if (actionBar != null) actionBar.hide()
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LOW_PROFILE
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)

        reviewsViewModel.toggleLoading()
        reviewsViewModel.loadNextPage()

        reviewsViewModel.reviews.observe(this, object: Observer<List<ReviewPresentation>>{
            override fun onChanged(t: List<ReviewPresentation>?) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container_view, reviewsFragment, ReviewsFragment::class.java.canonicalName)
                    .commit()

                if (actionBar != null) actionBar.show()
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
            }
        })

        reviewsViewModel.errorMessage.observe(this, object: Observer<String>{
            override fun onChanged(t: String?) {
                Snackbar.make((this as View), t!!, Snackbar.LENGTH_SHORT).show()
            }

        })
    }
}