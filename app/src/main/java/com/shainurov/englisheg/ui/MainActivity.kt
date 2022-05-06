package com.shainurov.englisheg.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.shainurov.englisheg.R
import com.shainurov.englisheg.databinding.ActivityMainBinding
import com.shainurov.englisheg.presentation.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val navController = this.findNavController(R.id.activity_main_nav_host_fragment)
        binding?.activityMainBottomNavigationView?.setupWithNavController(navController)

    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}