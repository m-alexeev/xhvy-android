package com.example.xhvy.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.xhvy.ui.theme.XhvyTheme

@Composable
fun NewExerciseScreen(navHostController: NavHostController, modifier: Modifier = Modifier) {


    Box(
        Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        Text(text = "New Exercise")
        

        Box(Modifier.align(Alignment.BottomCenter)) {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = { navHostController.popBackStack() },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
                ) {
                    Text(text = "Cancel")
                }
                Button(
                    onClick = {},
                ) {
                    Text(text = "Create")
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun NewExerciseScreenPreview() {
    XhvyTheme {
        NewExerciseScreen(navHostController = rememberNavController())
    }
}

