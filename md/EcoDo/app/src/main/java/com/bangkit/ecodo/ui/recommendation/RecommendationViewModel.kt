package com.bangkit.ecodo.ui.recommendation

import androidx.lifecycle.ViewModel
import com.bangkit.ecodo.data.database.Trash
import com.bangkit.ecodo.data.repository.TrashRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecommendationViewModel @Inject constructor(
    private val trashRepository: TrashRepository,
) : ViewModel() {

    fun getTrashById(id: Long) = trashRepository.getTrash(id)

    fun deleteTrash(trash: Trash) = trashRepository.deleteTrash(trash)
}