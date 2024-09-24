package com.example.xhvy.ui.screens.workouts

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.xhvy.R
import com.example.xhvy.data.models.Exercise
import com.example.xhvy.data.models.ExerciseBodyPart
import com.example.xhvy.data.models.ExerciseCategory
import com.example.xhvy.data.models.ExerciseSet
import com.example.xhvy.data.models.Workout
import com.example.xhvy.data.models.WorkoutExercise
import com.example.xhvy.data.repositories.WorkoutRepository
import com.example.xhvy.ui.components.general.FaIcon
import com.example.xhvy.ui.components.general.StyledTextButton
import com.example.xhvy.ui.components.workouts.WorkoutRowItem
import com.example.xhvy.ui.theme.Orange400
import com.example.xhvy.ui.theme.XhvyTheme
import kotlinx.coroutines.launch
import java.sql.Time
import java.time.Instant

@Composable
fun WorkoutCompleteScreen(
    workoutId: Int,
    navHostController: NavHostController,
    workoutRepository: WorkoutRepository,
    modifier: Modifier = Modifier
) {
    var completedWorkout by remember {
        mutableStateOf<Workout?>(null)
    }
    val coroutine = rememberCoroutineScope()
    LaunchedEffect(workoutId) {
        coroutine.launch {
            workoutRepository.getWorkout(workoutId).collect { fullWorkout ->
                val workout = Workout.from(fullWorkout)
                completedWorkout = workout
            }
        }
    }
    completedWorkout?.let { workout ->
        WorkoutCompleteView(workout = workout, onClickReturn = {
            navHostController.popBackStack()
        })
    } ?: run {
        Text(text = "Loading Workout Data")
    }
}

@Composable
fun WorkoutCompleteView(
    workout: Workout,
    onClickReturn: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(Modifier.padding(bottom = 36.dp)) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Row {
                    Text(text = "Congratulations!", style = MaterialTheme.typography.headlineMedium)
                    FaIcon(
                        iconPainterId = R.drawable.ic_fire,
                        tint = Orange400,
                        modifier = Modifier.size(28.dp)
                    )
                }
                Text(text = "Workout Complete", style = MaterialTheme.typography.labelLarge)
            }
        }
        Row(modifier = Modifier.padding(horizontal = 12.dp)) {
            WorkoutRowItem(
                workout = workout,
                onOptionSelected = {},
                showOptions = false,
            )
        }

        StyledTextButton(
            onClick = onClickReturn,
        ) {
            Text(text = "Return Home", color = MaterialTheme.colorScheme.primary)
        }
    }
}


@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
fun WorkoutCompleteViewPreview() {
    XhvyTheme() {
        WorkoutCompleteView(
            onClickReturn = {},
            workout = Workout(
                1, "New Afternoon Workout", startTime = Time.from(
                    Instant.now()
                ), endTime = Time.from(Instant.now()), workoutExercises = mutableListOf(
                    WorkoutExercise(
                        exerciseSets = mutableStateListOf(
                            ExerciseSet(1, 10, 10f, true),
                            ExerciseSet(2, 10, 10f, true),
                            ExerciseSet(3, 10, 20f, true),
                        ),
                        exercise = Exercise(
                            id = 1,
                            name = "Abs",
                            category = ExerciseCategory.ASSISTED_BODYWEIGHT,
                            bodyPart = ExerciseBodyPart.CORE
                        ),
                        completed = true,
                    )
                )
            )
        )
    }
}