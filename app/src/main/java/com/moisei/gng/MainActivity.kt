package com.moisei.gng

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.moisei.gng.ui.theme.AGoNoGoAdeptTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    private val handler = Handler(Looper.getMainLooper())
    private lateinit var gngState: MutableState<Color>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        gngState = mutableStateOf(ConfigurationHolder.goStateColor)

        setContent {
            AGoNoGoAdeptTheme {
                Box(modifier = Modifier.fillMaxSize()) {
                    Surface(color = MaterialTheme.colorScheme.background) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color = gngState.value)
                                .clickable {
                                    changeToNoGoState()
                                }
                        )
                    }
                    Button(
                        onClick = {
                            startActivity(
                                Intent(
                                    this@MainActivity,
                                    HomeScreenActivity::class.java
                                )
                            )
                        },
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .fillMaxWidth()
                            .height((LocalConfiguration.current.screenHeightDp * 0.1).dp)
                    ) {
                        Text(text = "HOME")
                    }
                }
            }
        }
    }

    private fun changeToNoGoState() {
        gngState.value = ConfigurationHolder.noGoStateColor
        handler.postDelayed({
            changeToGoState()
        }, ConfigurationHolder.noGoStateTimeMillis)
    }

    private fun changeToGoState() {
        gngState.value = ConfigurationHolder.goStateColor
    }
}
