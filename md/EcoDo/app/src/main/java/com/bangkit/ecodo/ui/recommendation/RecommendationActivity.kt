package com.bangkit.ecodo.ui.recommendation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.ecodo.R
import com.bangkit.ecodo.data.model.RecommendationModel
import com.bangkit.ecodo.databinding.ActivityRecomendationBinding
import com.bangkit.ecodo.ui.adapter.AdapterCardRecommendation
import com.bangkit.ecodo.util.toImageBitmap
import com.google.android.material.appbar.MaterialToolbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecommendationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecomendationBinding
    private var listCard = ArrayList<RecommendationModel>()
    private val viewModel: RecommendationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecomendationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val materialToolBar: MaterialToolbar = binding.topAppBar

        setSupportActionBar(materialToolBar)

        val trashId = intent.getLongExtra(TRASH_ID, -1L)

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvList.layoutManager = layoutManager

        listCard.addAll(listCards)
        val adapter = AdapterCardRecommendation(listCard)
        binding.rvList.adapter = adapter

        if (trashId != -1L) {
            viewModel.getTrashById(trashId).observe(this) { trash ->
                binding.tvJenis.text = trash.predictedClass
                binding.tvHead.text = getString(R.string.recommendation_head, trash.predictedClass)
                binding.exampleImg.setImageBitmap(trash.imageData.toImageBitmap())
            }
        }


    }

    private val listCards: ArrayList<RecommendationModel>
        get() {
            val dataName = resources.getStringArray(R.array.data_head)
            val dataDescription = resources.getStringArray(R.array.data_desc)
            val dataPhoto = resources.obtainTypedArray(R.array.data_img)
            val listCard = ArrayList<RecommendationModel>()
            for (i in dataName.indices) {
                val imgId = dataPhoto.getResourceId(i, 0)
                val card = RecommendationModel(imgId, dataName[i], dataDescription[i])
                listCard.add(card)
            }
            dataPhoto.recycle()
            return listCard
        }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_recommendation, menu)
        return true
    }

    companion object {
        const val TRASH_ID = "trash_id"
    }
}