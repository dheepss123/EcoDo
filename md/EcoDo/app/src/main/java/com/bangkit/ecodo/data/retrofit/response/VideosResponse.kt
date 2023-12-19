package com.bangkit.ecodo.data.retrofit.response

import com.bangkit.ecodo.data.model.VideoModel
import com.google.gson.annotations.SerializedName
import java.util.regex.Pattern

data class VideosResponse(

    @field:SerializedName("video")
    val item: Videos,
)

data class Videos(

    @field:SerializedName("data")
    val data: List<Video>,
)

data class Video(

    @field:SerializedName("video")
    val link: String,
)

fun Video.toVideoModel() = VideoModel(
    photo = getThumbnail(link),
    title = link
)

fun getThumbnail(url: String): String {
    val pattern = "(?<=youtu.be/|youtube.com/watch\\?v=|youtube.com/shorts/)([^\"&?/\\s]{11})"
    val compiledPattern = Pattern.compile(pattern)
    val matcher = compiledPattern.matcher(url)
    return if (matcher.find()) {
        "https://img.youtube.com/vi/${matcher.group()}/hqdefault.jpg"
    } else {
        "https://www.alqarn.dj/placeholder.png"
    }
}
