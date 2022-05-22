package com.shainurov.englisheg.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.shainurov.englisheg.R
import com.shainurov.englisheg.databinding.FragmentHomeBinding
import com.shainurov.englisheg.presentation.adapters.HomeAdapter
import com.shainurov.englisheg.presentation.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONArray

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by activityViewModels()
    private lateinit var adapter: HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        HomeAdapter(
            clickListener = {
                viewModel.selectFile.value = it
                this.findNavController().navigate(R.id.action_homeFragment_to_studyFragment)
            }
        ).also {
            adapter = it

        }


        viewModel.data.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                adapter.submitList(it)
                binding.progressBar3.isVisible = false
                binding.noData.isVisible = false
            } else {
                binding.noData.isVisible = true
                binding.progressBar3.isVisible = false
            }

        }


        binding.homeRecyclerView.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        viewModel.getList()
    }

    override fun onDestroy() {

        super.onDestroy()
    }

}