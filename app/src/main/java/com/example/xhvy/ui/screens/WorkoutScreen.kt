package com.example.xhvy.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.xhvy.navigation.WorkoutStack
import com.example.xhvy.ui.theme.XhvyTheme

@Composable
fun WorkoutScreen(navController: NavHostController, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .safeContentPadding()
    ) {
        Text(text = "WorkoutScreen")
        Button(
            onClick = { navController.navigate(WorkoutStack.NewWorkout) },
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(text = "Create Empty Workout")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WorkoutScreenPreview() {
    XhvyTheme {
        WorkoutScreen(rememberNavController())
    }
}