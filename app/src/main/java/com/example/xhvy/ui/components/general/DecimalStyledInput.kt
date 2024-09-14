package com.example.xhvy.ui.components.general

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import com.example.xhvy.domain.utils.DecimalFormatter


class DecimalInputVisualTransformation(
    private val decimalFormatter: DecimalFormatter
) : VisualTransformation {

    override fun filter(text: AnnotatedString): TransformedText {

        val inputText = text.text
        val formattedNumber = decimalFormatter.formatForVisual(inputText)

        val newText = AnnotatedString(
            text = formattedNumber,
            spanStyles = text.spanStyles,
            paragraphStyles = text.paragraphStyles
        )

        val offsetMapping = FixedCursorOffsetMapping(
            contentLength = inputText.length,
            formattedContentLength = formattedNumber.length
        )

        return TransformedText(newText, offsetMapping)
    }
}

private class FixedCursorOffsetMapping(
    private val contentLength: Int,
    private val formattedContentLength: Int,
) : OffsetMapping {
    override fun originalToTransformed(offset: Int): Int = formattedContentLength
    override fun transformedToOriginal(offset: Int): Int = contentLength
}

@Composable
fun DecimalStyledInput(
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
    decimalFormatter: DecimalFormatter,
    textStyle: TextStyle = TextStyle.Default,
    backgroundColor: Color? = null,
    initialValue: String,
    onValueChange: (value: String) -> Unit,
    debounceTime: Long = 0,
) {
    StyledInput(
        modifier = modifier,
        keyboardOptions = keyboardOptions,
        backgroundColor = backgroundColor,
        textStyle = textStyle,
        value = initialValue,
        visualTransformation = DecimalInputVisualTransformation(decimalFormatter = decimalFormatter),
        onValueChange = onValueChange,
        debounceTime = debounceTime
    )
}