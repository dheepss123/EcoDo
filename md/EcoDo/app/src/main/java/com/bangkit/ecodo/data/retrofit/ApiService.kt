package com.bangkit.ecodo.data.retrofit

import com.bangkit.ecodo.data.retrofit.response.TrashPredictResponse
import com.bangkit.ecodo.data.retrofit.response.VideosResponse
import okhttp3.MultipartBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {

    @Multipart
    @POST("predict")
    suspend fun predictTrash(
        @Part file: MultipartBody.Part,
    ): TrashPredictResponse

    @GET("video")
    suspend fun getVideos(): VideosResponse
}