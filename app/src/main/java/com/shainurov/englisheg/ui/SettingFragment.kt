package com.shainurov.englisheg.ui

import android.app.DownloadManager
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.shainurov.englisheg.databinding.FragmentSettingBinding
import com.shainurov.englisheg.presentation.adapters.PlaylistsAdapter
import com.shainurov.englisheg.presentation.viewmodels.SettingViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.io.File

@AndroidEntryPoint
class SettingFragment : Fragment() {

    private var binding: FragmentSettingBinding? = null
    private lateinit var adapter: PlaylistsAdapter
    private val viewModel: SettingViewModel by viewModels()
    private var dm: DownloadManager? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        adapter = PlaylistsAdapter {
            viewModel.savePlaylist(it.url, it.name)
        }


        viewModel.data.observe(viewLifecycleOwner) {
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