package com.example.xhvy.ui.screens.workouts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.xhvy.R
import com.example.xhvy.data.models.SetAction
import com.example.xhvy.data.models.Workout
import com.example.xhvy.data.models.WorkoutAction
import com.example.xhvy.domain.utils.calcTimeDifference
import com.example.xhvy.ui.components.general.FaIconButton
import com.example.xhvy.ui.components.general.StyledButton
import com.example.xhvy.ui.components.workouts.WorkoutEntryRow
import java.time.Instant
import java.util.Date

@Composable
fun ActiveWorkout(workout: Workout, onWorkoutAction: (workoutAction: WorkoutAction) -> Unit) {
    Column(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(horizontal = 12.dp)
    ) {
        Row(
            Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            FaIconButton(
                iconPainterId = R.drawable.ic_timer,
                contentDescription = null,
                onClick = {})
            StyledButton(
                Modifier.padding(end = 12.dp),
                onClick = { onWorkoutAction(WorkoutAction.CompleteWorkout) },
                shape = RoundedCornerShape(3.dp)
            ) {
                Text(text = "Finish", style = MaterialTheme.typography.titleSmall)
            }
        }
        // Workout Title
        Column {
            Text(
                text = workout.name,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = calcTimeDifference(
                    Date.from(Instant.now()),
                    workout.startTime
                )
            )
            Text(text = "Notes", style = MaterialTheme.typography.labelMedium)
        }
        // Workout Exercises

        LazyColumn {
            if (workout.workoutExercises.isEmpty()) {
                item {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Add an Exercise to start...",
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center
                    )
                }
            } else {
                items(items = workout.workoutExercises) { workoutExercise ->
                    WorkoutEntryRow(
                        modifier = Modifier.padding(vertical = 12.dp),
                        workoutExercise = workoutExercise,
                        onSetAction = { action: SetAction ->
                            when (action) {
                                is SetAction.AddSet -> {
                                    onWorkoutAction(WorkoutAction.AddSet(workoutExercise.id))
                                }

                                is SetAction.RemoveSet -> {
                                    onWorkoutAction(WorkoutAction.RemoveSet(action.setId))
                                }

                                is SetAction.ToggleComplete -> TODO()
                                is SetAction.UpdateSet -> {
                                    onWorkoutAction(
                                        WorkoutAction.EditSet(
                                            action.exerciseSet
                                        )
                                    )
                                }
                            }
                        },
                    )
                }
            }
            item {
                // Control Buttons
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    StyledButton(
                        onClick = {
                            onWorkoutAction(WorkoutAction.AddExercise)
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(6.dp),
                    ) {
                        Text(
                            text = stringResource(id = R.string.workout_add_exercise),
                            style = MaterialTheme.typography.titleSmall
                        )
                    }
                    Spacer(modifier = Modifier.padding(bottom = 6.dp))
                    StyledButton(
                        onClick = {
                            onWorkoutAction(WorkoutAction.CancelWorkout)
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(6.dp),
                        backgroundColor = MaterialTheme.colorScheme.errorContainer
                    ) {
                        Text(
                            text = stringResource(id = R.string.workout_cancel_workout),
                            style = MaterialTheme.typography.titleSmall
                        )
                    }
                }
            }
        }
    }
}