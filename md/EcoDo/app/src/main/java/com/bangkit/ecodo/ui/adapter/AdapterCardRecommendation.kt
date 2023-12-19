package com.bangkit.ecodo.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.ecodo.data.model.RecommendationModel
import com.bangkit.ecodo.databinding.CardItemRecomendationBinding
import com.bangkit.ecodo.ui.recommendation.information.InformationActivity
import com.bangkit.ecodo.ui.recommendation.video.VideoRecomendationActivity

class AdapterCardRecommendation(private val listCard: ArrayList<RecommendationModel>) :
    RecyclerView.Adapter<AdapterCardRecommendation.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val bindingItem =
            CardItemRecomendationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(bindingItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val card = listCard[position]
        holder.bind(card)
    }

    override fun getItemCount(): Int {
        return listCard.size
    }

    inner class ViewHolder(private val bindingItem: CardItemRecomendationBinding) :
        RecyclerView.ViewHolder(bindingItem.root) {

        fun bind(card: RecommendationModel) {
            bindingItem.reduceImg.setImageResource(card.imgItem)
            bindingItem.tvReduce.text = card.title
            bindingItem.reduceDesc.text = card.desc

            bindingItem.btnVideo.setOnClickListener {
                Toast.makeText(bindingItem.root.context, "Button Video Clicked", Toast.LENGTH_SHORT)
                    .show()
                val intent =
                    Intent(bindingItem.root.context, VideoRecomendationActivity::class.java)
                bindingItem.root.context.startActivity(intent)
            }
            bindingItem.btnCekDesc.setOnClickListener {
                Toast.makeText(bindingItem.root.context, "Button Desc Clicked", Toast.LENGTH_SHORT)
                    .show()
                val intent = Intent(bindingItem.root.context, InformationActivity::class.java)
                bindingItem.root.context.startActivity(intent)
            }
        }
    }
}