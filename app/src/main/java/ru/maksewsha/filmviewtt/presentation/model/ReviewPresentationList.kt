package ru.maksewsha.filmviewtt.presentation.model


data class ReviewPresentationList(
    val status: String,
    val copyright: String,
    val has_more: String,
    val num_results: Int,
    val results: List<ReviewPresentation>
)