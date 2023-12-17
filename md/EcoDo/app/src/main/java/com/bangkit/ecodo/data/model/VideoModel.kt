package com.bangkit.ecodo.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class VideoModel (
    var photo: Int,
    var title: String,
): Parcelable


