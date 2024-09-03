package com.example.xhvy.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.xhvy.R
import com.example.xhvy.ui.components.dashboard.ProfileHeading
import com.example.xhvy.ui.theme.XhvyTheme

@Composable
fun Dashboard(modifier: Modifier = Modifier) {
    Scaffold(topBar = {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.dashboard_heading),
                style = MaterialTheme.typography.titleLarge
            )
            IconButton(onClick = { }) {
                Icon(Icons.Default.Settings, contentDescription = null)
            }
        }
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
        Dashboard()
    }
}