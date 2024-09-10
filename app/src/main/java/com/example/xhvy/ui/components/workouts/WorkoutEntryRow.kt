package com.example.xhvy.ui.components.workouts

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.xhvy.R
import com.example.xhvy.data.models.Exercise
import com.example.xhvy.data.models.ExerciseBodyPart
import com.example.xhvy.data.models.ExerciseCategory
import com.example.xhvy.data.models.ExerciseSet
import com.example.xhvy.data.models.SetAction
import com.example.xhvy.data.models.WorkoutExercise
import com.example.xhvy.domain.utils.DecimalFormatter
import com.example.xhvy.ui.components.general.DecimalStyledInput
import com.example.xhvy.ui.components.general.FaIcon
import com.example.xhvy.ui.components.general.FaIconButton
import com.example.xhvy.ui.components.general.StyledButton
import com.example.xhvy.ui.components.general.StyledInput
import com.example.xhvy.ui.theme.XhvyTheme

class TableColumn(
    val title: String? = null,
    val weight: Float,
    val icon: Int? = null,
)


@Composable
fun WorkoutEntryRow(
    modifier: Modifier = Modifier,
    workoutExercise: WorkoutExercise,
    onSetAction: (action: SetAction) -> Unit,
) {
    val columnWeights = listOf(
        TableColumn("SET", 0.1f),
        TableColumn("PREVIOUS", .3f),
        TableColumn("LBS", 0.2f),
        TableColumn("REPS", 0.2f),
        TableColumn(icon = R.drawable.ic_check, weight = 0.1f)
    )
    var expanded by remember {
        mutableStateOf(false)
    }


    Column(modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = workoutExercise.exercise.name,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Box(modifier = Modifier) {
                FaIconButton(
                    iconPainterId = R.drawable.ic_ellipsis_vertical,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    onClick = { expanded = true }
                )
            }
        }
        Column(modifier = Modifier.fillMaxWidth()) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                columnWeights.forEach { item ->
                    TableHeaderColumn(
                        text = item.title,
                        weight = item.weight,
                        icon = item.icon
                    )
                }
            }
            // Data Rows
            workoutExercise.exerciseSets.forEachIndexed { index, item ->
                TableRow(
                    index,
                    item,
                    onSetAction = {
                        onSetAction(it)
                    },
                )
            }

        }
        StyledButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            onClick = { onSetAction(SetAction.AddSet) },
            contentPadding = PaddingValues(vertical = 2.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            FaIcon(
                modifier = Modifier.size(18.dp),
                iconPainterId = R.drawable.ic_plus,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
            )
            Text(
                text = "Add Row",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary,
            )
        }
    }
}


@Composable
fun RowScope.TableHeaderColumn(
    text: String? = null,
    icon: Int? = null,
    weight: Float,
) {
    if (text != null) {
        Text(
            text = text,
            modifier = Modifier
                .weight(weight)
                .padding(vertical = 8.dp),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.secondary,
            fontSize = MaterialTheme.typography.labelSmall.fontSize,
            fontWeight = FontWeight(400)
        )
    }
    if (icon != null) {
        FaIcon(
            iconPainterId = icon,
            contentDescription = null,
            modifier = Modifier
                .weight(weight)
                .size(18.dp),

            tint = MaterialTheme.colorScheme.secondary,
        )
    }
}


@Composable
fun TableRow(
    index: Int,
    set: ExerciseSet,
    onSetAction: (action: SetAction) -> Unit,
) {
    Row(
        Modifier
            .fillMaxWidth()
            .height(32.dp)
            .background(if (set.completed) MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.8f) else MaterialTheme.colorScheme.surface)
            .padding(vertical = 2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = (index + 1).toString(),
            Modifier.weight(0.1f),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.outline
        )
        Text(
            text = "180 lbs x 8",
            Modifier.weight(0.3f),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.outline
        )
        DecimalStyledInput(
            initialValue = "${if (set.weight != null) set.weight else ""}",
            onValueChange = { weight ->
                val newWeight = DecimalFormatter().cleanup(weight).toFloatOrNull()

                onSetAction(
                    SetAction.UpdateSet(
                        index,
                        set.copy(weight = if (newWeight == 0f) null else newWeight)
                    )
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal
            ),
            modifier = Modifier
                .weight(0.2f),
            backgroundColor = if (set.completed) MaterialTheme.colorScheme.primaryContainer else null,
            decimalFormatter = DecimalFormatter()
        )
        StyledInput(
            value = "${if (set.reps != null) set.reps else ""}",
            onValueChange = { reps ->
                val newValue = reps.filter { char -> char.isDigit() }
                onSetAction(
                    SetAction.UpdateSet(
                        index,
                        set.copy(reps = newValue.toIntOrNull())
                    )
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier
                .weight(0.2f)
                .padding(horizontal = 4.dp),
            backgroundColor = if (set.completed) MaterialTheme.colorScheme.primaryContainer else null
        )
        Box(
            modifier = Modifier
                .weight(0.1f)
        ) {
            FaIcon(
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.Center)
                    .clip(RoundedCornerShape(4.dp))
                    .background(if (set.completed) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceDim)
                    .clickable {
                        onSetAction(
                            SetAction.UpdateSet(
                                index,
                                set.copy(completed = !set.completed)
                            )
                        )
                    },
                tint = if (set.completed) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary,
                iconPainterId = R.drawable.ic_check,
                contentDescription = null,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WorkoutEntryRowPreview() {
    XhvyTheme {
        WorkoutEntryRow(
            workoutExercise = WorkoutExercise(
                0,
                Exercise(0, "Test", ExerciseCategory.CARDIO, ExerciseBodyPart.CARDIO)
            ),
            onSetAction = {}
        )
    }
}