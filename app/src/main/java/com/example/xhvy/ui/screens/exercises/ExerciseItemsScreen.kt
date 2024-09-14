package com.example.xhvy.ui.screens.exercises

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.xhvy.R
import com.example.xhvy.navigation.ExerciseStack
import com.example.xhvy.navigation.TopNavBar
import com.example.xhvy.ui.components.exercises.ExerciseItemsList
import com.example.xhvy.ui.components.general.FaIconButton
import com.example.xhvy.ui.theme.XhvyTheme
import com.example.xhvy.view_models.ExercisesViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ExerciseItemScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    exercisesViewModel: ExercisesViewModel = viewModel()
) {
    val exercises by exercisesViewModel.exercises.collectAsState()

    Scaffold(topBar = {
        TopNavBar(
            label = "Exercises",
            trailingButton = { CreateExerciseButton(navController) })
    }) { innerPadding ->
        Column(modifier.padding(innerPadding)) {
            ExerciseItemsList(
                exercises = exercises,
                exercisesViewModel = exercisesViewModel,
            )
        }
    }
}

@Composable
fun CreateExerciseButton(navController: NavHostController) {
    FaIconButton(
        iconPainterId = R.drawable.ic_plus,
        contentDescription = null,
        onClick = { navController.navigate(ExerciseStack.NewExercise) })
}

@Preview(showBackground = true, backgroundColor = 0xEEFFFBFE)
@Composable
fun ExerciseScreenPreview() {
    XhvyTheme {
        ExerciseItemScreen(navController = rememberNavController())
    }
}