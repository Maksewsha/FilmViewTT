package ru.maksewsha.filmviewtt.data.model.entity

import ru.maksewsha.filmviewtt.data.model.ReviewDataList


sealed class ReviewDataEntity {
    class Success(val data: ReviewDataList): ReviewDataEntity()
    class Fail(val errorMessage: String): ReviewDataEntity()
}