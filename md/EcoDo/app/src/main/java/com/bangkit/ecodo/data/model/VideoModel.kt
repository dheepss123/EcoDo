package com.bangkit.ecodo.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class VideoModel(
    var photo: String,
    var title: String,
) : Parcelable
