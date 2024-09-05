package com.example.xhvy

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.xhvy.data.repositories.AppDatabase
import com.example.xhvy.navigation.MainNavigation
import com.example.xhvy.ui.theme.XhvyTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        clearDatabase(this)
        val database = AppDatabase.getDatabase(this)

        setContent {
            XhvyTheme {
                MainNavigation(database = database)
            }
        }
    }
}

fun clearDatabase(context: Context) {
    context.deleteDatabase("exercise-db")

}