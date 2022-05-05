package com.shainurov.englisheg.presentation.adapters


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shainurov.domain.models.PlaylistModel
import com.shainurov.englisheg.databinding.PlaylistItemBinding

class PlaylistsAdapter :
    ListAdapter<PlaylistModel, PlaylistsAdapter.ItemViewholder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewholder {
        val binding =
            PlaylistItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewholder(
            binding
        )
    }

    override fun onBindViewHolder(holder: ItemViewholder, position: Int) {
        holder.bind(getItem(position))
    }

    class ItemViewholder(private val binding: PlaylistItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: PlaylistModel) = with(itemView) {
            binding.namePlaylist.text = "Название: " + item.name
            binding.sizePlaylist.text = "Размер: " + item.size + " kb"
            binding.levelPlaylist.text = "Уровень: " + item.level

            setOnClickListener {
                // TODO: Handle on click
            }
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<PlaylistModel>() {
    override fun areItemsTheSame(oldItem: PlaylistModel, newItem: PlaylistModel): Boolean {
        return oldItem.name == newItem.name ||
                oldItem.url == newItem.url ||
                oldItem.size == newItem.size ||
                oldItem.level == newItem.level
    }

    override fun areContentsTheSame(oldItem: PlaylistModel, newItem: PlaylistModel): Boolean {
        return oldItem == newItem
    }
}