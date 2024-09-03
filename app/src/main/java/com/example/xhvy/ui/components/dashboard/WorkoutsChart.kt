package com.example.xhvy.ui.components.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.xhvy.ui.theme.XhvyTheme
import java.time.Instant
import java.util.Date

class Workout(date: Date)

val workouts = listOf(
    Workout(Date.from(Instant.now())),
    Workout(Date.from(Instant.now())),
    Workout(Date.from(Instant.now())),
    Workout(Date.from(Instant.now())),
    Workout(Date.from(Instant.now())),
)


@Composable
fun WorkoutsChart(modifier: Modifier = Modifier) {
    val maxHeight = 10
    val squareSize = 24
    val chartSize = maxHeight * squareSize

    Surface(
        shadowElevation = 12.dp, modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
    ) {
        Column {
            Text(text = "Workouts")

            Column(Modifier.height(chartSize.dp)) {
//                Row {
                workouts.forEachIndexed { index, _ ->
                    Box(
                        modifier = Modifier
                            .size(size = squareSize.dp)
                            .background(Color.Gray)
                    )
                }
//                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WorkoutsChartPreview() {
    XhvyTheme {
        WorkoutsChart()
    }
}