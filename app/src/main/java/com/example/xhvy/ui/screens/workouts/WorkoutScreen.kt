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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.xhvy.navigation.WorkoutStack
import com.example.xhvy.ui.components.workouts.WorkoutRowItem
import com.example.xhvy.ui.theme.XhvyTheme
import com.example.xhvy.ui.view_models.WorkoutsViewModel

@Composable
fun WorkoutScreen(
    navController: NavHostController,
    workoutsViewModel: WorkoutsViewModel,
    modifier: Modifier = Modifier
) {
    val workouts by workoutsViewModel.workouts.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
    ) {
        Text(text = "WorkoutScreen")
        Button(
            onClick = { navController.navigate(WorkoutStack.NewWorkout) },
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

@Preview(showBackground = true)
@Composable
fun WorkoutScreenPreview() {
    XhvyTheme {
        WorkoutScreen(rememberNavController(), viewModel())
    }
}