package com.example.xhvy.ui.components.workouts

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.example.xhvy.R
import com.example.xhvy.ui.components.general.FaIcon
import com.example.xhvy.ui.components.general.StyledInput
import com.example.xhvy.ui.theme.XhvyTheme

class TableColumn(
    val title: String?,
    val weight: Float,
)


@Composable
fun WorkoutEntryRow(modifier: Modifier = Modifier) {
    val columnWeights = listOf(
        TableColumn("SET", 0.1f),
        TableColumn("PREVIOUS", .266f),
        TableColumn("LBS", 0.266f),
        TableColumn("REPS", 0.266f),
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
        LazyColumn(modifier = Modifier.fillMaxWidth()) {

            // Header
            item {
                Row(modifier = Modifier.fillMaxWidth()) {
                    columnWeights.forEachIndexed { index, item ->
                        TableHeaderColumn(
                            text = item.title,
                            weight = item.weight,
                            columnIndex = index
                        )
                    }
                }
            }
            // Rows
            items(sampleData) {
                var checked by remember {
                    mutableStateOf(false)
                }
                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(32.dp)
                        .background(if (checked) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surface)
                        .padding(vertical = 2.dp),

                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "$it", Modifier.weight(0.1f), textAlign = TextAlign.Left)
                    Text(
                        text = "180 lbs x 8",
                        Modifier.weight(0.266f),
                        textAlign = TextAlign.Center
                    )
                    StyledInput(
                        value = "",
                        onValueChange = {},
                        modifier = Modifier
                            .weight(0.266f)
                    )
                    StyledInput(
                        value = "", onValueChange = {},
                        modifier = Modifier
                            .weight(0.266f)
                            .padding(horizontal = 2.dp)
                    )
                    Box(
                        modifier = Modifier
                            .weight(0.1f)
                            .fillParentMaxHeight()
                    ) {
                        FaIcon(
                            modifier = Modifier
                                .size(24.dp)
                                .align(Alignment.Center)
                                .clip(RoundedCornerShape(4.dp))
                                .background(if (checked) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceDim)
                                .clickable { checked = !checked },
                            tint = if (checked) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary,
                            iconPainterId = R.drawable.ic_check,
                            contentDescription = null,
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun RowScope.TableHeaderColumn(
    columnIndex: Int,
    text: String?,
    weight: Float,
) {
    val headerText = text ?: ""
    Text(
        text = headerText,
        modifier = Modifier
            .weight(weight)
            .padding(vertical = 8.dp),
        textAlign = if (columnIndex > 0) TextAlign.Center else TextAlign.Left,
        color = MaterialTheme.colorScheme.secondary,
        fontSize = MaterialTheme.typography.labelSmall.fontSize,
        fontWeight = FontWeight(400)
    )
}

@Composable
fun RowScope.TableDataColumn(
    columnIndex: Int,
    weight: Float,
    content: @Composable() () -> Unit,
) {
    Box(modifier = Modifier.weight(weight)) {
        content()
    }
}

@Preview(showBackground = true)
@Composable
fun WorkoutEntryRowPreview() {
    XhvyTheme {
        WorkoutEntryRow()
    }
}