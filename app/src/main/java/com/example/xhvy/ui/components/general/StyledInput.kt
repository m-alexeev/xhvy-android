package com.example.xhvy.ui.components.general

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.xhvy.ui.theme.XhvyTheme

@Composable
fun StyledInput(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String = ""
) {

    BasicTextField(
        value = value, onValueChange = onValueChange,
        modifier = modifier
            .clip(RoundedCornerShape(6.dp))
            .background(MaterialTheme.colorScheme.primaryContainer),
        textStyle = TextStyle(textAlign = TextAlign.Center),
        cursorBrush = SolidColor(MaterialTheme.colorScheme.secondary),
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.Center,
            ) {
                innerTextField()
            }
        }
    )
}


@Preview(showBackground = true)
@Composable
fun StyledInputPreview() {
    XhvyTheme {
        var text by remember {
            mutableStateOf("")
        }
        Row {
            StyledInput(
                value = text,
                onValueChange = {t -> text = t},
                placeholder = "Test",
                modifier = Modifier.weight(0.3f)
            )
            StyledInput(
                value = text,
                onValueChange = { t -> text = t },
                placeholder = "Test",
                modifier = Modifier.weight(0.5f)
            )
        }
    }
}