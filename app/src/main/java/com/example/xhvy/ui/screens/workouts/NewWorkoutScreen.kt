package com.example.xhvy.ui.screens.workouts

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.xhvy.data.models.WorkoutAction
import com.example.xhvy.navigation.WorkoutStack
import com.example.xhvy.ui.theme.XhvyTheme
import com.example.xhvy.ui.view_models.NewWorkoutViewModel


@Composable
fun NewWorkoutScreen(
    navHostController: NavHostController,
    newWorkoutViewModel: NewWorkoutViewModel = viewModel(),
) {
    val workout by newWorkoutViewModel.workout.collectAsState()
    workout?.let {
        ActiveWorkout(workout = it, onWorkoutAction = { action ->
            when (action) {
                WorkoutAction.AddExercise -> navHostController.navigate(WorkoutStack.ExerciseList)
                WorkoutAction.CancelWorkout -> {
                    navHostController.popBackStack()
                    newWorkoutViewModel.cancelWorkout()
                }

                WorkoutAction.CompleteWorkout -> {
                    navHostController.popBackStack()
                    newWorkoutViewModel.saveWorkout()
                }

                is WorkoutAction.EditSet -> {
                    newWorkoutViewModel.editSet(updatedSet = action.exerciseSet)
                }

                is WorkoutAction.RemoveSet -> {
                    newWorkoutViewModel.removeSet(action.setId)
                }

                is WorkoutAction.AddSet -> {
                    newWorkoutViewModel.addSet(workoutExerciseId = action.exerciseId)
                }
            }
        })
    } ?: run {
        Text(text = "Loading workout or it doesn't exist")
    }

}


@Preview(showBackground = true)
@Composable
fun NewWorkoutScreenPreview() {
    XhvyTheme {
        NewWorkoutScreen(
            navHostController = rememberNavController()
        )
    }
}