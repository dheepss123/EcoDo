package com.bangkit.ecodo.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecommendationModel(
    var imgItem: Int,
    var title: String,
    var desc: String,
) : Parcelable
