package com.example.xhvy.ui.screens.workouts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.xhvy.R
import com.example.xhvy.data.models.SetAction
import com.example.xhvy.navigation.TopNavBar
import com.example.xhvy.ui.components.workouts.WorkoutEntryRow
import com.example.xhvy.view_models.TemplateCreateViewModel

@Composable
fun TemplateCreateScreen(
    modifier: Modifier = Modifier, templateCreateViewModel: TemplateCreateViewModel = viewModel()
) {
    Scaffold(topBar = { TopNavBar(label = "New Template") }) { innerPadding ->
        Column(modifier = modifier.padding(innerPadding)) {
            LazyColumn {
                item {
                    Text(text = templateCreateViewModel.name)
                }
                if (templateCreateViewModel.templateExercises.isEmpty()) {
                    item { Text(text = "Add an Exercise to the Templates") }
                }
                items(items = templateCreateViewModel.templateExercises) { exercise ->
                    WorkoutEntryRow(workoutExercise = exercise) { action: SetAction ->
                        when (action) {
                            SetAction.AddSet -> {}
                            is SetAction.RemoveSet -> {}
                            is SetAction.ToggleComplete -> {}
                            is SetAction.UpdateSet -> {}
                        }
                    }
                }
                item {
                    Column {
                        Button(onClick = { /*TODO*/ }) {
                            Text(text = stringResource(id = R.string.exercise_add))
                        }
                        Button(onClick = { /*TODO*/ }) {
                            Text(text = stringResource(id = R.string.action_cancel))
                        }
                    }
                }
            }
        }
    }

}