package com.example.xhvy.data.models

import java.time.Instant
import java.util.Date

sealed class TemplateAction {
    data class EditAction(val templateId: Int) : TemplateAction()
    data class RenameAction(val templateId: Int) : TemplateAction()
    data class ShareAction(val templateId: Int) : TemplateAction()
    data class DuplicateAction(val templateId: Int) : TemplateAction()
    data class ArchiveAction(val templateId: Int) : TemplateAction()
    data class DeleteAction(val templateId: Int) : TemplateAction()
}

data class Template(
    val id: Int = 0,
    var name: String,
    var templateExercises: MutableList<WorkoutExercise> = mutableListOf(),
    val active: Boolean = false,
    val isTemplate: Boolean = true,
) {
}