package ru.maksewsha.filmviewtt.presentation.model.entity

import ru.maksewsha.filmviewtt.presentation.model.ReviewPresentationList

sealed class ReviewPresentationEntity {
    class Success(val data: ReviewPresentationList): ReviewPresentationEntity()
    class Fail(val errorMessage: String): ReviewPresentationEntity()
}