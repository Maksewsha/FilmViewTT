package ru.maksewsha.filmviewtt.domain.model


data class ReviewDomain(
    val displayTitle: String?,
    val mpaaRating: String?,

    val criticsPick: Long?,

    val byline: String?,
    val headline: String?,

    val summaryShort: String?,

    val publicationDate: String?,

    val openingDate: String?,

    val dateUpdated: String?,

    val linkData: LinkDomain?,
    val multimediaData: MultimediaDomain?
)