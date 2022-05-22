package com.shainurov.englisheg.presentation.adapters


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shainurov.domain.models.FileList
import com.shainurov.domain.models.PlaylistModel
import com.shainurov.englisheg.databinding.FragmentHomeBinding
import com.shainurov.englisheg.databinding.HomeItemBinding
import com.shainurov.englisheg.databinding.PlaylistItemBinding
import java.io.File

class HomeAdapter(
    private val clickListener: (fileModel: FileList) -> Unit,
) :
    ListAdapter<FileList, HomeAdapter.ItemViewholder>(HomeDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewholder {
        val binding =
            HomeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewholder(
            binding
        )
    }

    override fun onBindViewHolder(holder: ItemViewholder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

    class ItemViewholder(private val binding: HomeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(
            fileModel: FileList,
            clickListener: (FileList) -> Unit,
        ) = with(itemView) {
            binding.namePlaylist.text = fileModel.fileName


            binding.namePlaylist.setOnClickListener {
                clickListener(fileModel)
            }


        }
    }
}

class HomeDiffCallback : DiffUtil.ItemCallback<FileList>() {
    override fun areItemsTheSame(oldItem: FileList, newItem: FileList): Boolean {
        return oldItem.fileName == newItem.fileName
    }

    override fun areContentsTheSame(oldItem: FileList, newItem: FileList): Boolean {
        return oldItem == newItem
    }
}