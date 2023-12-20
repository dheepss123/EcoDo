package com.bangkit.ecodo.ui.recommendation.video

import androidx.lifecycle.ViewModel
import com.bangkit.ecodo.data.repository.TrashRepository
import com.bangkit.ecodo.data.repository.VideoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VideoRecomendationViewModel @Inject constructor(
    private val videorepository: VideoRepository,
) : ViewModel() {

    fun getVideos() = videorepository.getVideos()
}