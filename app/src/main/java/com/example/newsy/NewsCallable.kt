package com.example.newsy

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsCallable {
    @GET("/v2/top-headlines")
    fun getNews(
        @Query("country") country: String = "us",
        @Query("category") category: String = "health",
        @Query("apiKey") apiKey: String = "1bc9817a278943169d41ef5f68ac9780",
        @Query("pageSize") pageSize: Int = 30
    ): Call<News>
}