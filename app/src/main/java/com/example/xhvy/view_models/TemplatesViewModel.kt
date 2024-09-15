package com.example.xhvy.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.xhvy.data.models.Template
import com.example.xhvy.data.repositories.WorkoutRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TemplatesViewModel(private val workoutRepository: WorkoutRepository) : ViewModel() {
    private var _templates = MutableStateFlow<List<Template>>(emptyList())
    var templates = _templates.asStateFlow()

    init {
        viewModelScope.launch {
            workoutRepository.getTemplates().collect() { templatesList ->
                val templateList: List<Template> = templatesList.map { it.template.toTemplate(it) }
                _templates.value = templateList
            }
        }
    }
}