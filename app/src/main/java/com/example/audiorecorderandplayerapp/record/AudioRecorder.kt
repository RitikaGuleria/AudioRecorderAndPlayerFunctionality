package com.example.audiorecorderandplayerapp.record

import java.io.File

interface AudioRecorder {
    fun start(outputFile : File)
    fun stop()
}