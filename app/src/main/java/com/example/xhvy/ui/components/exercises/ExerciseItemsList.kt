package com.example.xhvy.ui.components.exercises

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.xhvy.R
import com.example.xhvy.data.models.Exercise
import com.example.xhvy.data.models.ExerciseBodyType
import com.example.xhvy.data.models.ExerciseCategory
import com.example.xhvy.ui.theme.XhvyTheme
import java.util.Locale

val exercises = listOf(
    Exercise(1, "Bench Press", ExerciseCategory.BARBELL, ExerciseBodyType.CHEST),
    Exercise(2, "Squat", ExerciseCategory.BARBELL, ExerciseBodyType.LEGS),
    Exercise(3, "Deadlift", ExerciseCategory.BARBELL, ExerciseBodyType.BACK),
    Exercise(4, "Overhead Press", ExerciseCategory.BARBELL, ExerciseBodyType.SHOULDERS),
    Exercise(5, "Barbell Row", ExerciseCategory.BARBELL, ExerciseBodyType.BACK),

    Exercise(6, "Dumbbell Curl", ExerciseCategory.DUMBBELL, ExerciseBodyType.ARMS),
    Exercise(7, "Dumbbell Fly", ExerciseCategory.DUMBBELL, ExerciseBodyType.CHEST),
    Exercise(8, "Dumbbell Lateral Raise", ExerciseCategory.DUMBBELL, ExerciseBodyType.SHOULDERS),
    Exercise(9, "Dumbbell Shoulder Press", ExerciseCategory.DUMBBELL, ExerciseBodyType.SHOULDERS),
    Exercise(10, "Dumbbell Tricep Extension", ExerciseCategory.DUMBBELL, ExerciseBodyType.ARMS),

    Exercise(11, "Leg Press", ExerciseCategory.MACHINE, ExerciseBodyType.LEGS),
    Exercise(12, "Chest Press", ExerciseCategory.MACHINE, ExerciseBodyType.CHEST),
    Exercise(13, "Lat Pulldown", ExerciseCategory.MACHINE, ExerciseBodyType.BACK),
    Exercise(14, "Cable Row", ExerciseCategory.MACHINE, ExerciseBodyType.BACK),
    Exercise(15, "Leg Curl", ExerciseCategory.MACHINE, ExerciseBodyType.LEGS),

    Exercise(16, "Weighted Pull-Up", ExerciseCategory.WEIGHTED_BODYWEIGHT, ExerciseBodyType.BACK),
    Exercise(17, "Weighted Dip", ExerciseCategory.WEIGHTED_BODYWEIGHT, ExerciseBodyType.CHEST),
    Exercise(18, "Weighted Push-Up", ExerciseCategory.WEIGHTED_BODYWEIGHT, ExerciseBodyType.CHEST),
    Exercise(19, "Assisted Pull-Up", ExerciseCategory.ASSISTED_BODYWEIGHT, ExerciseBodyType.BACK),
    Exercise(20, "Assisted Dip", ExerciseCategory.ASSISTED_BODYWEIGHT, ExerciseBodyType.CHEST),

    Exercise(21, "Push-Up", ExerciseCategory.REPS, ExerciseBodyType.CHEST),
    Exercise(22, "Sit-Up", ExerciseCategory.REPS, ExerciseBodyType.CORE),
    Exercise(23, "Jumping Jack", ExerciseCategory.REPS, ExerciseBodyType.FULL_BODY),
    Exercise(24, "Plank", ExerciseCategory.DURATION, ExerciseBodyType.CORE),
    Exercise(25, "Wall Sit", ExerciseCategory.DURATION, ExerciseBodyType.LEGS),

    Exercise(26, "Running", ExerciseCategory.CARDIO, ExerciseBodyType.CARDIO),
    Exercise(27, "Cycling", ExerciseCategory.CARDIO, ExerciseBodyType.CARDIO),
    Exercise(28, "Rowing", ExerciseCategory.CARDIO, ExerciseBodyType.FULL_BODY),
    Exercise(29, "Swimming", ExerciseCategory.CARDIO, ExerciseBodyType.FULL_BODY),
    Exercise(30, "Jump Rope", ExerciseCategory.CARDIO, ExerciseBodyType.FULL_BODY)
)

@Composable
fun ExerciseItemsList(exercises: List<Exercise>, filter: String, modifier: Modifier = Modifier) {

    LazyColumn(modifier) {
        items(exercises) { exercise ->
            if (filter.isEmpty() || exercise.name.lowercase(Locale.getDefault())
                    .contains(filter.lowercase(Locale.getDefault())) || exercise.category.name.lowercase(
                    Locale.getDefault()
                )
                    .contains(
                        filter.lowercase(Locale.getDefault())
                    )
            ) {
                ExerciseRowItem(exercise = exercise)
            }
        }
    }
}

@Composable
fun TitledSection(
    @StringRes title: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(modifier) {
        Text(
            stringResource(id = title).uppercase(Locale.getDefault()),
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier
                .paddingFromBaseline(top = 40.dp, bottom = 8.dp)
                .padding(horizontal = 12.dp)
        )
        content()
    }
}


@Preview(showBackground = true)
@Composable
fun ExerciseItemsListPreview() {
    XhvyTheme {
        ExerciseItemsList(exercises, filter = "")
    }
}

@Preview(showBackground = true)
@Composable
fun ExerciseItemsListTitledPreview() {
    XhvyTheme {
        TitledSection(
            title = R.string.exercise_list_title,
            content = { ExerciseItemsListPreview() })
    }
}