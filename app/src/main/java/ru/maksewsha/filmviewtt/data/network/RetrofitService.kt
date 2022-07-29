package ru.maksewsha.filmviewtt.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import ru.maksewsha.filmviewtt.data.model.ReviewDataList
import ru.maksewsha.filmviewtt.presentation.model.ReviewPresentationList


interface RetrofitService {

    @GET("reviews/all.json")
    fun getReviewsAll(
        @Query("api-key") apiKey: String,
        @Query("offset") offset: Int
    ): Call<ReviewDataList>

    companion object {
        private var retrofit: RetrofitService? = null
        private const val BASE_URL = "https://api.nytimes.com/svc/movies/v2/"

        fun getInstance(): RetrofitService {
            if (retrofit == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(
                        OkHttpClient.Builder()
                            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                            .build()
                    )
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                this.retrofit = retrofit.create(RetrofitService::class.java)
            }
            return retrofit!!
        }
    }
}