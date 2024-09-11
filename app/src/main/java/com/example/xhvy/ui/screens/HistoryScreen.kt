package com.example.xhvy.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.xhvy.ui.view_models.NewWorkoutViewModel

@Composable
fun HistoryScreen (modifier: Modifier = Modifier, workoutViewModel: NewWorkoutViewModel = viewModel()){
    Text(text = "History")
}