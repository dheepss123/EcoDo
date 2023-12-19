package com.bangkit.ecodo.data.repository

import com.bangkit.ecodo.data.Article
import com.bangkit.ecodo.data.ArticleDataSource

class ArticleRepository {
    fun getArticles(): List<Article> = ArticleDataSource.articles
}