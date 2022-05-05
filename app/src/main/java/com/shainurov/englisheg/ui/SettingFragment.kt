package com.shainurov.englisheg.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.shainurov.englisheg.databinding.FragmentSettingBinding
import com.shainurov.englisheg.presentation.adapters.PlaylistsAdapter
import com.shainurov.englisheg.presentation.viewmodels.SettingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingFragment : Fragment() {

    private var binding: FragmentSettingBinding? = null
    private lateinit var adapter: PlaylistsAdapter
    private val viewModel: SettingViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = PlaylistsAdapter()



        viewModel.data.observe(viewLifecycleOwner) {
            Log.d("Hello", "${it}")
            adapter.submitList(it)
        }

        binding?.allPlaylists?.adapter = adapter

        binding?.swipe?.setOnRefreshListener {
            viewModel.loadData()
            binding?.swipe?.isRefreshing = false
        }

    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

}