package com.example.xhvy.view_models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.xhvy.data.models.ExerciseBodyPart
import com.example.xhvy.data.models.ExerciseCategory

class ExerciseNewViewModel : ViewModel() {
    private var _name by mutableStateOf("")
    private var _category by mutableStateOf<ExerciseCategory?>(null)
    private var _bodyPart by mutableStateOf<ExerciseBodyPart?>(null)


    val name: String
        get() = _name

    fun updateName(newName: String) {
        _name = newName
    }

    val category: ExerciseCategory?
        get() = _category

    fun updateCategory(category: ExerciseCategory?) {
        _category = category
    }

    val bodyPart: ExerciseBodyPart?
        get() = _bodyPart

    fun updateBodyPart(bodyPart: ExerciseBodyPart?) {
        _bodyPart = bodyPart
    }

}