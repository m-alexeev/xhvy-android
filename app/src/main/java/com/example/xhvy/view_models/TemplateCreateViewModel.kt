package com.example.xhvy.view_models

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.xhvy.data.models.ExerciseSet
import com.example.xhvy.data.models.Template
import com.example.xhvy.data.models.WorkoutExercise
import com.example.xhvy.data.repositories.TemplateRepository
import com.example.xhvy.data.repositories.WorkoutRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TemplateCreateViewModel : ViewModel() {
    private var _name = mutableStateOf("New Template")
    var name: String
        get() = _name.value
        set(value) {
            _name.value = value
        }
    private val _templateExercises: SnapshotStateList<WorkoutExercise> = mutableStateListOf()
    val templateExercises: MutableList<WorkoutExercise>
        get() = _templateExercises

    fun addExercise(newExercise: WorkoutExercise) {
        val exists =
            templateExercises.find { it.exercise.id == newExercise.exercise.id }
        if (exists == null) {
            templateExercises.add(newExercise)
        }
    }

    fun removeExercise(removeExercise: WorkoutExercise) {
        if (templateExercises.contains(removeExercise)) {
            templateExercises.remove(removeExercise)
        }
    }

    fun addSet(templateExercise: WorkoutExercise) {
        val newSet = ExerciseSet(templateExercise.getSets().size)
        templateExercise.exerciseSets.add(newSet)
    }

    fun removeSet(removeSetId: Int, templateExercise: WorkoutExercise) {
        val removeSet = templateExercise.exerciseSets.find { it.id == removeSetId }
        if (removeSet != null) {
            if (templateExercise.exerciseSets.contains(removeSet)) {
                templateExercise.exerciseSets.remove(removeSet)
            }
        }
    }

    fun updateSet(updatedTemplateSet: ExerciseSet, templateExercise: WorkoutExercise) {
        val setToUpdate = templateExercise.exerciseSets.find { it.id == updatedTemplateSet.id }
        if (setToUpdate != null) {
            val setIndex = templateExercise.exerciseSets.indexOf(setToUpdate)
            templateExercise.exerciseSets[setIndex] = updatedTemplateSet
        }
    }

    fun saveTemplate(repository: TemplateRepository) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertTemplate(
                Template(name = name, isTemplate = true),
                workoutExercises = templateExercises
            )
        }
    }
}