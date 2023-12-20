package com.bangkit.ecodo.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bangkit.ecodo.data.Article
import com.bangkit.ecodo.data.ArticleDataSource

class ArticleRepository {
    private val articlesLiveData: MutableLiveData<List<Article>> = MutableLiveData()

    fun getArticles(): LiveData<List<Article>> {
        articlesLiveData.value = ArticleDataSource.articles
        return articlesLiveData
    }
}