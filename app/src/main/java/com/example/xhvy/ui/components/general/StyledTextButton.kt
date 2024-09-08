package com.example.xhvy.ui.components.general

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.xhvy.ui.theme.XhvyTheme

@Composable
fun StyledTextButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    color: Color = if (enabled) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline,
    shape: Shape = MaterialTheme.shapes.extraSmall,
    contentPadding: PaddingValues = PaddingValues(horizontal = 4.dp),
    style: TextStyle = MaterialTheme.typography.bodyMedium,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .clip(shape)
            .clickable(enabled = enabled, onClick = onClick)
    ) {
        Text(
            modifier = Modifier.padding(contentPadding),
            text = text, color = color, style = style,
        )
    }
}


@Preview(showBackground = true)
@Composable
fun StyledTextButtonPreview() {
    XhvyTheme {
        StyledTextButton(text = "Test") {

        }
    }
}

