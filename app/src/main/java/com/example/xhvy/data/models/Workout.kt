package com.example.xhvy.data.models

import com.example.xhvy.domain.interfaces.Saveable

class Workout: Saveable{
    private var exercises: MutableList<Exercise> = mutableListOf()
        get() = field

    fun addExercise(exercise: Exercise){
        assert(!exercises.contains(exercise))
        exercises.add(exercise)
    }

    fun removeExercises(exercise: Exercise){
        assert(exercises.contains(exercise))
        exercises.remove(exercise)
    }

    override fun save() {
        TODO("Not yet implemented")
    }


}