package com.example.xhvy.ui.components.templates

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.xhvy.ui.components.general.StyledInput
import com.example.xhvy.ui.theme.XhvyTheme

@Composable
fun TemplateName(
    name: String,
    onNameUpdate: (newName: String) -> Unit,
) {
    StyledInput(
        value = name,
        onValueChange = { newName -> onNameUpdate(newName) },
        textStyle = MaterialTheme.typography.titleLarge.copy(color = MaterialTheme.colorScheme.onSurface),
        placeholder = "Chest Day",
        backgroundColor = MaterialTheme.colorScheme.background,
        shape = RoundedCornerShape(0.dp)
    )
}

@Preview()
@Composable
fun TemplateNamePreview() {

    var name by remember {
        mutableStateOf("Default Template")
    }

    XhvyTheme(darkTheme = false) {
        Surface(modifier = Modifier.fillMaxSize()) {
            TemplateName(name = name, onNameUpdate = { newName -> name = newName })

        }
    }
}