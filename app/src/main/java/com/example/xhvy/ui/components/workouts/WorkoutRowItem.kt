package com.example.xhvy.ui.components.workouts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.xhvy.R
import com.example.xhvy.data.models.Exercise
import com.example.xhvy.data.models.ExerciseBodyPart
import com.example.xhvy.data.models.ExerciseCategory
import com.example.xhvy.data.models.ExerciseSet
import com.example.xhvy.data.models.Workout
import com.example.xhvy.data.models.WorkoutDropdownAction
import com.example.xhvy.data.models.WorkoutExercise
import com.example.xhvy.data.models.getWorkoutDropdownActions
import com.example.xhvy.domain.utils.calcTimeDifference
import com.example.xhvy.domain.utils.calcTotalWeight
import com.example.xhvy.ui.components.general.FaIconButton
import com.example.xhvy.ui.components.general.FaLabeledIcon
import com.example.xhvy.ui.components.general.StyledDropdownMenu
import com.example.xhvy.ui.theme.XhvyTheme
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun WorkoutRowItem(
    modifier: Modifier = Modifier,
    workout: Workout,
    showOptions: Boolean = true,
    onOptionSelected: (WorkoutDropdownAction) -> Unit
) {
    var expanded by remember {
        mutableStateOf(false)
    }

    Surface(
        modifier = modifier
            .fillMaxWidth(),
        shadowElevation = 6.dp,
        shape = MaterialTheme.shapes.medium
    ) {
        Column(modifier.padding(12.dp)) {
            Row(
                modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = workout.name, style = MaterialTheme.typography.titleMedium)
                if (showOptions) {
                    Box {
                        FaIconButton(
                            modifier = Modifier.height(24.dp),
                            iconPainterId = R.drawable.ic_ellipsis,
                            tint = MaterialTheme.colorScheme.primary,
                            contentDescription = null,
                            backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                            onClick = { expanded = true }
                        )
                        StyledDropdownMenu(
                            options = getWorkoutDropdownActions(),
                            onOptionSelected = { dropdownOption ->
                                onOptionSelected(dropdownOption.action)
                            },
                            expanded = expanded,
                            onDismissRequest = { expanded = false })
                    }
                }
            }
            Text(
                text = SimpleDateFormat("EEEE, dd MMM", Locale.CANADA).format(workout.startTime),
                color = MaterialTheme.colorScheme.outline,
                fontSize = MaterialTheme.typography.bodyMedium.fontSize
            )
            Row(Modifier.fillMaxWidth()) {
                FaLabeledIcon(
                    iconPainterId = R.drawable.ic_clock,
                    tint = MaterialTheme.colorScheme.outline
                ) {
                    Text(
                        modifier = Modifier.padding(start = 4.dp),
                        text = calcTimeDifference(workout.endTime, workout.startTime),
                        style =
                        MaterialTheme.typography.titleSmall.copy(color = MaterialTheme.colorScheme.outline)
                    )
                }
                FaLabeledIcon(
                    modifier = Modifier.padding(start = 24.dp),
                    iconPainterId = R.drawable.ic_weight,
                    tint = MaterialTheme.colorScheme.outline
                ) {
                    Text(
                        modifier = Modifier.padding(start = 4.dp),
                        text = "${calcTotalWeight(workout.workoutExercises)} lbs",
                        style = MaterialTheme.typography.titleSmall.copy(color = MaterialTheme.colorScheme.outline)
                    )
                }
            }
            Column(modifier = Modifier.padding(top = 8.dp)) {
                Row {
                    WorkoutHeaderColumn(column = "Exercise")
                    WorkoutHeaderColumn(column = "Best Set")
                }
                Column {
                    workout.workoutExercises.forEach { workoutExercise ->
                        WorkoutExerciseRow(
                            modifier = Modifier.fillMaxWidth(),
                            workoutExercise = workoutExercise
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun RowScope.WorkoutHeaderColumn(column: String, weight: Float = 0.5f) {
    Text(text = column, Modifier.weight(weight), style = MaterialTheme.typography.labelLarge)
}

@Composable
fun WorkoutExerciseRow(modifier: Modifier = Modifier, workoutExercise: WorkoutExercise) {
    // Guard for empty workouts (shouldn't happen but useful for testing)
    if (workoutExercise.exerciseSets.size == 0) {
        return
    }

    var bestSet = workoutExercise.exerciseSets[0]
    var maxLiftedWeight = 0f
    // Get best set
    for ((index, set) in workoutExercise.exerciseSets.withIndex()) {
        val reps = set.reps ?: 0
        val weight = set.weight ?: 0
        val liftedWeight = reps.toFloat() * weight.toFloat()

        if (liftedWeight > maxLiftedWeight) {
            maxLiftedWeight = liftedWeight
            bestSet = workoutExercise.exerciseSets[index]
        }
    }

    Row(modifier = modifier) {
        Text(
            modifier = Modifier.weight(0.5f),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colorScheme.outline,
            style = MaterialTheme.typography.bodyMedium,
            text = "${workoutExercise.exerciseSets.size} x ${workoutExercise.exercise.name}"
        )
        Text(
            modifier = Modifier.weight(0.5f),
            color = MaterialTheme.colorScheme.outline,
            style = MaterialTheme.typography.bodyMedium,
            text = "${bestSet.reps} x ${bestSet.weight}lb"
        )
    }
}


@Preview(showBackground = true)
@Composable
fun WorkoutRowItemPreview() {
    XhvyTheme {
        Box(
            modifier = Modifier.padding(
                25.dp
            )
        ) {
            WorkoutRowItem(
                workout = Workout(
                    id = 1, name = "Midday Workout", workoutExercises = listOf(
                        WorkoutExercise(
                            id = 1,
                            exercise = Exercise(
                                id = 0,
                                "Bench Press",
                                ExerciseCategory.MACHINE,
                                ExerciseBodyPart.CHEST
                            ),
                            exerciseSets = listOf(
                                ExerciseSet(1, 10, 150f),
                                ExerciseSet(2, 9, 225f),
                                ExerciseSet(3, 10, 200f)
                            ).toMutableStateList()
                        ),
                        WorkoutExercise(
                            id = 1,
                            exercise = Exercise(
                                id = 0,
                                "Triceps Extension",
                                ExerciseCategory.CABLE,
                                ExerciseBodyPart.ARMS
                            ),
                            exerciseSets = listOf(
                                ExerciseSet(1, 10, 200f),
                                ExerciseSet(2, 10, 200f),
                                ExerciseSet(3, 10, 200f)
                            ).toMutableStateList()
                        )
                    ).toMutableList()
                ),
                onOptionSelected = {}
            )
        }
    }
}