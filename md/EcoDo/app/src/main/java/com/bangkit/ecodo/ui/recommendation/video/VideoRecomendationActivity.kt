package com.bangkit.ecodo.ui.recommendation.video

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.ecodo.databinding.ActivityVideoRecomendationBinding
import com.bangkit.ecodo.ui.adapter.AdapterItemTutorial
import com.bangkit.ecodo.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VideoRecomendationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVideoRecomendationBinding
    private lateinit var adapter: AdapterItemTutorial
    private val viewModel: VideoRecomendationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoRecomendationBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        adapter = AdapterItemTutorial(mutableListOf())
        binding.rvList.adapter = adapter

        setupRecyclerView()

        viewModel.getVideos().observe(this) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    showLoading(true)
                }

                is Resource.Success -> {
                    showLoading(false)
                    val listVideo = resource.data
                    adapter.updateList(listVideo)
                }

                is Resource.Error -> {
                    showLoading(false)
                    val errorMessage = resource.error
                    Log.d("TAG", errorMessage)
                }
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(
            this@VideoRecomendationActivity,
            LinearLayoutManager.VERTICAL,
            false
        )
        binding.rvList.layoutManager = layoutManager
        val itemDecoration =
            DividerItemDecoration(this@VideoRecomendationActivity, layoutManager.orientation)
        binding.rvList.addItemDecoration(itemDecoration)
    }
}