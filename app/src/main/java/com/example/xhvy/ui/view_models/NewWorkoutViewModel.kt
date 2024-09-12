package com.example.xhvy.ui.view_models

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.xhvy.data.entities.ExerciseSetEntity
import com.example.xhvy.data.entities.WorkoutEntity
import com.example.xhvy.data.entities.WorkoutExerciseEntity
import com.example.xhvy.data.models.ExerciseSet
import com.example.xhvy.data.models.Workout
import com.example.xhvy.data.models.WorkoutExercise
import com.example.xhvy.data.repositories.WorkoutRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NewWorkoutViewModel(private val workoutRepository: WorkoutRepository) : ViewModel() {
    private val _workout = MutableStateFlow<Workout?>(null)
    val workout: StateFlow<Workout?> = _workout.asStateFlow()

    init {
        viewModelScope.launch {
            workoutRepository.getActiveWorkout().collect { activeWorkout ->
                activeWorkout?.let {
                    _workout.value = activeWorkout.workout.toWorkout(activeWorkout)
                }
            }
        }
    }

    fun createWorkout() {
        viewModelScope.launch {
            val workoutEntity = WorkoutEntity(name = "New Workout", active = true)
            _workout.value = workoutRepository.insertWorkout(workoutEntity)
        }
    }

    fun cancelWorkout() {
        viewModelScope.launch(Dispatchers.IO) {
            workoutRepository.deleteActiveWorkout()
        }
    }

    fun addWorkoutExercise(workoutExercise: WorkoutExercise) {
        viewModelScope.launch {
            val workoutExerciseEntity = WorkoutExerciseEntity.from(workoutExercise)
            workoutExerciseEntity.workoutId = _workout.value?.id ?: return@launch
            workoutRepository.insertWorkoutExercise(workoutExerciseEntity)
            Log.e("WKT", workout.value?.workoutExercises.toString())
        }
    }

    fun addSet(workoutExerciseId: Int) {
        viewModelScope.launch {
            val newSet = ExerciseSetEntity(
                reps = 0,
                weight = 0f,
                completed = false,
                workoutExerciseId = workoutExerciseId
            )
            workoutRepository.insertExerciseSet(newSet)
        }
    }

    fun removeSet(setId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            workoutRepository.deleteSetById(setId)
        }
    }


    fun editSet(updatedSet: ExerciseSet) {
        viewModelScope.launch(Dispatchers.IO) {
            val setEntity = ExerciseSetEntity.from(updatedSet)
            workoutRepository.updateSetById(
                setEntity.id,
                setEntity.reps,
                setEntity.weight,
                setEntity.completed
            )
        }
    }

    fun saveWorkout() {
        viewModelScope.launch {
            val workout = _workout.value ?: return@launch
            workoutRepository.completeWorkout(workout.id)
            _workout.value = null
        }

    }
}