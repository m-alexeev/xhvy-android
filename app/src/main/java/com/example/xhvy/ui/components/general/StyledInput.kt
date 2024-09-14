package com.example.xhvy.ui.components.general

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.xhvy.ui.theme.XhvyTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun StyledInput(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    textStyle: TextStyle = TextStyle.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    placeholder: String = "",
    visualTransformation: VisualTransformation = VisualTransformation.None,
    backgroundColor: Color? = null,
    debounceTime: Long = 0,
    contentPadding: PaddingValues = PaddingValues(vertical = 4.dp),
) {
    var text by remember {
        mutableStateOf(value)
    }

    var debouncedText by remember {
        mutableStateOf(value)
    }

    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(text) {
        coroutineScope.launch {
            delay(debounceTime)
            debouncedText = text
            onValueChange(debouncedText)
        }
    }

    BasicTextField(
        value = text, onValueChange = { t -> text = t },
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(backgroundColor ?: MaterialTheme.colorScheme.surfaceContainer),
        textStyle = textStyle,
        cursorBrush = SolidColor(MaterialTheme.colorScheme.outline),
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .padding(contentPadding),
            ) {
                if (value.isEmpty()) {
                    Text(
                        text = placeholder,
                        color = MaterialTheme.colorScheme.outline,
                        style = textStyle
                    )
                }
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
            mutableStateOf(123)
        }
        Row(Modifier.padding(12.dp)) {
            StyledInput(
                value = text.toString(),
                onValueChange = { t -> text = t.toInt() },
                placeholder = "Test",
                modifier = Modifier.weight(0.3f)
            )
        }
    }
}