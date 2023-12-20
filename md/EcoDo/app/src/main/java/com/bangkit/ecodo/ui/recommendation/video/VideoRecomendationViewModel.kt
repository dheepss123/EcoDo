package com.bangkit.ecodo.ui.recommendation.video

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.ecodo.data.model.VideoModel
import com.bangkit.ecodo.data.repository.VideoRepository
import com.bangkit.ecodo.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VideoRecomendationViewModel @Inject constructor(
    private val videorepository: VideoRepository,
) : ViewModel() {

    private val _videoList = MutableLiveData<Resource<List<VideoModel>>>()
    val videoList: LiveData<Resource<List<VideoModel>>> get() = _videoList

    fun getVideos() {
        viewModelScope.launch {
            _videoList.value = Resource.Loading
            try {
                val response = videorepository.getVideos()
                _videoList.value = response.value
            } catch (e: Exception) {
                _videoList.value = Resource.Error(e.localizedMessage?.toString() ?: "Unknown Error")
            }
        }
    }
}