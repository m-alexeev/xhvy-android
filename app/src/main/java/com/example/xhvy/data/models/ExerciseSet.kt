package com.example.xhvy.data.models

class ExerciseSet(val id: Int, private var setReps: Int, private var setWeight: Float, private var setCompleted: Boolean = false) {
    var repetitions: Int
        get() = this.setReps;
        set(value) {
            if (value < 0) {
                throw IllegalArgumentException("Repetitions cannot be less than 0")
            } else {
                setReps = value
            }
        }

    var weight: Float
        get() = this.setWeight
        set(value) {
            if (value < 0) {
                throw IllegalArgumentException("Weight cannot be less than 0")
            } else {
                setWeight = value
            }
        }

    var completed: Boolean
        get() = this.setCompleted
        set(value){
            this.setCompleted = value
        }

}