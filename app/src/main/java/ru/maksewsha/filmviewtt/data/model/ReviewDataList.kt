package ru.maksewsha.filmviewtt.data.model


data class ReviewDataList(
    val status: String,
    val copyright: String,
    val has_more: String,
    val num_results: Int,
    val results: List<ReviewData>
)