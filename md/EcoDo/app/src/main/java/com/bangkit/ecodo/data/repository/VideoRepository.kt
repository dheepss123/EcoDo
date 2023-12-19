package com.bangkit.ecodo.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.bangkit.ecodo.data.model.VideoModel
import com.bangkit.ecodo.data.retrofit.ApiService
import com.bangkit.ecodo.data.retrofit.response.Video
import com.bangkit.ecodo.data.retrofit.response.Videos
import com.bangkit.ecodo.data.retrofit.response.VideosResponse
import com.bangkit.ecodo.data.retrofit.response.toVideoModel
import com.bangkit.ecodo.util.Resource

class VideoRepository(
    private val apiService: ApiService,
) {
    fun getVideos(): LiveData<Resource<List<VideoModel>>> = liveData {
        try {
            emit(Resource.Loading)
//            val response = apiService.getVideos()
            val response =
                VideosResponse(
                    Videos(
                        listOf(
                            Video("https://youtu.be/c9l_zzD2vPc?feature=shared"),
                            Video("https://youtu.be/c9l_zzD2vPc?feature=shared"),
                            Video("https://youtu.be/c9l_zzD2vPc?feature=shared"),
                        )
                    )
                )
//            emit(Resource.Success(response.item.data.map { it.toVideoModel() }))
            emit(Resource.Success(response.item.data.map { it.toVideoModel() }))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage?.toString() ?: "Unknown Error"))
        }
    }
}