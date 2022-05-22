package com.shainurov.englisheg.ui

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shainurov.englisheg.databinding.FragmentDetailBinding
import com.shainurov.englisheg.presentation.adapters.QuestionAdapter
import com.shainurov.englisheg.presentation.viewmodels.DetailViewModel
import com.shainurov.englisheg.presentation.viewmodels.SettingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private val viewModelSetting: SettingViewModel by activityViewModels()
    private val viewModel: DetailViewModel by viewModels()
    private lateinit var adapter: QuestionAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        QuestionAdapter(
            clickListener = {

            }
        ).also {
            adapter = it
        }
        binding.detailRecyclerView.adapter = adapter

        binding.detailRecyclerView.setOnScrollChangeListener { _, _, _, _, _ ->
            binding.textView.text =
                "${binding.detailRecyclerView.getCurrentPosition()}/${adapter.currentList.count()}"
        }

        viewModel.data.observe(viewLifecycleOwner) {
            adapter.submitList(it)
            binding.progressBar.isVisible = false
        }

        viewModelSetting.sel.observe(viewLifecycleOwner) {
            viewModel.readDataFromDatabase(it.name.trim())
            viewModel.getListOfPlaylist(it.filePath, it.name)
            viewModel.readDataFromDatabase(it.name.trim())
        }

        binding.scrollToTop.setOnClickListener {
            val animator = ValueAnimator.ofInt(
                binding.detailRecyclerView.getCurrentPosition(),
                0
            )
            animator.duration = 3000
            animator.addUpdateListener { animator ->
                binding.detailRecyclerView.scrollToPosition(
                    animator.animatedValue.toString().toInt()
                )
            }
            animator.start()

            binding.detailRecyclerView.scrollToPosition(1)
        }
    }

    private fun RecyclerView?.getCurrentPosition(): Int {
        return (this?.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}