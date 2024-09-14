package com.example.xhvy.view_models

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.example.xhvy.data.models.WorkoutExercise

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

    fun addExercise(workoutExercise: WorkoutExercise) {
        templateExercises.add(workoutExercise)
        Log.d("TEMPLATE", templateExercises.toString() )
    }

    fun removeExercise() {

    }

    fun addSet() {

    }

    fun removeSet() {

    }
}