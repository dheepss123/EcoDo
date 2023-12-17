package com.bangkit.ecodo.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.ecodo.R
import com.bangkit.ecodo.data.model.RecomendationModel
import com.bangkit.ecodo.data.model.VideoModel
import com.bangkit.ecodo.databinding.ItemTutorialBinding
import com.bangkit.ecodo.ui.recomendation.information.InformationActivity

class AdapterItemTutorial (private val listItem: ArrayList<VideoModel>) : RecyclerView.Adapter<AdapterItemTutorial.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_tutorial, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val video: VideoModel = listItem[position]
        holder.bind(video)
    }

    override fun getItemCount(): Int = listItem.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        private var tvName: TextView = itemView.findViewById(R.id.tvItem)

        fun bind(card: VideoModel) {
            imgPhoto.setImageResource(card.photo)
            tvName.text = card.title
        }
    }
}