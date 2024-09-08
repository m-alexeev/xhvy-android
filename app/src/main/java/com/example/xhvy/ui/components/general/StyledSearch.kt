package com.example.xhvy.ui.components.general

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.xhvy.R
import com.example.xhvy.ui.theme.XhvyTheme

@Composable
fun StyledSearch(
    value: String,
    onValueChange: (value: String) -> Unit,
    modifier: Modifier = Modifier,
    leadingIcon: @Composable() (() -> Unit)? = null,
    trailingIcon: @Composable() (() -> Unit)? = null,
    contentPadding: PaddingValues = PaddingValues(horizontal = 4.dp, vertical = 2.dp),
    shape: Shape = MaterialTheme.shapes.small,
    backgroundColor: Color = MaterialTheme.colorScheme.outlineVariant,
    textColor: Color = MaterialTheme.colorScheme.onSurface,
    placeHolder: String = "",
) {
    Box(
        modifier = modifier
            .clip(shape)
            .background(backgroundColor)
    ) {
        Row(
            modifier = modifier.padding(contentPadding),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (leadingIcon != null) {
                leadingIcon()
            }
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = modifier.padding(contentPadding),
                singleLine = true,
                cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
                textStyle = TextStyle(color = textColor),
                decorationBox = {
                    Box() {
                        if (value.isEmpty()) {
                            Text(
                                modifier = Modifier.align(Alignment.CenterStart),
                                text = placeHolder,
                                color = MaterialTheme.colorScheme.outline,
                                style = MaterialTheme.typography.labelMedium
                            )
                        }
                        it()
                    }
                }
            )
            if (trailingIcon != null) {
                trailingIcon()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StyledSearchPreview() {
    XhvyTheme {
        var text by remember {
            mutableStateOf("")
        }
        StyledSearch(
            leadingIcon = { FaIcon(iconPainterId = R.drawable.ic_search) },
            value = text,
            onValueChange = { t -> text = t },
            placeHolder = "Search..."
        )
    }
}