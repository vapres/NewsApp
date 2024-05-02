package com.route.test.utils.fragments.newsdetails

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.route.test.api.ApiManager
import com.route.test.model.article.NewsItem
import com.route.test.model.article.NewsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsDetailsViewModel : ViewModel() {

    var newsItem by mutableStateOf<NewsItem?>(null)
    private var getNewsItemCall: Call<NewsResponse>? = null

    fun getNewsItem(title: String) {
        getNewsItemCall =
            ApiManager
                .getNewsServices()
                .getArticle(title = title)

        getNewsItemCall?.enqueue(object : Callback<NewsResponse> {
            override fun onResponse(
                call: Call<NewsResponse>,
                response: Response<NewsResponse>
            ) {
                if (response.isSuccessful) {
                    val item = response.body()?.articles?.get(0)
                    if (item != null)
                        newsItem = item
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.e("getNewsItem on Failure", t.localizedMessage ?: "")
            }

        })

    }

    override fun onCleared() {
        super.onCleared()
        getNewsItemCall?.cancel()
    }

}