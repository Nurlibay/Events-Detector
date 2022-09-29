package uz.unidev.eventdetector.presentation.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.unidev.eventdetector.R
import uz.unidev.eventdetector.databinding.ScreenMainBinding
import uz.unidev.eventdetector.service.EventService

/**
 *  Created by Nurlibay Koshkinbaev on 27/09/2022 16:01
 */

@AndroidEntryPoint
class MainScreen : Fragment(R.layout.screen_main) {

    private val binding: ScreenMainBinding by viewBinding()
    private val viewModel: MainViewModel by viewModels<MainViewModelImpl>()
    private val adapter by lazy { EventAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllEvents()
        setupAdapter()
        startService()
    }

    private fun startService() {
        val intent = Intent(requireContext(), EventService::class.java)
        ContextCompat.startForegroundService(requireContext(), intent)
    }

    private fun setupAdapter() {
        binding.rvEvents.adapter = adapter
        viewModel.allEventData.onEach {
            adapter.submitList(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)
        adapter.switchStatusChangeListener {
            viewModel.itemClick(it)
        }
    }
}