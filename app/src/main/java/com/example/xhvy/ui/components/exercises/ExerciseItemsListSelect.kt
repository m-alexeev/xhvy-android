package com.example.xhvy.ui.components.exercises

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.xhvy.R
import com.example.xhvy.data.models.Exercise
import com.example.xhvy.ui.components.general.FaIcon
import com.example.xhvy.ui.components.general.FaIconButton
import com.example.xhvy.ui.components.general.StyledButton
import com.example.xhvy.ui.components.general.StyledSearch
import com.example.xhvy.ui.components.general.StyledTextButton
import com.example.xhvy.ui.view_models.ExercisesViewModel


@Composable
fun ExerciseItemListSelect(
    exercisesViewModel: ExercisesViewModel,
    onCancel: () -> Unit,
    onSelect: (exercise: Exercise) -> Unit,
    onClickNew: () -> Unit,
    onClickAdd: (exercises: List<Exercise>) -> Unit,
    modifier: Modifier = Modifier,
) {
    val exercises by exercisesViewModel.exercises.collectAsState()

    val selectedExercises = remember {
        mutableStateListOf<Exercise>()
    }

    Column(modifier = modifier.padding(horizontal = 8.dp, vertical = 8.dp)) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 12.dp)
        ) {
            FaIconButton(
                iconPainterId = R.drawable.ic_x,
                contentDescription = null,
                onClick = onCancel
            )
            StyledTextButton(
                text = stringResource(id = R.string.exercise_new),
                style = MaterialTheme.typography.titleSmall,
                onClick = { onClickNew() }
            )
            Spacer(modifier = Modifier.weight(1.0f))
            StyledTextButton(
                text = stringResource(id = R.string.exercise_add),
                enabled = selectedExercises.isNotEmpty(),
                onClick = { onClickAdd(selectedExercises) })
        }
        StyledSearch(
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = MaterialTheme.colorScheme.surfaceContainerHighest,
            value = exercisesViewModel.search,
            leadingIcon = {
                FaIcon(
                    iconPainterId = R.drawable.ic_search,
                    modifier = Modifier.size(14.dp),
                    tint = MaterialTheme.colorScheme.outline
                )
            },
            placeHolder = "Search...",
            onValueChange = { value -> exercisesViewModel.updateSearch(value) })

        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 12.dp, bottom = 6.dp)
        ) {
            StyledButton(
                onClick = { /*TODO*/ },
                backgroundColor = MaterialTheme.colorScheme.surfaceContainerHighest,
                shape = MaterialTheme.shapes.small,
                contentPadding = PaddingValues(vertical = 4.dp),
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            ) {
                Text(text = "Categories", style = MaterialTheme.typography.labelMedium)
            }
            StyledButton(
                onClick = { /*TODO*/ },
                backgroundColor = MaterialTheme.colorScheme.surfaceContainerHighest,
                shape = MaterialTheme.shapes.small,
                contentPadding = PaddingValues(vertical = 4.dp),
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Body Parts", style = MaterialTheme.typography.labelMedium)
            }
        }
        LazyColumn {
            itemsIndexed(items = exercises, key = { _, item -> item.id }) { index, item ->
                ExerciseItemRow(exercise = item, onSelect = { exercise ->
                    if (selectedExercises.contains(exercise)) {
                        selectedExercises.remove(exercise)
                    } else {
                        selectedExercises.add(exercise)
                    }
                }, selected = selectedExercises.contains(item))
                if (index < exercises.lastIndex) {
                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 4.dp),
                        color = MaterialTheme.colorScheme.outline
                    )
                }
            }

        }
    }
}


@Composable
fun LazyItemScope.ExerciseItemRow(
    exercise: Exercise,
    onSelect: (exercise: Exercise) -> Unit,
    modifier: Modifier = Modifier,
    selected: Boolean = false,
) {
    val backgroundColor by animateColorAsState(
        targetValue = if (selected) {
            MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
        } else Color.Unspecified, label = ""
    )

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onSelect(exercise) }
            .background(
                backgroundColor,
                shape = RoundedCornerShape(4.dp)
            )
            .padding(4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row {
            Box(
                modifier = Modifier
                    .padding(end = 8.dp)
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.background)
            )
            Column {
                Text(text = exercise.name)
                Text(text = exercise.bodyPart.toString())
            }
        }

        if (selected) {
            FaIcon(iconPainterId = R.drawable.ic_check)
        }
    }
}