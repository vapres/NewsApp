package com.route.test.utils.fragments.news

import android.content.ContentValues
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.route.test.api.ApiManager
import com.route.test.model.article.NewsItem
import com.route.test.model.article.NewsResponse
import com.route.test.model.source.SourceItem
import com.route.test.model.source.SourcesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel : ViewModel() {
    var newsList by mutableStateOf(listOf<NewsItem>())
    var newsFoundState by mutableStateOf(true)
    var loadingState by mutableStateOf(true)
    private var getNewsCall: Call<NewsResponse>? = null

    var messageState by mutableStateOf<String?>(null)
    var selectedTabIndex by mutableIntStateOf(0)
    var sourcesList by mutableStateOf(listOf<SourceItem>())
    private var getSourcesCall: Call<SourcesResponse>? = null


    fun getNews(sourceId: String) {
        getNewsCall = ApiManager
            .getNewsServices()
            .getNews(sourceId = sourceId)

        getNewsCall?.enqueue(object : Callback<NewsResponse> {
            override fun onResponse(
                call: Call<NewsResponse>,
                response: Response<NewsResponse>
            ) {

                loadingState = false
                if (response.isSuccessful) {
                    val news = response.body()?.articles
                    if (!news.isNullOrEmpty()) {
                        newsList = news
                        newsFoundState = true

                    } else
                        newsFoundState = false
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.e(ContentValues.TAG, "Failed to fetch data: ${t.message}")
                loadingState = false
            }
        })
    }

    fun getSources(category: String) {
        loadingState = true
        getSourcesCall = ApiManager
            .getNewsServices()
            .getSources(category = category)
        getSourcesCall?.enqueue(object : Callback<SourcesResponse> {
            override fun onResponse(
                call: Call<SourcesResponse>,
                response: Response<SourcesResponse>
            ) {
                if (response.isSuccessful) {
                    val sources = response.body()?.sources
                    sources?.let {
                        sourcesList = it
                        getNews(sourcesList[selectedTabIndex].id ?: "")
                    }
                } else
                    messageState = response.errorBody().toString()

                loadingState = false
            }

            override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {
                Log.e(ContentValues.TAG, "Failed to fetch data: ${t.message}")
            }

        })
    }

    override fun onCleared() {
        super.onCleared()
        getNewsCall?.cancel()
        getSourcesCall?.cancel()
    }
}
