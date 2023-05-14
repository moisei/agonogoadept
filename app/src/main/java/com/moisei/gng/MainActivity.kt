package com.moisei.gng

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.moisei.gng.ui.theme.AGoNoGoAdeptTheme

class MainActivity : ComponentActivity() {
    private val handler = Handler(Looper.getMainLooper())
    private lateinit var goState: MutableState<Color>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        goState = mutableStateOf(Color.Green)

        setContent {
            AGoNoGoAdeptTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colorScheme.background) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = goState.value)
                            .clickable {
                                changeToNoGoState()
                            }
                    )
                }
            }
        }
    }

    private fun changeToNoGoState() {
        goState.value = Color.Red
        handler.postDelayed({
            changeToGoState()
        }, 5000) // Change to GO state after 5 seconds
    }

    private fun changeToGoState() {
        goState.value = Color.Green
    }
}
