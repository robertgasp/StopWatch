package com.example.stopwatch.view

import android.database.Observable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.stopwatch.*
import com.example.stopwatch.databinding.ActivityMainBinding
import com.example.stopwatch.ElapsedTimeCalculator
import com.example.stopwatch.StopwatchListOrchestrator
import com.example.stopwatch.StopwatchStateCalculator
import com.example.stopwatch.state.StopWatchStateHolder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.observeOn
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.myTicker.observe(
            this
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

