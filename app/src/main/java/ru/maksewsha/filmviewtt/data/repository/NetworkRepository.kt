package ru.maksewsha.filmviewtt.data.repository

import ru.maksewsha.filmviewtt.BuildConfig
import ru.maksewsha.filmviewtt.data.model.ReviewDataList
import ru.maksewsha.filmviewtt.data.model.entity.ReviewDataEntity
import ru.maksewsha.filmviewtt.data.network.RetrofitService
import ru.maksewsha.filmviewtt.data.utils.DataMapper
import ru.maksewsha.filmviewtt.domain.model.entity.ReviewDomainEntity
import ru.maksewsha.filmviewtt.domain.repository.Repository

class NetworkRepository(private val retrofitService: RetrofitService): Repository {
    private val mapper = DataMapper()
    override fun fetchData(offset: Int): ReviewDomainEntity {
        val result = retrofitService.getReviewsAll(BuildConfig.API_KEY, offset)
        val response = result.execute()
        return if(response.isSuccessful){
            mapper.mapToEntity(ReviewDataEntity.Success(data = response.body() as ReviewDataList))
        } else {
            mapper.mapToEntity(ReviewDataEntity.Fail(response.message()))
        }
    }
}