package com.example.xhvy.ui.components.general

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.xhvy.R
import com.example.xhvy.ui.theme.XhvyTheme

@Composable
fun StyledButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    shape: Shape = RoundedCornerShape(16.dp),
    enabled: Boolean = true,
    contentPadding: PaddingValues = PaddingValues(vertical = 6.dp, horizontal = 12.dp),
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    backgroundColor: Color = MaterialTheme.colorScheme.primaryContainer,
    content: @Composable RowScope.() -> Unit,
) {
    val contentColor = if (enabled) colors.contentColor else colors.disabledContentColor

    Surface(
        modifier = modifier,
        color = backgroundColor,
        enabled = enabled,
        shape = shape,
        onClick = { onClick() }
    ) {
        ProvideContentColorTextStyle(
            contentColor = contentColor,
            textStyle = MaterialTheme.typography.titleMedium
        ) {
            Row(
                modifier = Modifier.padding(contentPadding),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                content = content
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun StyledButtonPreview() {
    XhvyTheme {
        Box(modifier = Modifier.padding(12.dp)) {
            StyledButton(onClick = {}, modifier = Modifier.fillMaxWidth()) {
                FaIcon(iconPainterId = R.drawable.ic_plus, modifier = Modifier.size(18.dp))
                Text(
                    text = "Sample Button",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.titleSmall
                )
            }
        }

    }
}