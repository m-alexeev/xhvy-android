package com.example.xhvy.ui.screens.exercises

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.xhvy.navigation.ExerciseStack
import com.example.xhvy.ui.components.exercises.ExerciseItemListSelect
import com.example.xhvy.view_models.ExercisesViewModel

@Composable
fun SelectExerciseModal(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    exercisesViewModel: ExercisesViewModel = viewModel(),
) {
    Dialog(onDismissRequest = { navController.popBackStack() }) {
        Card(
            modifier = modifier
                .fillMaxHeight()
                .padding(vertical = 12.dp)
        ) {
            ExerciseItemListSelect(
                exercisesViewModel = exercisesViewModel,
                onSelect = {},
                onCancel = { navController.popBackStack() },
                onConfirm = { exercises ->
                    navController.previousBackStackEntry?.savedStateHandle?.set(
                        "selectedExercises",
                        exercises.toList()
                    )
                    navController.popBackStack()
                },
                onClickNew = {
                    navController.navigate(ExerciseStack.NewExercise)
                }

            )
        }
    }
}

