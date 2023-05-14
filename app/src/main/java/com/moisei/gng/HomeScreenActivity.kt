package com.moisei.gng

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview

class HomeScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                HomeScreen {
                    startActivity(Intent(this, SessionConfigurationActivity::class.java))
                }
            }
        }
    }
}

@Composable
fun HomeScreen(onStartSession: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = onStartSession) {
            Text("Start New Session")
        }
        Spacer(Modifier.height(16.dp))
        Button(onClick = { /*TODO: Implement restart session*/ }) {
            Text("Restart Saved Session")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    MaterialTheme {
        HomeScreen {}
    }
}
