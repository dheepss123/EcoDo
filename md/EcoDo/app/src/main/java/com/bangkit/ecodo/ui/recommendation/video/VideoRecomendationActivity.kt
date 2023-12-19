package com.bangkit.ecodo.ui.recommendation.video

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.ecodo.R
import com.bangkit.ecodo.data.model.VideoModel
import com.bangkit.ecodo.data.retrofit.response.getThumbnail
import com.bangkit.ecodo.databinding.ActivityVideoRecomendationBinding
import com.bangkit.ecodo.ui.adapter.AdapterItemTutorial

class VideoRecomendationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVideoRecomendationBinding
    private lateinit var adapter: AdapterItemTutorial

    private var listCard = ArrayList<VideoModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoRecomendationBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val layoutManager = LinearLayoutManager(
            this@VideoRecomendationActivity,
            LinearLayoutManager.VERTICAL,
            false
        )
        binding.rvList.layoutManager = layoutManager
        val itemDecoration =
            DividerItemDecoration(this@VideoRecomendationActivity, layoutManager.orientation)
        binding.rvList.addItemDecoration(itemDecoration)

        listCard.addAll(listCards)
        adapter = AdapterItemTutorial(listCard)
        binding.rvList.adapter = adapter

    }

    private val listCards: ArrayList<VideoModel>
        get() {
            val dataName = resources.getStringArray(R.array.data_head)
            val dataPhoto = resources.obtainTypedArray(R.array.data_img)
            val listCard = ArrayList<VideoModel>()
            for (i in dataName.indices) {
                val card = VideoModel(
                    "https://play-lh.googleusercontent.com/j_u0NsRJc_PlGGY5APSV5jDCrO6OeBmKSa2Jpgx7qq4Zy0S2WyI8wRiB0PD0YpNpO7U7",
                    dataName[i]
                )
                listCard.add(card)
            }
            dataPhoto.recycle()
            return listCard
        }
}