package com.example.stopwatch

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stopwatch.state.StopWatchState
import com.example.stopwatch.state.StopWatchStateHolder
import kotlinx.coroutines.*
import kotlinx.coroutines.NonCancellable.isActive
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val timestampProvider = object : TimestampProvider {
        override fun getMilliSeconds(): Long = System.currentTimeMillis()
    }

    private val scope: CoroutineScope = viewModelScope

    val stopWatchStateHolder: StopWatchStateHolder = StopWatchStateHolder(
        StopwatchStateCalculator(
            timestampProvider,
            ElapsedTimeCalculator(timestampProvider)
        ),
        ElapsedTimeCalculator(timestampProvider),
        TimestampMillisecondsFormatter()
    )

    private var job: Job? = null
    private val mutableTicker = MutableLiveData<String>()
    val myTicker = mutableTicker


    fun start() {
        if (job == null) startJob()
        stopWatchStateHolder.start()
    }

    private fun startJob() {
        scope.launch {
            while (isActive) {
                mutableTicker.value = stopWatchStateHolder.getStringTimeRepresentation()
                delay(20)
            }
        }
    }

    fun pause() {
        stopWatchStateHolder.paused()
        stopJob()
    }

    private fun stopJob() {
        scope.coroutineContext.cancelChildren()
        job = null
    }

    fun stop() {
        stopWatchStateHolder.stop()
        stopJob()
        clearValue()
    }

    private fun clearValue() {
        mutableTicker.value = "00:00:000"
    }
}