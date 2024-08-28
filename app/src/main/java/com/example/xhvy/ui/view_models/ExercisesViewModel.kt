package com.example.xhvy.ui.view_models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.xhvy.data.models.Exercise
import com.example.xhvy.data.models.ExerciseBodyType
import com.example.xhvy.data.models.ExerciseCategory

class ExercisesViewModel : ViewModel() {
    private val _exercises = getExercises.toMutableStateList()
    private var _search by mutableStateOf("")

    val exercises: List<Exercise>
        get() = if (_search.isEmpty()) {
            _exercises
        } else {
            _exercises.filter { exercise ->
                exercise.name.contains(
                    _search,
                    ignoreCase = true
                ) || exercise.category.name.contains(
                    _search,
                    ignoreCase = true
                )
            }
        }
    
    val search: String
        get() = _search

    // Function to update the search query
    fun updateSearch(newSearch: String) {
        _search = newSearch
    }

}


val getExercises = listOf(
    Exercise(1, "Bench Press", ExerciseCategory.BARBELL, ExerciseBodyType.CHEST),
    Exercise(2, "Squat", ExerciseCategory.BARBELL, ExerciseBodyType.LEGS),
    Exercise(3, "Deadlift", ExerciseCategory.BARBELL, ExerciseBodyType.BACK),
    Exercise(4, "Overhead Press", ExerciseCategory.BARBELL, ExerciseBodyType.SHOULDERS),
    Exercise(5, "Barbell Row", ExerciseCategory.BARBELL, ExerciseBodyType.BACK),

    Exercise(6, "Dumbbell Curl", ExerciseCategory.DUMBBELL, ExerciseBodyType.ARMS),
    Exercise(7, "Dumbbell Fly", ExerciseCategory.DUMBBELL, ExerciseBodyType.CHEST),
    Exercise(
        8,
        "Dumbbell Lateral Raise",
        ExerciseCategory.DUMBBELL,
        ExerciseBodyType.SHOULDERS
    ),
    Exercise(
        9,
        "Dumbbell Shoulder Press",
        ExerciseCategory.DUMBBELL,
        ExerciseBodyType.SHOULDERS
    ),
    Exercise(10, "Dumbbell Tricep Extension", ExerciseCategory.DUMBBELL, ExerciseBodyType.ARMS),

    Exercise(11, "Leg Press", ExerciseCategory.MACHINE, ExerciseBodyType.LEGS),
    Exercise(12, "Chest Press", ExerciseCategory.MACHINE, ExerciseBodyType.CHEST),
    Exercise(13, "Lat Pulldown", ExerciseCategory.MACHINE, ExerciseBodyType.BACK),
    Exercise(14, "Cable Row", ExerciseCategory.MACHINE, ExerciseBodyType.BACK),
    Exercise(15, "Leg Curl", ExerciseCategory.MACHINE, ExerciseBodyType.LEGS),

    Exercise(
        16,
        "Weighted Pull-Up",
        ExerciseCategory.WEIGHTED_BODYWEIGHT,
        ExerciseBodyType.BACK
    ),
    Exercise(17, "Weighted Dip", ExerciseCategory.WEIGHTED_BODYWEIGHT, ExerciseBodyType.CHEST),
    Exercise(
        18,
        "Weighted Push-Up",
        ExerciseCategory.WEIGHTED_BODYWEIGHT,
        ExerciseBodyType.CHEST
    ),
    Exercise(
        19,
        "Assisted Pull-Up",
        ExerciseCategory.ASSISTED_BODYWEIGHT,
        ExerciseBodyType.BACK
    ),
    Exercise(20, "Assisted Dip", ExerciseCategory.ASSISTED_BODYWEIGHT, ExerciseBodyType.CHEST),

    Exercise(21, "Push-Up", ExerciseCategory.REPS, ExerciseBodyType.CHEST),
    Exercise(22, "Sit-Up", ExerciseCategory.REPS, ExerciseBodyType.CORE),
    Exercise(23, "Jumping Jack", ExerciseCategory.REPS, ExerciseBodyType.FULL_BODY),
    Exercise(24, "Plank", ExerciseCategory.DURATION, ExerciseBodyType.CORE),
    Exercise(25, "Wall Sit", ExerciseCategory.DURATION, ExerciseBodyType.LEGS),

    Exercise(26, "Running", ExerciseCategory.CARDIO, ExerciseBodyType.CARDIO),
    Exercise(27, "Cycling", ExerciseCategory.CARDIO, ExerciseBodyType.CARDIO),
    Exercise(28, "Rowing", ExerciseCategory.CARDIO, ExerciseBodyType.FULL_BODY),
    Exercise(29, "Swimming", ExerciseCategory.CARDIO, ExerciseBodyType.FULL_BODY),
    Exercise(30, "Jump Rope", ExerciseCategory.CARDIO, ExerciseBodyType.FULL_BODY)
)
