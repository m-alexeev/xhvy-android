package com.example.xhvy.ui.screens.workouts

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.xhvy.ui.theme.XhvyTheme



@Composable
fun NewWorkoutScreen() {

    Column {
        Text(text = "New Workout")
    }
}

@Preview(showBackground = true)
@Composable
fun NewWorkoutScreenPreview() {
    XhvyTheme {
        NewWorkoutScreen()
    }
}