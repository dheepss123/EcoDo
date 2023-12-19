package com.bangkit.ecodo.ui.scan_trash

import android.util.Log
import androidx.lifecycle.ViewModel
import com.bangkit.ecodo.data.repository.TrashRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ScanTrashViewModel @Inject constructor(
    private val trashRepository: TrashRepository,
) : ViewModel() {
    fun uploadImage(imageFile: File) = trashRepository.predictTrash(imageFile)
}