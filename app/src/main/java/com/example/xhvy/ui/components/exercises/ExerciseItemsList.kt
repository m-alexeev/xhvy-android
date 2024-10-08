package com.example.xhvy.ui.components.exercises

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.xhvy.R
import com.example.xhvy.data.models.Exercise
import com.example.xhvy.ui.components.general.FaIconButton
import com.example.xhvy.ui.theme.XhvyTheme
import com.example.xhvy.view_models.ExercisesViewModel
import com.example.xhvy.view_models.getExercises
import java.util.Locale


@Composable
fun ExerciseItemsList(
    exercises: List<Exercise>,
    exercisesViewModel: ExercisesViewModel?,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier) {
        items(items = exercises, key = { it.id }) { exercise ->
            ExerciseRowItem(
                exercise = exercise,
                onDelete = {
                    exercisesViewModel?.deleteExercise(exercise)
                })
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
                .padding(horizontal = 12.dp)
        )
        content()
    }
}


@Preview(showBackground = true)
@Composable
fun ExerciseItemsListPreview() {
    XhvyTheme {
        ExerciseItemsList(
            getExercises,
            null
        )
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