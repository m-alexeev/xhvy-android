package com.example.xhvy.data.models

import androidx.compose.runtime.Composable

enum class KeyType {
    Input,
    Delete,
}

class XhvyKey(
    val item: @Composable() () -> Unit,
    val keyType: KeyType = KeyType.Input
) {
}