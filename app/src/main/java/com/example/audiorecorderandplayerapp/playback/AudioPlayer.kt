package com.example.audiorecorderandplayerapp.playback

import java.io.File

interface AudioPlayer {
    fun playFile(file: File)
    fun stop()
}