package com.example.xhvy.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.xhvy.navigation.TopNavBar
import com.example.xhvy.ui.components.dashboard.ProfileHeading
import com.example.xhvy.ui.theme.XhvyTheme
import com.example.xhvy.ui.view_models.NewWorkoutViewModel

@Composable
fun DashboardScreen(modifier: Modifier = Modifier) {
    Scaffold(topBar = {
        TopNavBar(label = "Dashboard")
    })
    { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(innerPadding)
                .padding(horizontal = 12.dp, vertical = 12.dp)
        ) {
            ProfileHeading(name = "Misha Alexeev", workouts = 312)
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DashboardPreview() {
    XhvyTheme {
        DashboardScreen()
    }
}