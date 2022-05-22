package com.shainurov.englisheg.ui

import android.animation.ObjectAnimator
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.shainurov.domain.models.QuestionModel
import com.shainurov.englisheg.R
import com.shainurov.englisheg.databinding.FragmentStudyBinding
import com.shainurov.englisheg.presentation.utils.LoginUIState
import com.shainurov.englisheg.presentation.viewmodels.HomeViewModel
import com.shainurov.englisheg.presentation.viewmodels.StudyViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

@AndroidEntryPoint
class StudyFragment : Fragment() {

    private lateinit var binding: FragmentStudyBinding
    private val viewModel: StudyViewModel by viewModels()
    private val records = ArrayList<QuestionModel>()
    private val viewModelHome: HomeViewModel by activityViewModels()
    private var num: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStudyBinding.inflate(layoutInflater, container, false)
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

        lifecycleScope.launchWhenCreated {
            viewModel.viewUIState.collect {
                when (it) {
                    is LoginUIState.Success -> {
                        binding.timeCA.text = "close"
                        binding.timeCA.setTextColor(resources.getColor(R.color.all_button))
                    }
                    is LoginUIState.Loading ->
                        binding.timeCA.text = it.time

                }
            }
        }

        binding.learnCB.setOnCheckedChangeListener { compoundButton, b ->
            records[num].copy(removeFromStudy = b)
            lifecycleScope.launch(Dispatchers.IO) {
                records[num].let { viewModel.insert(it) }
            }
        }

        binding.editCard.setOnClickListener {
            this.findNavController().navigate(R.id.action_studyFragment_to_editFragment)
        }

        binding.answerET.setOnKeyListener { _, keyCode, keyEvent ->
            if (keyEvent.action == KeyEvent.ACTION_DOWN &&
                keyCode == KeyEvent.KEYCODE_ENTER
            ) {
                binding.apply {
                    records[num].apply {
                        if (answerET.text.isNotEmpty()) {
                            nextBtn.isVisible = true

                            if (answerET.text.toString().lowercase(Locale.getDefault()).trim() ==
                                sentenceInEnglish
                                    .lowercase(Locale.getDefault()).trim()
                            ) {
                                binding.transcriptionTV.isVisible = true
                                countCorrectAnswers = countCorrectAnswers.plus(1)

                                totalCount = totalCount.plus(1)

                                lifecycleScope.launch(Dispatchers.IO) {
                                    viewModel.insert(this@apply)
                                }

                                answerET.isEnabled = false
                                nextBtn.isVisible =true
                                questionView.setTextColor(resources.getColor(android.R.color.holo_green_light))

                            } else {
                                binding.transcriptionTV.isVisible = true
                                countIncorrectAnswers = countIncorrectAnswers.plus(1)

                                totalCount = totalCount.plus(1)

                                lifecycleScope.launch(Dispatchers.IO) {
                                    viewModel.insert(this@apply)
                                }
                                answerET.isEnabled = false

                                correctAnswerCV.isVisible = true
                                correctAnswer.text = records[num].sentenceInEnglish
                                viewModel.startTimeCounter()


                                questionView.setTextColor(resources.getColor(android.R.color.holo_red_dark))
                            }
                        }
                    }
                }
                true
            } else
                false
        }
        binding.timeCA.setOnClickListener {
            binding.correctAnswerCV.isVisible = false
        }

        binding.nextBtn.setOnClickListener {
            binding.editCard.isVisible = true
            random()
            loadSentece()
            viewModel.mutableSelectedItem.value = records[num]
        }
    }

    private fun loadSentece() {


        binding.apply {
            correctAnswerCV.isVisible = false
            transcriptionTV.isVisible = false
            nextBtn.text = getString(R.string.next_button_text)
            answerET.isVisible = true
            learnCB.isVisible = true

            questionView.setTextColor(resources.getColor(R.color.white))
            answerET.apply {
                isEnabled = true
                text.clear()
            }
            if (answerET.text.isEmpty()) {
                nextBtn.isVisible = false
            }
            learnCB.isChecked = records[num].removeFromStudy
            questionView.text = records[num].sentenceInRussian
            transcriptionTV.text = records[num].transcription

            ObjectAnimator.ofInt(
                binding.learnPB,
                "progress",
                records[num].totalCount
            )
                .setDuration(100)
                .start()
        }

    }

    fun random() {
        num = (0..records.size).filter { it != num }.random()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}