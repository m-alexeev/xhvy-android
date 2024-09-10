package com.example.xhvy.ui.view_models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.xhvy.data.models.ExerciseSet
import com.example.xhvy.data.models.Workout
import com.example.xhvy.data.models.WorkoutExercise
import java.util.Date

class NewWorkoutViewModel : ViewModel() {
    private var _workout by mutableStateOf(
        Workout(
            name = "New Afternoon Workout"
        )
    )
    private var _workoutExercises = mutableStateListOf<WorkoutExercise>()

    val workoutName: String
        get() = _workout.name

    val workoutStart: Date
        get() = _workout.startTime

    val workoutExercises: List<WorkoutExercise>
        get() = _workoutExercises


    fun addWorkoutExercise(workoutExercise: WorkoutExercise) {
        if (!workoutExercises.contains(workoutExercise)) {
            _workoutExercises.add(workoutExercise)
        }
    }

    fun addSet(index: Int) {
        val workoutExercise = _workoutExercises[index]
        workoutExercise.exerciseSets.add(ExerciseSet(workoutExercise.getSets().size, null, null))
    }

    fun removeSet(exerciseIndex: Int, setIndex: Int) {
        val workoutExercise = _workoutExercises[exerciseIndex]
        workoutExercise.exerciseSets.removeAt(setIndex)

    }


    fun editSet(exerciseIndex: Int, setIndex: Int, updatedSet: ExerciseSet) {
        val workoutExercise = _workoutExercises[exerciseIndex]
        workoutExercise.exerciseSets[setIndex] = updatedSet
    }
}