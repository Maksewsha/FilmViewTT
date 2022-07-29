package ru.maksewsha.filmviewtt.domain.model.entity

import ru.maksewsha.filmviewtt.domain.model.ReviewDomainList


sealed class ReviewDomainEntity {
    class Success(val data: ReviewDomainList): ReviewDomainEntity()
    class Fail(val errorMessage: String): ReviewDomainEntity()
}