package com.moisei.gng

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

class SessionConfigurationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                SessionConfiguration()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SessionConfiguration() {
    val context = LocalContext.current
    Column(modifier = Modifier.padding(16.dp)) {
        val noGoStateTime = remember { mutableStateOf("5") }
        val goStateColor = remember { mutableStateOf("Green") }
        val noGoStateColor = remember { mutableStateOf("Red") }

        TextField(
            value = noGoStateTime.value,
            onValueChange = { noGoStateTime.value = it },
            label = { Text("No Go state time") }
        )
        Spacer(Modifier.height(16.dp))
        TextField(
            value = goStateColor.value,
            onValueChange = { goStateColor.value = it },
            label = { Text("Go State color") }
        )
        Spacer(Modifier.height(16.dp))
        TextField(
            value = noGoStateColor.value,
            onValueChange = { noGoStateColor.value = it },
            label = { Text("NoGo State color") }
        )
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = {
                val intent = Intent(context, MainActivity::class.java).apply {
                    ConfigurationHolder.noGoStateTimeMillis = 1000 * (noGoStateTime.value.toLongOrNull() ?: 5L)
                    ConfigurationHolder.goStateColor = string2color(goStateColor)
                    ConfigurationHolder.noGoStateColor = string2color(noGoStateColor)
                    putExtra("NO_GO_STATE_COLOR", noGoStateColor.value)
                }
                context.startActivity(intent)
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Start")
        }
    }
}

private fun string2color(goStateColor: MutableState<String>) =
    when (goStateColor.value.lowercase()) {
        "red" -> Color.Red
        "blue" -> Color.Blue
        // Add other colors if needed
        else -> Color.Green
    }
