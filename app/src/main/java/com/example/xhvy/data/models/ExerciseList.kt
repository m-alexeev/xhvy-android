package com.example.xhvy.data.models

class ExerciseList(exercisesList: List<Exercise>) {
    private val exercises: LinkedHashMap<Int, Exercise> = LinkedHashMap()
    init{
        // insert existing exercises into
        for (exercise in exercisesList){
            exercises[exercise.id] = exercise
        }
    }

    fun getExercises(): LinkedHashMap<Int, Exercise>{
        return this.exercises
    }

    fun addExercise(exercise: Exercise){
        assert(!exercises.containsKey(exercise.id))
        exercises[exercise.id] = exercise
    }

    fun removeExercise(exerciseId: Int){
        assert(exercises.containsKey(exerciseId))
        exercises.remove(exerciseId)
    }

    fun updateExercise(exercise: Exercise){
        // overwrite the entry with new data
        assert(exercises.containsKey(exercise.id))
        exercises[exercise.id] = exercise
    }
}