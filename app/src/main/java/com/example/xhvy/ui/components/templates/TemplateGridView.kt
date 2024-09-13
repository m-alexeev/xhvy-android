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
import com.example.xhvy.ui.components.general.DropdownMenuItemVariant
import com.example.xhvy.ui.components.general.DropdownOption


@Composable
fun TemplateGridView() {

    val sampleList = (1..10).toList()
    LazyVerticalGrid(
        columns = GridCells.Fixed(count = 2),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(sampleList) { index ->
            TemplateContainer<TemplateAction>(
                title = "Template",
                dropDownOptions = getTemplateOptions(index),
                onOptionSelected = { dropdownOption ->
                    val action = dropdownOption.action
                    when (action) {
                        is TemplateAction.EditAction -> {}
                        is TemplateAction.DeleteAction -> {}
                        is TemplateAction.ArchiveAction -> {}
                        is TemplateAction.DuplicateAction -> {}
                        is TemplateAction.RenameAction -> {}
                        is TemplateAction.ShareAction -> {}
                    }
                }) {
                Text(text = "Sample Text")
            }
        }
    }
}

fun getTemplateOptions(templateId: Int): List<DropdownOption<TemplateAction>> {
    val templateDropdownOptions = listOf<DropdownOption<TemplateAction>>(
        DropdownOption(
            label = "Edit",
            icon = R.drawable.ic_pencil,
            action = TemplateAction.EditAction(templateId)
        ),
        DropdownOption(
            label = "Rename",
            icon = R.drawable.ic_pencil,
            action = TemplateAction.RenameAction(templateId)
        ),
        DropdownOption(
            label = "Share",
            icon = R.drawable.ic_share_2,
            action = TemplateAction.ShareAction(templateId)
        ),
        DropdownOption(
            label = "Duplicate",
            icon = R.drawable.ic_copy,
            action = TemplateAction.DuplicateAction(templateId)
        ),
        DropdownOption(
            label = "Archive",
            icon = R.drawable.ic_archive,
            action = TemplateAction.ArchiveAction(templateId),
            variant = DropdownMenuItemVariant.WARNING
        ),
        DropdownOption(
            label = "Delete",
            icon = R.drawable.ic_trash_2,
            action = TemplateAction.DeleteAction(templateId),
            variant = DropdownMenuItemVariant.DESTRUCTIVE
        ),

        )
    return templateDropdownOptions
}