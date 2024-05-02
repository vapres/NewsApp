package com.route.test.api

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiManager {

    private val logging = HttpLoggingInterceptor { message -> Log.e("api ->", message) }
        .setLevel(HttpLoggingInterceptor.Level.BODY)
    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://newsapi.org/v2/  ")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getNewsServices(): NewsServices {
        return retrofit.create(NewsServices::class.java)
    }
}