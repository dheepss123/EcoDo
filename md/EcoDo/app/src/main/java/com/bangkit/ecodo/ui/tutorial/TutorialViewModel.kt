package com.bangkit.ecodo.ui.tutorial

import androidx.lifecycle.ViewModel
import com.bangkit.ecodo.data.repository.VideoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TutorialViewModel @Inject constructor(
    private val videoRepository: VideoRepository,
) : ViewModel() {

    fun getVideos() = videoRepository.getVideos()
}