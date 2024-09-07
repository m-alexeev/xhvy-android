package com.example.xhvy.ui.components.workouts

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.xhvy.R
import com.example.xhvy.ui.components.general.FaIcon
import com.example.xhvy.ui.components.general.StyledButton
import com.example.xhvy.ui.components.general.StyledInput
import com.example.xhvy.ui.theme.XhvyTheme
import com.example.xhvy.ui.view_models.NewWorkoutViewModel

class TableColumn(
    val title: String?,
    val weight: Float,
)


@Composable
fun WorkoutEntryRow(modifier: Modifier = Modifier, workoutViewModel: NewWorkoutViewModel) {
    val columnWeights = listOf(
        TableColumn("SET", 0.1f),
        TableColumn("PREVIOUS", .3f),
        TableColumn("LBS", 0.2f),
        TableColumn("REPS", 0.2f),
        TableColumn("C", 0.1f)
    )

    val sampleData = (1..3).mapIndexed { index, item -> item }

    Column(modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Bench Press",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
            FaIcon(
                iconPainterId = R.drawable.ic_ellipsis_vertical,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
        }
        Column(modifier = Modifier.fillMaxWidth()) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                columnWeights.forEachIndexed { index, item ->
                    TableHeaderColumn(
                        text = if (index != columnWeights.lastIndex) item.title else null,
                        weight = item.weight,
                        icon = if (index == columnWeights.lastIndex) R.drawable.ic_check else null
                    )
                }
            }
            // Data Rows
            sampleData.forEach {
                var checked by remember {
                    mutableStateOf(false)
                }
                TableRow(it, checked = checked, onChecked = { checked = !checked })
            }

        }
        StyledButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            onClick = { /*TODO*/ },
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
fun TableRow(it: Int, checked: Boolean, onChecked: () -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .height(32.dp)
            .background(if (checked) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surface)
            .padding(vertical = 2.dp),

        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "$it",
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
        StyledInput(
            value = "",
            onValueChange = {},
            modifier = Modifier
                .weight(0.2f),
            backgroundColor = if (checked) MaterialTheme.colorScheme.primaryContainer else null

        )
        StyledInput(
            value = "", onValueChange = {},
            modifier = Modifier
                .weight(0.2f)
                .padding(horizontal = 4.dp),
            backgroundColor = if (checked) MaterialTheme.colorScheme.primaryContainer else null
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
                    .background(if (checked) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceDim)
                    .clickable { onChecked() },
                tint = if (checked) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary,
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
        WorkoutEntryRow(workoutViewModel = viewModel())
    }
}