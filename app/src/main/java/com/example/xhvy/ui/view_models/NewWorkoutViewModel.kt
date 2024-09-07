package com.example.xhvy.ui.view_models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.xhvy.data.models.Workout
import com.example.xhvy.data.models.WorkoutExercise
import java.util.Date

class NewWorkoutViewModel : ViewModel() {
    private var _workout by mutableStateOf(
        Workout(
            name = "New Afternoon Workout"
        )
    )
    private var _workoutExercise = mutableStateListOf<WorkoutExercise>()

    val workoutName: String
        get() = _workout.name

    val workoutStart: Date
        get() = _workout.startTime

    val workoutExercises: List<WorkoutExercise>
        get() = _workoutExercise


    fun addWorkoutExercise(workoutExercise: WorkoutExercise) {
//        if (!workoutExercises.contains(workoutExercise)) {
            _workoutExercise.add(workoutExercise)
//        }
    }

    fun getWorkoutExercise(id: Int): WorkoutExercise? {
        val workoutExercise = workoutExercises.find { item -> item.id == id }
        return workoutExercise
    }


}