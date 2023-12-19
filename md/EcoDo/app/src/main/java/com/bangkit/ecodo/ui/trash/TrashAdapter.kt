package com.bangkit.ecodo.ui.trash

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.ecodo.data.database.Trash
import com.bangkit.ecodo.databinding.ItemHistoryBinding
import com.bangkit.ecodo.util.toImageBitmap

class TrashAdapter(
    private val dataset: List<Trash>,
    private val onItemClick: (Long) -> Unit,
) :
    RecyclerView.Adapter<TrashAdapter.TrashViewHolder>() {
    inner class TrashViewHolder(
        val binding: ItemHistoryBinding,
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrashViewHolder {
        val binding = ItemHistoryBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return TrashViewHolder(binding)
    }

    override fun getItemCount(): Int = dataset.size

    override fun onBindViewHolder(holder: TrashViewHolder, position: Int) {
        val trash = dataset[position]
        holder.binding.apply {
            tvItem.text = trash.predictedClass
            imgItemPhoto.setImageBitmap(trash.imageData.toImageBitmap())
        }
        holder.binding.cardView.setOnClickListener {
            onItemClick(trash.id)
        }
    }
}