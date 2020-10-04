package com.ryanrvldo.shoppinglisttestdrivendevelopment.data.remote

import com.ryanrvldo.shoppinglisttestdrivendevelopment.BuildConfig
import com.ryanrvldo.shoppinglisttestdrivendevelopment.data.remote.responses.ImageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayApi {

    @GET("api/")
    suspend fun searchForImage(
        @Query("q") searchQuery: String,
        @Query("key") apiKey: String = BuildConfig.PIXABAY_API_KEY
    ): Response<ImageResponse>
}