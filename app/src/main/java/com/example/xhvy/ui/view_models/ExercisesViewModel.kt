package com.example.xhvy.ui.view_models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.xhvy.data.models.Exercise
import com.example.xhvy.data.models.ExerciseBodyPart
import com.example.xhvy.data.models.ExerciseCategory
import com.example.xhvy.data.repositories.ExerciseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ExercisesViewModel(private val exerciseRepository: ExerciseRepository) : ViewModel() {
    private var _search by mutableStateOf("")
    private val _exercises = MutableStateFlow<List<Exercise>>(emptyList())
    val exercises: StateFlow<List<Exercise>> = _exercises.asStateFlow()

    init {
        // Collect changes from the database
        viewModelScope.launch {
            exerciseRepository.getAllExercises().collect { exerciseList ->
                _exercises.value = exerciseList.sortedBy { e -> e.name }
            }
        }
    }


//
//    val exercises: List<Exercise>
//        get() = if (_search.isEmpty()) {
//            _exercises.sortedBy { e -> e.name }
//        } else {
//            _exercises.filter { exercise ->
//                exercise.name.contains(
//                    _search,
//                    ignoreCase = true
//                ) || exercise.category.name.contains(
//                    _search,
//                    ignoreCase = true
//                )
//            }
//        }

    val search: String
        get() = _search

    // Function to update the search query
    fun updateSearch(newSearch: String) {
        _search = newSearch
    }

    fun addExercise(exercise: Exercise) {
        viewModelScope.launch {
            exerciseRepository.insertExercise(exercise)
        }
    }

    fun deleteExercise(exercise: Exercise) {
        assert(exercise.deletable)
        viewModelScope.launch(Dispatchers.IO) {
            exerciseRepository.deleteExercise(exercise)
        }
    }


//    fun updateExercise(exercise: Exercise) {
//        val index = _exercises.indexOf(exercise)
//        _exercises[index] = exercise
//    }

}

val getExercises = listOf(
    Exercise(1, "Bench Press", ExerciseCategory.BARBELL, ExerciseBodyPart.CHEST),
    Exercise(2, "Squat", ExerciseCategory.BARBELL, ExerciseBodyPart.LEGS),
    Exercise(3, "Deadlift", ExerciseCategory.BARBELL, ExerciseBodyPart.BACK),
    Exercise(4, "Overhead Press", ExerciseCategory.BARBELL, ExerciseBodyPart.SHOULDERS),
    Exercise(5, "Barbell Row", ExerciseCategory.BARBELL, ExerciseBodyPart.BACK),

    Exercise(
        6,
        "Dumbbell Curl", ExerciseCategory.DUMBBELL, ExerciseBodyPart.ARMS
    ),
    Exercise(7, "Dumbbell Fly", ExerciseCategory.DUMBBELL, ExerciseBodyPart.CHEST),
    Exercise(
        8,
        "Dumbbell Lateral Raise",
        ExerciseCategory.DUMBBELL,
        ExerciseBodyPart.SHOULDERS
    ),
    Exercise(
        9,
        "Dumbbell Shoulder Press",
        ExerciseCategory.DUMBBELL,
        ExerciseBodyPart.SHOULDERS
    ),
    Exercise(10, "Dumbbell Tricep Extension", ExerciseCategory.DUMBBELL, ExerciseBodyPart.ARMS),

    Exercise(11, "Leg Press", ExerciseCategory.MACHINE, ExerciseBodyPart.LEGS),
    Exercise(12, "Chest Press", ExerciseCategory.MACHINE, ExerciseBodyPart.CHEST),
    Exercise(13, "Lat Pulldown", ExerciseCategory.MACHINE, ExerciseBodyPart.BACK),
    Exercise(14, "Cable Row", ExerciseCategory.MACHINE, ExerciseBodyPart.BACK),
    Exercise(15, "Leg Curl", ExerciseCategory.MACHINE, ExerciseBodyPart.LEGS),

    Exercise(
        16,
        "Weighted Pull-Up",
        ExerciseCategory.WEIGHTED_BODYWEIGHT,
        ExerciseBodyPart.BACK
    ),
    Exercise(17, "Weighted Dip", ExerciseCategory.WEIGHTED_BODYWEIGHT, ExerciseBodyPart.CHEST),
    Exercise(
        18,
        "Weighted Push-Up",
        ExerciseCategory.WEIGHTED_BODYWEIGHT,
        ExerciseBodyPart.CHEST
    ),
    Exercise(
        19,
        "Assisted Pull-Up",
        ExerciseCategory.ASSISTED_BODYWEIGHT,
        ExerciseBodyPart.BACK
    ),
    Exercise(20, "Assisted Dip", ExerciseCategory.ASSISTED_BODYWEIGHT, ExerciseBodyPart.CHEST),

    Exercise(21, "Push-Up", ExerciseCategory.REPS, ExerciseBodyPart.CHEST),
    Exercise(22, "Sit-Up", ExerciseCategory.REPS, ExerciseBodyPart.CORE),
    Exercise(23, "Jumping Jack", ExerciseCategory.REPS, ExerciseBodyPart.FULL_BODY),
    Exercise(24, "Plank", ExerciseCategory.DURATION, ExerciseBodyPart.CORE),
    Exercise(25, "Wall Sit", ExerciseCategory.DURATION, ExerciseBodyPart.LEGS),

    Exercise(26, "Running", ExerciseCategory.CARDIO, ExerciseBodyPart.CARDIO),
    Exercise(27, "Cycling", ExerciseCategory.CARDIO, ExerciseBodyPart.CARDIO),
    Exercise(28, "Rowing", ExerciseCategory.CARDIO, ExerciseBodyPart.FULL_BODY),
    Exercise(29, "Swimming", ExerciseCategory.CARDIO, ExerciseBodyPart.FULL_BODY),
    Exercise(30, "Jump Rope", ExerciseCategory.CARDIO, ExerciseBodyPart.FULL_BODY)
)
