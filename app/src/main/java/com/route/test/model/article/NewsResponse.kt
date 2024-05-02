package com.route.test.model.article

data class NewsResponse(
    val articles: List<NewsItem>,
    val status: String,
    val totalResults: Int
)