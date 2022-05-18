package com.example.stopwatch.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.stopwatch.MainViewModel
import com.example.stopwatch.R
import com.example.stopwatch.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    private lateinit var _binding: FragmentMainBinding
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.myTicker.observe(
            viewLifecycleOwner
        )
        {
            binding.textTime.text = viewModel.stopWatchStateHolder.getStringTimeRepresentation()
        }

        binding.buttonStart.setOnClickListener {
            viewModel.start()
        }

        binding.buttonPause.setOnClickListener {
            viewModel.pause()
        }

        binding.buttonStop.setOnClickListener {
            viewModel.stop()
        }
    }
}