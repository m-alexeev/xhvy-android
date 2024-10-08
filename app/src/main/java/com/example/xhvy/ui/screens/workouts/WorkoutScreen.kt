package com.example.xhvy.ui.screens.workouts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.xhvy.R
import com.example.xhvy.navigation.TopNavBar
import com.example.xhvy.navigation.WorkoutStack
import com.example.xhvy.ui.components.general.FaIconButton
import com.example.xhvy.ui.components.general.StyledButton
import com.example.xhvy.ui.components.templates.TemplateGridView
import com.example.xhvy.view_models.TemplatesViewModel
import com.example.xhvy.view_models.WorkoutCreateViewModel


@Composable
fun WorkoutScreen(
    navController: NavHostController,
    workoutCreateViewModel: WorkoutCreateViewModel,
    templatesViewModel: TemplatesViewModel,
    modifier: Modifier = Modifier
) {
    val activeWorkout by workoutCreateViewModel.workout.collectAsState()
    val templates by templatesViewModel.templates.collectAsState()
    val workoutIsActive by remember(activeWorkout) {
        derivedStateOf { activeWorkout != null }
    }

    Scaffold(
        topBar = { TopNavBar(label = "Workouts") },
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(innerPadding)
                .padding(horizontal = 12.dp, vertical = 12.dp)
        ) {
            Text(
                text = "QUICK START",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.outline
            )
            StyledButton(
                onClick = {
                    if (!workoutIsActive) {
                        workoutCreateViewModel.createWorkout()
                    }
                    navController.navigate(WorkoutStack.NewWorkout)
                },
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(text = stringResource(id = if (workoutIsActive) R.string.workout_continue else R.string.workout_empty_create))
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp, bottom = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "MY TEMPLATES",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.outline
                )
                FaIconButton(
                    modifier = Modifier.size(24.dp),
                    iconPainterId = R.drawable.ic_plus,
                    contentDescription = null,
                    onClick = {
                        navController.navigate(WorkoutStack.NewTemplate)
                    })
            }
            TemplateGridView(templates, navController, templatesViewModel)
        }
    }

}