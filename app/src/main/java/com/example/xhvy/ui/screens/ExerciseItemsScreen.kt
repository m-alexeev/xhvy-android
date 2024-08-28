package com.example.xhvy.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.xhvy.ui.components.exercises.ExerciseItemsList
import com.example.xhvy.ui.components.exercises.exercises
import com.example.xhvy.ui.components.general.Searchbar

@Composable
fun ExerciseItemScreen() {
    Column {
        Searchbar()
        ExerciseItemsList(exercises = exercises)
    }
}