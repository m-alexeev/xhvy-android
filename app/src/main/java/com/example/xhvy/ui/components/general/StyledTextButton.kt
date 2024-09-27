package com.example.xhvy.ui.components.general

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.xhvy.ui.theme.XhvyTheme

@Composable
fun StyledTextButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = MaterialTheme.shapes.small,
    contentPadding: PaddingValues = PaddingValues(horizontal = 4.dp),
    onClick: () -> Unit,
    colors: ButtonColors = ButtonDefaults.buttonColors(
        contentColor = MaterialTheme.colorScheme.primary,
        containerColor = MaterialTheme.colorScheme.background,
    ),
    content: @Composable() () -> Unit,
) {
    StyledButton(
        modifier = modifier,
        shape = shape,
        onClick = onClick,
        enabled = enabled,
        colors = colors,
    ) {
        Box(modifier = Modifier.padding(contentPadding)) {
            content()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun StyledTextButtonPreview() {
    XhvyTheme {
        StyledTextButton(onClick = {}) {
            Text(text = "Sample Button")
        }
    }
}

