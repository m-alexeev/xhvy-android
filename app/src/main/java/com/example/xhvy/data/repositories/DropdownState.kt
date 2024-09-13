package com.example.xhvy.data.repositories

import com.example.xhvy.ui.components.general.DropdownOption

class DropdownState<T>(private val options: List<DropdownOption<T>>) {
    private var _selectedOption: DropdownOption<T>? = null
    val selectedOption: DropdownOption<T>? get() = _selectedOption

    fun selectOption(option: DropdownOption<T>) {
        _selectedOption = option
    }

    fun getOptions(): List<DropdownOption<T>> = options
}