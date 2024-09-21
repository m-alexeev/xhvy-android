package com.example.xhvy.ui.components.workouts

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.xhvy.domain.utils.differenceResult
import com.example.xhvy.ui.theme.XhvyTheme
import kotlinx.coroutines.delay
import java.sql.Time
import java.time.Instant
import java.util.Date

@Composable
fun WorkoutDurationCounter(startTime: Date, modifier: Modifier = Modifier) {
    var timeDifference by remember {
        mutableStateOf("")
    }


    fun advanceTimer() {
        val currentTime = System.currentTimeMillis()
        val time = currentTime - startTime.toInstant().toEpochMilli()
        timeDifference = differenceResult(time)
    }

    LaunchedEffect(Unit) {
        while (true) {
            advanceTimer()
            delay(1000)
        }
    }
    Text(text = timeDifference, modifier = modifier)

}


@Preview(showBackground = true)
@Composable
fun WorkoutDurationCounterPreview() {
    XhvyTheme {
        WorkoutDurationCounter(startTime = Time.from(Instant.now()))
    }
}