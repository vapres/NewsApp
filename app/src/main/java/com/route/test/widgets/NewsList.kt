package com.route.test.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import com.route.test.model.article.NewsItem
import com.route.test.widgets.NewsCard

@Composable
fun NewsList(
    newsList: List<NewsItem>,
    newsFoundState: Boolean,
    loadingState: Boolean,
    onNewsClick: (String) -> Unit
) {
    if (loadingState)

        ProgressIndicator()
    else {

        if (newsFoundState) {

            LazyColumn(verticalArrangement = Arrangement.SpaceEvenly) {
                items(newsList.size) { position ->
                    NewsCard(newsItem = newsList[position]) { title ->
                        onNewsClick(title)
                    }
                }
            }
        } else {

            ArticlesNotFound()
        }
    }
}


