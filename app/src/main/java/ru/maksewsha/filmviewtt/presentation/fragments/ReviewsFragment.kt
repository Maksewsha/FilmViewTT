package ru.maksewsha.filmviewtt.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import ru.maksewsha.filmviewtt.R
import ru.maksewsha.filmviewtt.presentation.elements.PaginateScrollListener
import ru.maksewsha.filmviewtt.presentation.elements.ReviewsViewAdapter
import ru.maksewsha.filmviewtt.presentation.model.ReviewPresentation
import ru.maksewsha.filmviewtt.presentation.viewmodels.ReviewsViewModel

class ReviewsFragment : Fragment(R.layout.reviews_fragment) {

    private lateinit var reviewsRecycler: RecyclerView

    private val reviewsViewModel by activityViewModels<ReviewsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        reviewsRecycler = view.findViewById(R.id.recycler_view)

        reviewsRecycler.adapter = ReviewsViewAdapter(requireContext())
        reviewsRecycler.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        reviewsRecycler.addOnScrollListener(object :
            PaginateScrollListener(reviewsRecycler.layoutManager as LinearLayoutManager) {
            override fun loadMoreItems() {
                reviewsViewModel.loadNextPage()
                reviewsViewModel.toggleLoading()
            }

            override fun isLastPage(): Boolean {
                return reviewsViewModel.isLastPage()
            }

            override fun isLoading(): Boolean {
                return reviewsViewModel.isLoading()
            }
        })

        reviewsViewModel.reviews.observe(
            viewLifecycleOwner,
            object : Observer<List<ReviewPresentation>> {
                override fun onChanged(t: List<ReviewPresentation>?) {
                    reviewsViewModel.toggleLoading()
                    (reviewsRecycler.adapter as ReviewsViewAdapter).addAll(t!!)
                    (reviewsRecycler.adapter as ReviewsViewAdapter).removeLoadingFooter()
                    if (reviewsViewModel.isLastPage()) (reviewsRecycler.adapter as ReviewsViewAdapter).addLoadingFooter()
                }
            })

        reviewsViewModel.errorMessage.observe(viewLifecycleOwner, object : Observer<String>{
            override fun onChanged(t: String?) {
                Snackbar.make(view, t!!, Snackbar.LENGTH_SHORT).show()
            }
        })
    }
}