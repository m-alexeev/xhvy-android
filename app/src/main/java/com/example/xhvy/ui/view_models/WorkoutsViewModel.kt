package com.example.xhvy.ui.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.xhvy.data.entities.FullWorkout
import com.example.xhvy.data.models.Workout
import com.example.xhvy.data.repositories.WorkoutRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WorkoutsViewModel(private val workoutRepository: WorkoutRepository) : ViewModel() {
    private val _allWorkouts = MutableStateFlow<List<Workout>>(emptyList())
    val workouts: StateFlow<List<Workout>> = _allWorkouts.asStateFlow()

    init {
        viewModelScope.launch {
            workoutRepository.getCompletedWorkouts().collect { workoutEntities ->
                val workouts: List<Workout> = workoutEntities.map { fullWorkout: FullWorkout ->
                    fullWorkout.workout.toWorkout(fullWorkout)
                }
                _allWorkouts.value = workouts
            }
        }
    }
}