package com.shainurov.englisheg.ui

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
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
import org.json.JSONArray
import java.io.File
import java.io.FileInputStream

@AndroidEntryPoint
class SettingFragment : Fragment() {

    private var binding: FragmentSettingBinding? = null
    private lateinit var adapter: PlaylistsAdapter
    private val viewModel: SettingViewModel by viewModels()


    private val broadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val action = intent?.action
            if (DownloadManager.ACTION_DOWNLOAD_COMPLETE == action) {
                Toast.makeText(context, "Download Completed", Toast.LENGTH_SHORT).show()
                viewModel.loadData()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.deleted.observe(viewLifecycleOwner) {
            viewModel.loadData()
        }


        context?.registerReceiver(
            broadcastReceiver,
            IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE)
        )


        PlaylistsAdapter(
            clickListener = {
                viewModel.savePlaylist(it.url, it.name)
            },
            deleteClickListener = { deletedFile ->
                viewModel.deletePlaylisyt(deletedFile)
            }
        ).also {
            adapter = it
        }

        viewModel.data.observe(viewLifecycleOwner, adapter::submitList)

        binding?.allPlaylists?.adapter = adapter

        binding?.swipe?.setOnRefreshListener {
            viewModel.loadData()
            binding?.swipe?.isRefreshing = false
        }

    }

    override fun onDestroy() {
        null.also { binding = it }
        super.onDestroy()
    }

}