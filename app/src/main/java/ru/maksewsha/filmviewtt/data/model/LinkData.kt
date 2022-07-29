package ru.maksewsha.filmviewtt.data.model

import com.google.gson.annotations.SerializedName

data class LinkData (
    val type: String?,
    val url: String?,

    @SerializedName("suggested_link_text")
    val suggestedLinkText: String?
)