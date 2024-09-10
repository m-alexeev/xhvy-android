package com.example.xhvy.ui.view_models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.xhvy.data.entities.ExerciseEntity
import com.example.xhvy.data.entities.ExerciseSetEntity
import com.example.xhvy.data.entities.WorkoutExerciseEntity
import com.example.xhvy.data.entities.WorkoutExerciseFull
import com.example.xhvy.data.models.ExerciseSet
import com.example.xhvy.data.models.Workout
import com.example.xhvy.data.models.WorkoutExercise
import com.example.xhvy.data.repositories.WorkoutRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date

class NewWorkoutViewModel : ViewModel() {
    private var _workout by mutableStateOf(
        Workout(
            name = "New Afternoon Workout"
        )
    )
    private var _workoutExercises = mutableStateListOf<WorkoutExercise>()

    init {
        _workout.workoutExercises = _workoutExercises
    }

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

    fun saveWorkout(workoutRepository: WorkoutRepository) {
        val workoutExerciseEntity = workoutExercises.map { it ->
            WorkoutExerciseFull(
                workoutExerciseEntity = WorkoutExerciseEntity.from(it),
                exercise = ExerciseEntity.from(it.exercise),
                exerciseSets = it.exerciseSets.map { set ->
                    ExerciseSetEntity.from(set)
                })
        }
        // Insert workout
        viewModelScope.launch(Dispatchers.IO) {
            workoutRepository.insertWorkout(_workout, workoutExercises)
        }

    }
}