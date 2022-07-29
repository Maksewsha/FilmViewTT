package ru.maksewsha.filmviewtt.presentation.model


data class ReviewPresentation(
    val displayTitle: String?,
    val mpaaRating: String?,

    val criticsPick: Long?,

    val byline: String?,
    val headline: String?,

    val summaryShort: String?,

    val publicationDate: String?,

    val openingDate: String?,

    val dateUpdated: String?,

    val linkData: LinkPresentation?,
    val multimediaData: MultimediaPresentation?
) {
    constructor() : this("", "", 0, "", "", "", "", "", "", null, null)
}