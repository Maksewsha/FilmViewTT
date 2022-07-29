package ru.maksewsha.filmviewtt.domain.utils

interface EntityMapper<T, F> {
    fun mapToEntity(from: F): T
    fun mapFromEntity(entity: T): F
}