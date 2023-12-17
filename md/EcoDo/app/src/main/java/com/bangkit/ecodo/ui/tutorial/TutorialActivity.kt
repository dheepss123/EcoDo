package com.bangkit.ecodo.ui.tutorial

import android.graphics.Color
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.ecodo.R
import com.bangkit.ecodo.data.model.RecomendationModel
import com.bangkit.ecodo.data.model.VideoModel
import com.bangkit.ecodo.databinding.ActivityRecomendationBinding
import com.bangkit.ecodo.databinding.ActivityTutorialBinding
import com.bangkit.ecodo.ui.adapter.AdapterCardRecomendation
import com.bangkit.ecodo.ui.adapter.AdapterItemTutorial

class TutorialActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTutorialBinding
    private lateinit var adapter: AdapterItemTutorial

    private var  listCard = ArrayList<VideoModel>()

    private val dataSumber = listOf("Data 1", "Data 2", "Data 3", "Data 4", "Data 5")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTutorialBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val layoutManager = LinearLayoutManager(this@TutorialActivity, LinearLayoutManager.VERTICAL, false)
        binding.rvList.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this@TutorialActivity, layoutManager.orientation)
        binding.rvList.addItemDecoration(itemDecoration)

        val search = binding.searchTutorial
        val searchIcon = search.findViewById<ImageView>(androidx.appcompat.R.id.search_mag_icon)
        val searchPlate = search.findViewById<EditText>(androidx.appcompat.R.id.search_src_text)

        searchPlate.setTextColor(Color.GREEN)
        searchIcon.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN)

        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                cariData(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                filterData(newText)
                return true
            }
        })

        val head = binding.tvFilter
        head.text = getString(R.string.tv_filter_s)

        listCard.addAll(listCards)
        adapter = AdapterItemTutorial(listCard)
        binding.rvList.adapter = adapter

    }

    private fun cariData(kataKunci: String) {
        val hasilPencarian = mutableListOf<String>()
        for (data in dataSumber) {
            if (data.contains(kataKunci, ignoreCase = true)) {
                hasilPencarian.add(data)
            }
        }
        println("Hasil Pencarian: $hasilPencarian")
    }

    private fun filterData(teksPencarian: String) {
        val dataTersaring = mutableListOf<String>()
        for (data in dataSumber) {
            if (data.contains(teksPencarian, ignoreCase = true)) {
                dataTersaring.add(data)
            }
        }
        println("Data Tersaring: $dataTersaring")
    }

    fun main() {
        cariData("Data")
        filterData("2")
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