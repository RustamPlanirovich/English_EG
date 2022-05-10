package com.shainurov.englisheg.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.shainurov.englisheg.databinding.FragmentDetailBinding
import com.shainurov.englisheg.presentation.adapters.QuestionAdapter
import com.shainurov.englisheg.presentation.viewmodels.DetailViewModel
import com.shainurov.englisheg.presentation.viewmodels.SettingViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.io.File

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var binding: FragmentDetailBinding? = null
    private val viewModelSetting: SettingViewModel by activityViewModels()
    private val viewModel: DetailViewModel by viewModels()
    private lateinit var adapter: QuestionAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.readDataFromDatabase()

        QuestionAdapter(
            clickListener = {

            }
        ).also {
            adapter = it
        }
        binding?.detailRecyclerView?.adapter = adapter

        viewModel.data.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

//        viewModelSetting.sel.observe(viewLifecycleOwner) {
//            viewModel.readDataFromJsonFile(it.filePath.toUri())
//        }
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

}