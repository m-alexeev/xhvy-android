package com.example.xhvy.ui.screens.workouts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.xhvy.navigation.WorkoutStack
import com.example.xhvy.ui.components.workouts.WorkoutRowItem
import com.example.xhvy.ui.view_models.NewWorkoutViewModel
import com.example.xhvy.ui.view_models.WorkoutsViewModel

@Composable
fun WorkoutScreen(
    navController: NavHostController,
    workoutsViewModel: WorkoutsViewModel,
    newWorkoutViewModel: NewWorkoutViewModel,
    modifier: Modifier = Modifier
) {
    val workouts by workoutsViewModel.workouts.collectAsState()
    val activeWorkout by newWorkoutViewModel.workout.collectAsState()
    Column(
        modifier = modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
    ) {
        Text(text = "WorkoutScreen")
        Button(
            onClick = {
                if (activeWorkout == null) {
                    newWorkoutViewModel.createWorkout()
                }
                navController.navigate(WorkoutStack.NewWorkout)
            },
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(text = "Create Empty Workout")
        }
        LazyColumn {
            items(items = workouts) { workout ->
                WorkoutRowItem(workout = workout)
                Spacer(modifier = Modifier.padding(vertical = 6.dp))
            }
        }
    }
}