package ru.maksewsha.filmviewtt.dagger

import dagger.Module
import dagger.Provides
import ru.maksewsha.filmviewtt.data.network.RetrofitService
import ru.maksewsha.filmviewtt.data.repository.NetworkRepository

@Module
object DataModule{

    @Provides
    fun provideRetrofit(): RetrofitService {
        return RetrofitService.getInstance()
    }

    @Provides
    fun provideNetworkRepository(retrofitService: RetrofitService): NetworkRepository{
        return NetworkRepository(retrofitService)
    }
}