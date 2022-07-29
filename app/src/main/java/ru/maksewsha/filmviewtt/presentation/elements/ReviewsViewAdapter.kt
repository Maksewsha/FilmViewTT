package ru.maksewsha.filmviewtt.presentation.elements

import android.content.Context
import android.opengl.Visibility
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.maksewsha.filmviewtt.R
import ru.maksewsha.filmviewtt.presentation.model.ReviewPresentation


class ReviewsViewAdapter(private val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    enum class ViewType(val value: Int) {
        ITEM(1), LOADING(2)
    }

    private val reviewList = ArrayList<ReviewPresentation>()
    private var isLoadingAdded = false

    fun setReviewList(reviews: List<ReviewPresentation>) {
        reviewList.clear()
        reviewList.addAll(reviews)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var viewHolder: RecyclerView.ViewHolder? = null
        when (viewType) {
            ViewType.ITEM.value -> {
                val reviewItem =
                    LayoutInflater.from(context).inflate(R.layout.review_item, parent, false)
                viewHolder = ReviewViewHolder(reviewItem)
            }
            ViewType.LOADING.value -> {
                val loadingItem =
                    LayoutInflater.from(context).inflate(R.layout.loading_item, parent, false)
                viewHolder = LoadingViewHolder(loadingItem)
            }
        }
        return viewHolder!!
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            ViewType.ITEM.value -> {
                (holder as ReviewViewHolder).reviewTitle.text = reviewList[position].displayTitle
                holder.reviewDescription.text =
                    reviewList[position].summaryShort

                Glide
                    .with(context)
                    .load(reviewList[position].multimediaData?.src)
                    .override(
                        reviewList[position].multimediaData?.width?.toInt()?.times(2) ?: 250,
                        reviewList[position].multimediaData?.height?.toInt()?.times(2)  ?: 250
                    )
                    .into(holder.reviewPoster)
            }
            ViewType.LOADING.value -> {
                (holder as LoadingViewHolder).progressBar.visibility = View.VISIBLE
            }
        }
    }

    override fun getItemCount() = reviewList.size

    override fun getItemViewType(position: Int): Int {
        return if (position == reviewList.size - 1 && isLoadingAdded) ViewType.LOADING.value else ViewType.ITEM.value
    }

    fun addLoadingFooter() {
        isLoadingAdded = true
        add(ReviewPresentation())
    }

    fun removeLoadingFooter() {
        isLoadingAdded = false
        val position = reviewList.size - 1
        val result = getItem(position)
        reviewList.removeAt(position)
        notifyItemRemoved(position)
    }

    fun add(review: ReviewPresentation) {
        reviewList.add(review)
        notifyItemInserted(reviewList.size - 1)
    }

    fun addAll(reviewList: List<ReviewPresentation>) {
        for (result in reviewList) {
            add(result)
        }
    }

    private fun getItem(position: Int): ReviewPresentation {
        return reviewList[position]
    }


    class ReviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val reviewTitle: TextView
        val reviewPoster: ImageView
        val reviewDescription: TextView

        init {
            reviewTitle = itemView.findViewById(R.id.review_title)
            reviewPoster = itemView.findViewById(R.id.review_poster) as ImageView
            reviewDescription = itemView.findViewById(R.id.review_description)
        }
    }

    class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val progressBar: ProgressBar

        init {
            progressBar = itemView.findViewById(R.id.loading_progress)
        }
    }
}