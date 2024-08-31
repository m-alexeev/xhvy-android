package com.example.xhvy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.xhvy.navigation.MainNavigation
import com.example.xhvy.ui.theme.XhvyTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            XhvyTheme {
                MainNavigation()
            }
        }
    }
}
