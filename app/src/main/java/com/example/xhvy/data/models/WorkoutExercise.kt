package com.example.xhvy.data.models


data class WorkoutExercise(
    val id: Int,
    val exercise: Exercise,
    var completed: Boolean,
    var exerciseSets: MutableList<ExerciseSet> = mutableListOf()
) {
    fun getSets(): List<ExerciseSet> {
        return exerciseSets
    }

    fun removeSet(set: ExerciseSet) {
        assert(exerciseSets.contains(set))
        exerciseSets.remove(set)
    }

    fun addSet() {
        // Create new set based on previous set or initialize to 0
        // Completion is false by default
        val newSet = ExerciseSet(exerciseSets.size, 0, 0.0f)
        if (exerciseSets.size > 0) {
            val lastSet: ExerciseSet = exerciseSets[exerciseSets.lastIndex]
            newSet.weight = lastSet.weight
            newSet.reps = lastSet.reps
        }
        exerciseSets.add(newSet)
    }

    fun editSet(setId: Int, setData: ExerciseSet) {
        assert(exerciseSets.find { it.id == setId } != null)
        val item = exerciseSets.find { it.id == setId }
        if (item != null) {
            item.weight = setData.weight
            item.reps = setData.reps
        }
    }

    fun setSetCompletion(setId: Int, completed: Boolean) {
        assert(exerciseSets.find { it.id == setId } != null)
        val item = exerciseSets.find { it.id == setId }
        if (item != null) {
            item.completed = completed
        }

        // Check all sets mark exercise as complete / incomplete if all sets are completed
        var exerciseComplete = true
        for (it in exerciseSets) {
            if (!it.completed) {
                exerciseComplete = false
                break
            }
        }
        this.completed = exerciseComplete
    }

}

