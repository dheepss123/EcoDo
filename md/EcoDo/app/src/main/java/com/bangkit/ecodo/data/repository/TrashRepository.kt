package com.bangkit.ecodo.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.bangkit.ecodo.data.database.TrashDao
import com.bangkit.ecodo.data.database.Trash
import com.bangkit.ecodo.data.retrofit.ApiService
import com.bangkit.ecodo.util.Resource
import com.bangkit.ecodo.util.reduceFileImage
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class TrashRepository(
    private val trashDao: TrashDao,
    private val apiService: ApiService,
) {

    fun predictTrash(image: File): LiveData<Resource<Long>> = liveData {
        try {
            emit(Resource.Loading)
            val imageFile = image.reduceFileImage()
            val requestImageFile = imageFile.asRequestBody("image/jpeg".toMediaType())
            val multipartBody = MultipartBody.Part.createFormData(
                "file",
                imageFile.name,
                requestImageFile
            )
            val response = apiService.predictTrash(multipartBody)
            val trashId = trashDao.insert(
                Trash(
                    predictedClass = response.predictedClass.data.nama,
                    videoUrl = response.predictedClass.data.video,
                    articleUrl = response.predictedClass.data.artikel,
                    imageData = imageFile.readBytes(),
                )
            )
            emit(Resource.Success(trashId))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage?.toString() ?: "Unknown Error"))
        }
    }

    fun getTrashHistory() = trashDao.getAll()

    fun getTrash(id: Long) = trashDao.getTrashById(id)
}