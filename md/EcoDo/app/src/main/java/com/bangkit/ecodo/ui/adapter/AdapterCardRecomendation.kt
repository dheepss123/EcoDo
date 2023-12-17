package com.bangkit.ecodo.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.ecodo.R
import com.bangkit.ecodo.data.model.RecomendationModel
import com.bangkit.ecodo.databinding.CardItemRecomendationBinding
import com.bangkit.ecodo.ui.recomendation.information.InformationActivity
import com.bangkit.ecodo.ui.recomendation.video.VideoRecomendationActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class AdapterCardRecomendation(private val listCard: ArrayList<RecomendationModel>) : RecyclerView.Adapter<AdapterCardRecomendation.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val bindingItem = CardItemRecomendationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(bindingItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val card = listCard[position]
        holder.bind(card)
    }

    override fun getItemCount(): Int {
        return listCard.size
    }

    inner class ViewHolder(private val bindingItem: CardItemRecomendationBinding) : RecyclerView.ViewHolder(bindingItem.root) {

        fun bind(card: RecomendationModel) {
            bindingItem.reduceImg .setImageResource(card.imgItem)
            bindingItem.tvReduce .text = card.title
            bindingItem.reduceDesc .text = card.desc

            bindingItem.btnVideo.setOnClickListener {
                Toast.makeText(bindingItem.root.context, "Button Video Clicked", Toast.LENGTH_SHORT).show()
                val intent = Intent(bindingItem.root.context, VideoRecomendationActivity::class.java)
                bindingItem.root.context.startActivity(intent)
            }
            bindingItem.btnCekDesc.setOnClickListener {
                Toast.makeText(bindingItem.root.context, "Button Desc Clicked", Toast.LENGTH_SHORT).show()
                val intent = Intent(bindingItem.root.context, InformationActivity::class.java)
                bindingItem.root.context.startActivity(intent)
            }
        }
    }
}