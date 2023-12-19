package com.bangkit.ecodo.data.retrofit.response

import com.google.gson.annotations.SerializedName

data class TrashPredictResponse(

	@field:SerializedName("predicted_class")
	val predictedClass: String
)
