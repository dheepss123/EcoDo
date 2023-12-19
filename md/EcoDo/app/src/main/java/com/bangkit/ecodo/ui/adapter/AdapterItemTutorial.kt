package com.bangkit.ecodo.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.ecodo.R
import com.bangkit.ecodo.data.model.VideoModel

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