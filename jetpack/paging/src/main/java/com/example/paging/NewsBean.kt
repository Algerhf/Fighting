package com.example.paging

data class News(val hasMore: Boolean, val newsList: List<NewsBean>)

data class NewsBean(val id: Int, val content: String)
