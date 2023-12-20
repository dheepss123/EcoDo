package com.bangkit.ecodo.ui.recommendation.information

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bangkit.ecodo.data.Article
import com.bangkit.ecodo.data.repository.ArticleRepository
import com.bangkit.ecodo.data.repository.VideoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class InformationViewModel @Inject constructor(
    private val articlerepository: ArticleRepository,
) : ViewModel() {

    fun getArticle(): LiveData<List<Article>> {
        return articlerepository.getArticles()
    }
}