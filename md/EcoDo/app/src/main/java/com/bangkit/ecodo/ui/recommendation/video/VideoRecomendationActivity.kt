package com.bangkit.ecodo.ui.recommendation.video

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.ecodo.R
import com.bangkit.ecodo.data.model.VideoModel
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
                val imgId = dataPhoto.getResourceId(i, 0)
                val card = VideoModel(imgId, dataName[i])
                listCard.add(card)
            }
            dataPhoto.recycle()
            return listCard
        }
}