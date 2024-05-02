package com.route.test.api

import com.route.test.model.article.NewsResponse
import com.route.test.model.source.SourcesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsServices {
    @GET("top-headlines/sources")
    fun getSources(
        @Query("apiKey") apiKey: String= Constants.getApiKey()?:"",
        @Query("category") category: String
    ): Call<SourcesResponse>

    @GET("/v2/everything")
    fun getNews(
        @Query("apiKey") apiKey: String= Constants.getApiKey()?:"",
        @Query("sources") sourceId: String
    ): Call<NewsResponse>

    @GET("/v2/everything")
    fun getArticle(
        @Query("apiKey") apiKey: String = Constants.getApiKey()?:"",
        @Query("q") title: String,
        @Query("searchIn") field: String = "title"
    ): Call<NewsResponse>

    @GET("/v2/everything")
    fun getSearchedArticles(
        @Query("apiKey") apiKey: String= Constants.getApiKey()?:"",
        @Query("q") searchQuery: String,
    ): Call<NewsResponse>
}