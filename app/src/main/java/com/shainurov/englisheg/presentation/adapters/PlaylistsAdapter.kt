package com.shainurov.englisheg.presentation.adapters


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shainurov.domain.models.PlaylistModel
import com.shainurov.englisheg.databinding.PlaylistItemBinding

class PlaylistsAdapter(
    private val clickListener: (playlistModel: PlaylistModel) -> Unit,
    private val deleteClickListener: (fileName: String) -> Unit
) :
    ListAdapter<PlaylistModel, PlaylistsAdapter.ItemViewholder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewholder {
        val binding =
            PlaylistItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewholder(
            binding
        )
    }

    override fun onBindViewHolder(holder: ItemViewholder, position: Int) {
        holder.bind(getItem(position), clickListener, deleteClickListener)
    }

    class ItemViewholder(private val binding: PlaylistItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(
            item: PlaylistModel,
            clickListener: (PlaylistModel) -> Unit,
            deleteClickListener: (fileName: String) -> Unit
        ) = with(itemView) {
            binding.namePlaylist.text = "Название: " + item.name.trim()
            binding.sizePlaylist.text = "Размер: " + item.size + " kb"
            binding.levelPlaylist.text = "Уровень: " + item.level
            binding.downloadButton.isVisible = !item.download
            binding.deleteButton.isVisible = item.download

            binding.downloadButton.setOnClickListener {
                clickListener(item)
            }
            binding.deleteButton.setOnClickListener {
                deleteClickListener(item.filePath)
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