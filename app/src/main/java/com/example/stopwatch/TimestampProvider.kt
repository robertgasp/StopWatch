package com.example.stopwatch

interface TimestampProvider {
    fun getMilliSeconds(): Long
}