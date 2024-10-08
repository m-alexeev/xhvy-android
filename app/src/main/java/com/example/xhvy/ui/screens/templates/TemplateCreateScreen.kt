package com.example.xhvy.ui.screens.templates

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.xhvy.R
import com.example.xhvy.data.models.Exercise
import com.example.xhvy.data.models.ExerciseAction
import com.example.xhvy.data.models.ExerciseSet
import com.example.xhvy.data.models.SetAction
import com.example.xhvy.data.models.WorkoutExercise
import com.example.xhvy.data.repositories.TemplateRepository
import com.example.xhvy.navigation.TopNavBar
import com.example.xhvy.navigation.WorkoutStack
import com.example.xhvy.ui.components.general.FaIconButton
import com.example.xhvy.ui.components.general.StyledTextButton
import com.example.xhvy.ui.components.templates.TemplateName
import com.example.xhvy.ui.components.workouts.WorkoutEntryRow
import com.example.xhvy.view_models.TemplateCreateViewModel

@Composable
fun TemplateCreateScreen(
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
    templateCreateViewModel: TemplateCreateViewModel = viewModel(),
    templateRepository: TemplateRepository,
) {
    val savedStateHandle = remember { navHostController.currentBackStackEntry?.savedStateHandle }
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(Unit) {
        savedStateHandle?.getLiveData<List<Exercise>>("selectedExercises")
            ?.observe(lifecycleOwner) { selectedItems ->
                selectedItems.forEach { exercise: Exercise ->
                    val workoutExercise =
                        WorkoutExercise(
                            exercise = exercise,
                            exerciseSets = mutableStateListOf(ExerciseSet(0))
                        )
                    templateCreateViewModel.addExercise(workoutExercise)
                }
            }
    }

    Scaffold(topBar = {
        TopNavBar(label = "New Template", leadingButton = {
            FaIconButton(
                iconPainterId = R.drawable.ic_x,
                onClick = {
                    navHostController.popBackStack()
                })
        }, trailingButton = {
            StyledTextButton(onClick = {
                templateCreateViewModel.saveTemplate(templateRepository)
                navHostController.popBackStack()
            }) {
                Text(
                    text = stringResource(id = R.string.action_save),
                    style = MaterialTheme.typography.titleMedium
                )
            }
        })
    }) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .padding(horizontal = 12.dp, vertical = 24.dp)
        ) {
            LazyColumn {
                item(key = "NameField") {
                    TemplateName(
                        name = templateCreateViewModel.name,
                        onNameUpdate = { newName -> templateCreateViewModel.name = newName })
                }
                if (templateCreateViewModel.templateExercises.isEmpty()) {
                    item { Spacer(modifier = Modifier.padding(vertical = 24.dp)) }
                }
                items(
                    items = templateCreateViewModel.templateExercises,
                    key = { it.exercise.name }) { exercise ->
                    WorkoutEntryRow(
                        workoutExercise = exercise,
                        template = true,
                        onSetAction = { action: SetAction ->
                            when (action) {
                                SetAction.AddSet -> {
                                    templateCreateViewModel.addSet(exercise)
                                }

                                is SetAction.RemoveSet -> {
                                    templateCreateViewModel.removeSet(action.setId, exercise)
                                }

                                is SetAction.UpdateSet -> {
                                    templateCreateViewModel.updateSet(action.exerciseSet, exercise)
                                }
                            }
                        },
                        onExerciseAction = { action: ExerciseAction ->
                            when (action) {
                                is ExerciseAction.DeleteExercise -> {
                                    templateCreateViewModel.removeExercise(exercise)
                                }
                            }
                        }
                    )
                }
                item {
                    StyledTextButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 12.dp),
                        shape = MaterialTheme.shapes.small,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.background,
                            contentColor = MaterialTheme.colorScheme.primary
                        ),
                        onClick = { navHostController.navigate(WorkoutStack.ExerciseList) }) {
                        Text(
                            textAlign = TextAlign.Center,
                            text = stringResource(id = R.string.workout_add_exercise),
                            style = MaterialTheme.typography.titleMedium,
                        )
                    }
                }
            }
        }
    }

}