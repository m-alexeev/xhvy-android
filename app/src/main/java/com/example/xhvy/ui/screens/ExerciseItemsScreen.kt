package com.example.xhvy.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.xhvy.R
import com.example.xhvy.ui.components.exercises.ExerciseItemsList
import com.example.xhvy.ui.components.exercises.TitledSection
import com.example.xhvy.ui.components.general.Searchbar
import com.example.xhvy.ui.theme.XhvyTheme
import com.example.xhvy.ui.view_models.ExercisesViewModel
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun ExerciseItemScreen(
    modifier: Modifier = Modifier,
    exercisesViewModel: ExercisesViewModel = viewModel()
) {
    XhvyTheme {
        Column(modifier) {
            Searchbar(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 12.dp),
                search = exercisesViewModel.search,
                onSearchChange = { newSearchValue -> exercisesViewModel.updateSearch(newSearchValue) })
            TitledSection(
                title = R.string.exercise_list_title,
                content = {
                    ExerciseItemsList(
                        exercises = exercisesViewModel.exercises,
                        Modifier.fillMaxHeight()
                    )
                }
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xEEFFFBFE)
@Composable
fun ExerciseScreenPreview() {
    XhvyTheme {
        ExerciseItemScreen()
    }
}