package com.example.xhvy.data.models

sealed class TemplateAction {
    data object DeleteAction : TemplateAction()
    data class EditAction(val templateId: Int) : TemplateAction()
}


data class Template(val id: Int) {
}