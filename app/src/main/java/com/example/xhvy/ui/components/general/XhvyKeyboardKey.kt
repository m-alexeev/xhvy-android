package com.example.xhvy.ui.components.general

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.xhvy.data.models.XhvyKey
import com.example.xhvy.ui.theme.XhvyTheme


@Composable
fun XhvyKeyboardKey(xhvyKey: XhvyKey, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .padding(vertical = 2.dp)
            .clip(MaterialTheme.shapes.extraSmall)
            .background(MaterialTheme.colorScheme.surface)
            .clickable {
                onClick()
            },
    ) {
        xhvyKey.item()
    }
}

@Preview
@Composable
fun XhvyKeyboardKeyPreview() {
    XhvyTheme(darkTheme = true) {
        XhvyKeyboardKey(xhvyKey = XhvyKey(item = { Text(text = "1") }), onClick = {})
    }
}
