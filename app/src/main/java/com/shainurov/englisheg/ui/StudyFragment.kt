package com.shainurov.englisheg.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.shainurov.domain.models.QuestionModel
import com.shainurov.englisheg.R
import com.shainurov.englisheg.databinding.FragmentStudyBinding
import com.shainurov.englisheg.presentation.viewmodels.HomeViewModel
import com.shainurov.englisheg.presentation.viewmodels.StudyViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StudyFragment : Fragment() {

    private var _binding: FragmentStudyBinding? = null
    private val binding = _binding!!
    private val viewModel: StudyViewModel by viewModels()
    private val records = ArrayList<QuestionModel>()
    private val viewModelHome: HomeViewModel by activityViewModels()
    private var num: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStudyBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelHome.selectFile.observe(viewLifecycleOwner) {
            viewModel.getData(it.fileName)
        }

        viewModel.data.observe(viewLifecycleOwner) {
            records.addAll(it)
            if (records.isNotEmpty()) {
                binding.nextBtn.apply {
                    isVisible = true
                    text = getString(R.string.start_button_text)
                }
                binding.transcriptionTV.isVisible = false
                binding.learnCB.isVisible = false
            }
        }
    }

    fun random() {
        num = (0..records.size).filter { it != num }.random()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}