package com.example.xhvy.ui.components.general

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.xhvy.ui.theme.XhvyTheme
import java.text.DecimalFormatSymbols


class DecimalFormatter(
    symbols: DecimalFormatSymbols = DecimalFormatSymbols.getInstance()
) {
    private val decimalSeparator = symbols.decimalSeparator

    fun cleanup(input: String): String {

        if (input.matches("\\D".toRegex())) return ""
        if (input.matches("0+".toRegex())) return "0"

        val sb = StringBuilder()

        var hasDecimalSep = false

        for (char in input) {
            if (char.isDigit()) {
                sb.append(char)
                continue
            }
            if (char == decimalSeparator && !hasDecimalSep) {
                if (sb.isEmpty()) {
                    sb.append('0')
                }
                sb.append(char)
                hasDecimalSep = true
            }
        }
        return sb.toString()
    }


    fun formatForVisual(input: String): String {
        val split = input.split(decimalSeparator)
        val intPart = split[0]
        val fractionPart = split.getOrNull(1)
        return if (fractionPart == null) intPart else intPart + decimalSeparator + fractionPart
    }
}

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
        value = initialValue,
        onValueChange = { newValue ->
            onValueChange(DecimalFormatter().cleanup(newValue))
        },
        modifier = modifier,
        keyboardOptions = keyboardOptions,
        backgroundColor = backgroundColor,
        textStyle = textStyle,
        visualTransformation = DecimalInputVisualTransformation(decimalFormatter = decimalFormatter),
        debounceTime = debounceTime
    )
}

@Preview(showBackground = true)
@Composable
fun DecimalStyledInputPreview() {
    XhvyTheme {
        var weight by remember {
            mutableStateOf<String?>(null)
        }
        Column(
            Modifier
                .padding(12.dp)
                .fillMaxWidth()
        ) {
            Text(text = "$weight")
            DecimalStyledInput(
                initialValue = if (weight == null) "" else weight.toString(),
                onValueChange = { newWeight ->
                    weight = newWeight
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal
                ),
                textStyle = TextStyle(
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurface
                ),
                modifier = Modifier
                    .weight(0.2f),
                decimalFormatter = DecimalFormatter(),
                debounceTime = 200,
            )
        }
    }
}