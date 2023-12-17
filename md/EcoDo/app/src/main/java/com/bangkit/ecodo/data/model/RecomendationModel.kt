package com.bangkit.ecodo.data.model

import android.media.Image
import android.os.Parcelable
import android.widget.ImageView
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecomendationModel(
    var imgItem: Int,
    var title: String,
    var desc: String,
) : Parcelable
