package com.example.stopwatch.state

sealed class StopWatchState {
    data class Paused(
        val elapsingTime: Long
    ) : StopWatchState()

    data class Running(
        val startTime: Long,
        val elapsingTime: Long
    ) : StopWatchState()
}
