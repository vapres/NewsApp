package com.route.test.utils.fragments.search

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.focus.FocusRequester
import androidx.lifecycle.ViewModel
import com.route.test.api.ApiManager
import com.route.test.model.article.NewsItem
import com.route.test.model.article.NewsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel : ViewModel() {

    var newsList by mutableStateOf(listOf<NewsItem>())
    var searchQuery by mutableStateOf("")
    var newsFoundState by mutableStateOf(true)
    var loadingState by mutableStateOf(false)
    var isFocused by mutableStateOf(false)
    val focusRequester = FocusRequester()
    var messageState by mutableStateOf<String?>(null)
    private var getSearchedArticlesCall: Call<NewsResponse>? = null

    fun getSearchedArticles() {
        loadingState = true

        getSearchedArticlesCall =
            ApiManager
                .getNewsServices()
                .getSearchedArticles(searchQuery = searchQuery)
        getSearchedArticlesCall?.enqueue(object : Callback<NewsResponse> {
            override fun onResponse(
                call: Call<NewsResponse>,
                response: Response<NewsResponse>
            ) {
                if (response.isSuccessful) {
                    val news = response.body()?.articles
                    if (!news.isNullOrEmpty()) {
                        newsList = news
                        newsFoundState = true
                    } else {
                        newsFoundState = false
                    }
                } else
                    messageState = response.errorBody().toString()

                loadingState = false
                searchQuery = ""
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                messageState = t.localizedMessage?:""
                loadingState = false
                searchQuery = ""
            }

        })
    }

    override fun onCleared() {
        super.onCleared()
        getSearchedArticlesCall?.cancel()
    }
}