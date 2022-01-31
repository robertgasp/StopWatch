package com.example.stopwatch

import com.example.stopwatch.state.StopWatchState

class ElapsedTimeCalculator(
    private val timestampProvider: TimestampProvider
) {

    fun calculate(state: StopWatchState.Running): Long {
        val currentTimeStamp = timestampProvider.getMilliSeconds()
        val timePassedSinceStart = if (currentTimeStamp > state.startTime) {
            currentTimeStamp - state.startTime
        } else {
            0
        }
        return timePassedSinceStart + state.elapsingTime
    }
}