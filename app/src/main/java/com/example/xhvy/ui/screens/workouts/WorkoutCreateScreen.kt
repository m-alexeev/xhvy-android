package com.example.xhvy.ui.screens.workouts

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.xhvy.data.models.Exercise
import com.example.xhvy.data.models.WorkoutAction
import com.example.xhvy.data.models.WorkoutExercise
import com.example.xhvy.navigation.MainStack
import com.example.xhvy.navigation.WorkoutStack
import com.example.xhvy.ui.components.workouts.ActiveWorkout
import com.example.xhvy.ui.theme.XhvyTheme
import com.example.xhvy.view_models.WorkoutCreateViewModel


@Composable
fun WorkoutCreateScreen(
    navHostController: NavHostController,
    workoutCreateViewModel: WorkoutCreateViewModel = viewModel(),
) {
    val workout by workoutCreateViewModel.workout.collectAsState()
    val savedStateHandle = remember { navHostController.currentBackStackEntry?.savedStateHandle }
    val lifecycleOwner = LocalLifecycleOwner.current

    // Launched Effect runs only once when the composable is initially composed
    LaunchedEffect(Unit) {
        savedStateHandle?.getLiveData<List<Exercise>>("selectedExercises")
            ?.observe(lifecycleOwner) { selectedItems ->
                selectedItems.forEach { exercise: Exercise ->
                    val workoutExercise =
                        WorkoutExercise(exercise = exercise)
                    workoutCreateViewModel.addWorkoutExercise(workoutExercise)
                }
            }
    }


    // TODO: Split into separate components and pass around data to make sure that the whole screen doesnt recompose
    workout?.let {workout ->
        ActiveWorkout(workout = workout, onWorkoutAction = { action ->
            when (action) {
                WorkoutAction.AddExercise -> navHostController.navigate(WorkoutStack.ExerciseList)
                WorkoutAction.CancelWorkout -> {
                    navHostController.popBackStack()
                    workoutCreateViewModel.cancelWorkout()
                }

                WorkoutAction.CompleteWorkout -> {
                    navHostController.navigate(WorkoutStack.WorkoutComplete(workoutId = workout.id)) {
                        popUpTo(WorkoutStack.NewWorkout) {
                            inclusive = true
                        }
                    }
                    workoutCreateViewModel.saveWorkout()
                }

                is WorkoutAction.EditSet -> {
                    workoutCreateViewModel.editSet(updatedSet = action.exerciseSet)
                }

                is WorkoutAction.RemoveSet -> {
                    workoutCreateViewModel.removeSet(action.setId)
                }

                is WorkoutAction.AddSet -> {
                    workoutCreateViewModel.addSet(workoutExerciseId = action.exerciseId)
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
        WorkoutCreateScreen(
            navHostController = rememberNavController()
        )
    }
}