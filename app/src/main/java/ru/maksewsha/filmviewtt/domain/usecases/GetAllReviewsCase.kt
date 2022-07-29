package ru.maksewsha.filmviewtt.domain.usecases

import ru.maksewsha.filmviewtt.domain.repository.Repository

class GetAllReviewsCase(private val repository: Repository) {
    fun execute(offset: Int) = repository.fetchData(offset)
}