package com.example.xhvy.ui.screens.exercises

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.xhvy.data.models.Exercise
import com.example.xhvy.data.models.exerciseBodyParts
import com.example.xhvy.data.models.exerciseCategories
import com.example.xhvy.navigation.TopNavBar
import com.example.xhvy.ui.components.general.DialogWithList
import com.example.xhvy.ui.theme.XhvyTheme
import com.example.xhvy.ui.view_models.ExercisesViewModel
import com.example.xhvy.ui.view_models.NewExerciseViewModel

@Composable
fun NewExerciseScreen(
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
    exercisesViewModel: ExercisesViewModel = viewModel(),
    newExerciseViewModel: NewExerciseViewModel = viewModel()
) {
    var openCategory by remember { mutableStateOf(false) }
    var openBodyPart by remember {
        mutableStateOf(false)
    }

    Scaffold(topBar = { TopNavBar(label = "New Exercise") }) { innerPadding ->
        Box(
            Modifier
                .padding(innerPadding)
                .padding(top = 12.dp)
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(horizontal = 12.dp)) {
                TextField(label = { Text(text = "Name") },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text(text = "Exercise Name") },
                    value = newExerciseViewModel.name,
                    onValueChange = { newExerciseViewModel.updateName(it) })
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 32.dp)
                ) {
                    Text(text = "Category")
                    Text(
                        text = if (newExerciseViewModel.category != null) newExerciseViewModel.category.toString() else "None",
                        modifier = Modifier.clickable { openCategory = true },
                        color = if (newExerciseViewModel.category != null) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.surfaceDim
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp)
                ) {
                    Text(text = "Body Part")
                    Text(
                        text = if (newExerciseViewModel.bodyPart != null) newExerciseViewModel.bodyPart.toString() else "None",
                        modifier = Modifier.clickable { openBodyPart = true },
                        color = if (newExerciseViewModel.bodyPart != null) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.surfaceDim
                    )
                }
            }
            if (openCategory) {
                DialogWithList(
                    header = "Category",
                    selected = newExerciseViewModel.category,
                    onDismissRequest = { openCategory = false },
                    onSelect = { item -> newExerciseViewModel.updateCategory(item) },
                    items = exerciseCategories
                )
            }
            if (openBodyPart) {
                DialogWithList(
                    header = "Body Part",
                    selected = newExerciseViewModel.bodyPart,
                    onDismissRequest = { openBodyPart = false },
                    onSelect = { item -> newExerciseViewModel.updateBodyPart(item) },
                    items = exerciseBodyParts
                )
            }
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
                        enabled = newExerciseViewModel.name.isNotEmpty() && newExerciseViewModel.bodyPart != null && newExerciseViewModel.category != null,
                        onClick = {
                            if (newExerciseViewModel.name.isNotEmpty() && newExerciseViewModel.bodyPart != null && newExerciseViewModel.category != null) {
                                val exercise = Exercise(
                                    name = newExerciseViewModel.name,
                                    category = newExerciseViewModel.category!!,
                                    bodyPart = newExerciseViewModel.bodyPart!!,
                                    deletable = true
                                )
                                exercisesViewModel.addExercise(exercise)
                                navHostController.popBackStack()
                            }
                        },
                    ) {
                        Text(text = "Create")
                    }
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

