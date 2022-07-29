package ru.maksewsha.filmviewtt.data.model

import com.google.gson.annotations.SerializedName

data class ReviewData(
    @SerializedName("display_title")
    val displayTitle: String?,
    @SerializedName("mpaa_rating")
    val mpaaRating: String?,

    @SerializedName("critics_pick")
    val criticsPick: Long?,

    val byline: String?,
    val headline: String?,

    @SerializedName("summary_short")
    val summaryShort: String?,

    @SerializedName("publication_date")
    val publicationDate: String?,

    @SerializedName("opening_date")
    val openingDate: String?,

    @SerializedName("date_updated")
    val dateUpdated: String?,

    @SerializedName("link")
    val linkData: LinkData?,
    @SerializedName("multimedia")
    val multimediaData: MultimediaData?
)