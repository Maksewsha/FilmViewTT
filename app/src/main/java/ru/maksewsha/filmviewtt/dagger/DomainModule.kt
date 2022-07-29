package ru.maksewsha.filmviewtt.dagger

import dagger.Module
import dagger.Provides
import ru.maksewsha.filmviewtt.data.repository.NetworkRepository
import ru.maksewsha.filmviewtt.domain.usecases.GetAllReviewsCase
import javax.inject.Inject
import javax.inject.Singleton

@Module
object DomainModule {
    @Singleton
    @Provides
    fun provideGetAllUseCase(repository: NetworkRepository) = GetAllReviewsCase(repository)
}