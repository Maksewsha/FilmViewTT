package ru.maksewsha.filmviewtt.domain.model


data class ReviewDomainList(
    val status: String,
    val copyright: String,
    val has_more: String,
    val num_results: Int,
    val results: List<ReviewDomain>
)