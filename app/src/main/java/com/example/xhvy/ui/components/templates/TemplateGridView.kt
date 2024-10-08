package com.example.xhvy.ui.components.templates

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.xhvy.R
import com.example.xhvy.data.models.Template
import com.example.xhvy.data.models.TemplateAction
import com.example.xhvy.navigation.WorkoutStack
import com.example.xhvy.ui.components.general.DropdownOption
import com.example.xhvy.view_models.TemplatesViewModel


@Composable
fun TemplateGridView(
    templates: List<Template>,
    navHostController: NavHostController,
    templatesViewModel: TemplatesViewModel
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(count = 2),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        item {
            TemplateContainer {
                Box(
                    Modifier
                        .fillMaxSize()
                        .clip(MaterialTheme.shapes.small)
                        .clickable {
                            navHostController.navigate(WorkoutStack.NewTemplate)
                        },

                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "Tap to Add\n New Template",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
        items(templates) { template ->
            TemplateContainerWithDropDown(
                title = template.name,
                dropDownOptions = getTemplateOptions(template.id),
                onOptionSelected = { dropdownOption ->
                    val action = dropdownOption.action
                    when (action) {
                        is TemplateAction.EditAction -> {}
                        is TemplateAction.DeleteAction -> {
                            templatesViewModel.deleteTemplate(template)
                        }

                        is TemplateAction.ArchiveAction -> {}
                        is TemplateAction.DuplicateAction -> {}
                        is TemplateAction.RenameAction -> {}
                        is TemplateAction.ShareAction -> {}
                    }
                }) {
                Column {
                    val exercisesString =
                        template.templateExercises.joinToString(", ") { it.exercise.name }
                    Text(
                        text = exercisesString,
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.outline,
                        overflow = TextOverflow.Ellipsis
                    )
                }
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
        ),
        DropdownOption(
            label = "Delete",
            icon = R.drawable.ic_trash_2,
            action = TemplateAction.DeleteAction(templateId),
        )
    )
    return templateDropdownOptions
}