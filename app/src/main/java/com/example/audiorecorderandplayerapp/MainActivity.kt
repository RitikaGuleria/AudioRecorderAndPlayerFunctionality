package com.example.audiorecorderandplayerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import com.example.audiorecorderandplayerapp.playback.AndroidAudioPlayer
import com.example.audiorecorderandplayerapp.record.AndroidAudioRecorder
import com.example.audiorecorderandplayerapp.ui.theme.AudioRecorderAndPlayerAppTheme
import java.io.File
import java.util.jar.Manifest

class MainActivity : ComponentActivity() {

    private val recorder by lazy{
        AndroidAudioRecorder(this)
    }
    private val player by lazy {
        AndroidAudioPlayer(applicationContext)
    }
    //here we will store our audio
    private var audioFile: File?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivityCompat.requestPermissions(this,
            arrayOf(android.Manifest.permission.RECORD_AUDIO),0)

        setContent {
            AudioRecorderAndPlayerAppTheme {
                Column(modifier= Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally)
                {
                    Button(onClick = { File(cacheDir,"audio.mp3").also{
                        recorder.start(it)
                        audioFile=it
                    }}) {
                        Text(text = "Start Recording")
                    }

                    Button(onClick = { recorder.stop()}) {
                       Text(text = "Stop Recording")
                    }

                    Button(onClick = { player.playFile(audioFile ?: return@Button)}) {
                        Text(text = "Play")
                    }

                    Button(onClick = { player.stop()}) {
                        Text(text = "Stop Playing")
                    }
                }
            }
        }
    }
}

