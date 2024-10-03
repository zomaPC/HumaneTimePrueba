package com.example.humanetime

//import com.example.humanetime.navigation.Navigator
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.humanetime.navigation.Navigator
import com.example.humanetime.ui.theme.HumaneTimeTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HumaneTimeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    Navigator()
                }
            }
        }
    }
}

