package com.shainurov.englisheg.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shainurov.data.models.Question
import com.shainurov.domain.models.QuestionModel
import com.shainurov.englisheg.databinding.QuestionItemBinding

class QuestionAdapter(private val clickListener: (wallpaper: QuestionModel) -> Unit) :
    ListAdapter<QuestionModel, QuestionAdapter.ItemViewholder>(DiffCallbackQuestion()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewholder {
        val binding =
            QuestionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewholder(
            binding
        )

    }

    override fun onBindViewHolder(holder: ItemViewholder, position: Int) {
        getItem(position)?.let { holder.bind(it, clickListener) }
    }

    class ItemViewholder(private val binding: QuestionItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: QuestionModel, clickListener: (QuestionModel) -> Unit) = with(itemView) {
            binding.question.text = "Вопрос на английском: " + item.sentenceInEnglish
            binding.questionRus.text = "Вопрос на русском: " + item.sentenceInRussian
            binding.transcription.text = "Транскрипция: " + item.transcription
            binding.succesTV.text = "Верных ответов: " + item.countCorrectAnswers.toString()
            binding.errorTV.text = "Ошибок: " + item.countIncorrectAnswers.toString()
            binding.totalTV.text = "Всего показов: " + item.totalCount.toString()
            binding.learnPB.progress = item.totalCount
            binding.learnCB.isChecked = item.removeFromStudy

            itemView.setOnClickListener {
                clickListener(item)
            }
        }
    }
}

class DiffCallbackQuestion : DiffUtil.ItemCallback<QuestionModel>() {
    override fun areItemsTheSame(oldItem: QuestionModel, newItem: QuestionModel): Boolean {
        return oldItem.id == newItem.id ||
                oldItem.countCorrectAnswers == newItem.countCorrectAnswers ||
                oldItem.countIncorrectAnswers == newItem.countIncorrectAnswers ||
                oldItem.removeFromStudy == newItem.removeFromStudy ||
                oldItem.sentenceInEnglish == newItem.sentenceInEnglish ||
                oldItem.sentenceInRussian == newItem.sentenceInRussian ||
                oldItem.totalCount == newItem.totalCount ||
                oldItem.transcription == newItem.transcription
    }

    override fun areContentsTheSame(oldItem: QuestionModel, newItem: QuestionModel): Boolean {
        return oldItem == newItem
    }
}