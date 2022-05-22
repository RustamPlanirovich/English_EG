package com.shainurov.englisheg.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.shainurov.domain.models.QuestionModel
import com.shainurov.englisheg.R
import com.shainurov.englisheg.databinding.FragmentEditBinding
import com.shainurov.englisheg.presentation.viewmodels.DetailViewModel
import com.shainurov.englisheg.presentation.viewmodels.EditViewModel
import com.shainurov.englisheg.presentation.viewmodels.StudyViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditFragment : Fragment() {

    private lateinit var binding: FragmentEditBinding
    private val viewModelStudy: StudyViewModel by activityViewModels()
    private val viewModelDetail: DetailViewModel by activityViewModels()
    private val viewModel: EditViewModel by viewModels()
    lateinit var question: QuestionModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelStudy.mutableSelectedItem.observe(viewLifecycleOwner) {
            binding.sentenceInEnglish.setText(it.sentenceInEnglish)
            binding.sentenceInRussian.setText(it.sentenceInRussian)
            binding.learn.isChecked = it.removeFromStudy
            question = it
        }

        viewModelDetail.mutableSelectedItem.observe(viewLifecycleOwner) {
            binding.sentenceInEnglish.setText(it.sentenceInEnglish)
            binding.sentenceInRussian.setText(it.sentenceInRussian)
            binding.learn.isChecked = it.removeFromStudy
            question = it
        }

        binding.saveBtn.setOnClickListener {
            question.sentenceInEnglish = binding.sentenceInEnglish.text.toString()
            question.sentenceInRussian = binding.sentenceInRussian.text.toString()
            question.removeFromStudy = binding.learn.isChecked

            viewModel.update(question)
            this.findNavController().navigate(R.id.action_editFragment_to_studyFragment)

        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }


}