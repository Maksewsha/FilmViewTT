package ru.maksewsha.filmviewtt.domain.repository

import ru.maksewsha.filmviewtt.domain.model.entity.ReviewDomainEntity

interface Repository {
    fun fetchData(offset: Int): ReviewDomainEntity
}