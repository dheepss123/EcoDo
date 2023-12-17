package com.bangkit.ecodo.ui.recomendation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.ecodo.R
import com.bangkit.ecodo.data.model.RecomendationModel
import com.bangkit.ecodo.databinding.ActivityRecomendationBinding
import com.bangkit.ecodo.databinding.CardItemRecomendationBinding
import com.bangkit.ecodo.ui.adapter.AdapterCardRecomendation
import com.bangkit.ecodo.ui.recomendation.information.InformationActivity
import com.bangkit.ecodo.ui.recomendation.video.VideoRecomendationActivity

class RecomendationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecomendationBinding
    private lateinit var adapter: AdapterCardRecomendation

    private lateinit var bindingItem: CardItemRecomendationBinding
    private var  listCard = ArrayList<RecomendationModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecomendationBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val layoutManager = LinearLayoutManager(this@RecomendationActivity, LinearLayoutManager.HORIZONTAL, false)
        binding.rvList.layoutManager = layoutManager

        val tittle = binding.tvTitleJenis
        tittle.text = getString(R.string.title_jenis)
        val jenis = binding.tvJenis
        jenis.text = getString(R.string.jenis)
        val head = binding.tvHead
        head.text = getString(R.string.head)

        listCard.addAll(listCards)
        adapter = AdapterCardRecomendation(listCard)
        binding.rvList.adapter = adapter

    }

    private val listCards: ArrayList<RecomendationModel>
        get() {
            val dataName = resources.getStringArray(R.array.data_head)
            val dataDescription = resources.getStringArray(R.array.data_desc)
            val dataPhoto = resources.obtainTypedArray(R.array.data_img)
            val listCard = ArrayList<RecomendationModel>()
            for (i in dataName.indices) {
                val imgId = dataPhoto.getResourceId(i, 0)
                val card = RecomendationModel(imgId, dataName[i], dataDescription[i])
                listCard.add(card)
            }
            dataPhoto.recycle()
            return listCard
        }
}