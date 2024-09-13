package com.example.xhvy.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.xhvy.navigation.TopNavBar
import com.example.xhvy.ui.components.workouts.WorkoutRowItem
import com.example.xhvy.ui.view_models.WorkoutsViewModel

@Composable
fun HistoryScreen(workoutsViewModel: WorkoutsViewModel, modifier: Modifier = Modifier ) {

    val completedWorkouts by workoutsViewModel.workouts.collectAsState()
    Scaffold(topBar = { TopNavBar(label = "History") }) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(innerPadding)
        ) {
            LazyColumn {
                items(items = completedWorkouts) { workout ->
                    WorkoutRowItem(workout = workout)
                    Spacer(modifier = Modifier.padding(vertical = 6.dp))
                }
            }
        }
    }

}