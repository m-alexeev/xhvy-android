package com.example.xhvy.ui.components.templates

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.xhvy.R
import com.example.xhvy.data.models.TemplateAction
import com.example.xhvy.ui.components.general.DropdownOption


@Composable
fun TemplateGridView() {
    val templateDropdownOptions = getTemplateOptions()

    val sampleList = (1..10).toList()
    LazyVerticalGrid(
        columns = GridCells.Fixed(count = 2),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(sampleList) { _ ->
            TemplateContainer<TemplateAction>(
                title = "Template",
                dropDownOptions = templateDropdownOptions,
                onOptionSelected = { dropdownOption ->
                    when (dropdownOption.action) {
                        is TemplateAction.EditAction -> {}
                        is TemplateAction.DeleteAction -> {}
                    }
                }) {
                Text(text = "Sample Text")
            }
        }
    }
}

fun getTemplateOptions(): List<DropdownOption<TemplateAction>> {
    val templateDropdownOptions = listOf<DropdownOption<TemplateAction>>(
        DropdownOption(
            label = "Edit",
            icon = R.drawable.ic_pencil,
            action = TemplateAction.DeleteAction
        ),
        DropdownOption(
            label = "Edit",
            icon = R.drawable.ic_trash_2,
            action = TemplateAction.EditAction(1)
        ),
    )
    return templateDropdownOptions
}