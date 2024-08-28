package com.example.xhvy.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.xhvy.R
import com.example.xhvy.ui.components.exercises.ExerciseItemsList
import com.example.xhvy.ui.components.exercises.TitledSection
import com.example.xhvy.ui.components.exercises.exercises
import com.example.xhvy.ui.components.general.Searchbar
import com.example.xhvy.ui.theme.XhvyTheme

@Composable
fun ExerciseItemScreen(modifier: Modifier = Modifier) {
    var search by rememberSaveable {
        mutableStateOf("")
    }
    Column(modifier) {
        Searchbar(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 12.dp),
            search = search,
            onSearchChange = { newSearchValue -> search = newSearchValue })
        TitledSection(
            title = R.string.exercise_list_title,
            content = { ExerciseItemsList(exercises = exercises, filter = search, Modifier.fillMaxHeight()) }
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xEEFFFBFE)
@Composable
fun ExerciseScreenPreview() {
    XhvyTheme {
        ExerciseItemScreen()
    }
}