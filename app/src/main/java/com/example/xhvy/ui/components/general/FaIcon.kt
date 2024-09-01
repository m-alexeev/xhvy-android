package com.example.xhvy.ui.components.general

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.xhvy.R
import com.example.xhvy.ui.theme.XhvyTheme

@Composable
fun FaIcon(
    iconPainterId: Int,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    tint: Color = MaterialTheme.colorScheme.onSurface,
) {
    Icon(
        painter = painterResource(id = iconPainterId),
        contentDescription = contentDescription,
        modifier = modifier,
        tint = tint
    )
}

@Composable
fun FaIconButton(
    iconPainterId: Int,
    modifier: Modifier = Modifier,
    contentDescription: String?,
    tint: Color = MaterialTheme.colorScheme.onSurface,
    onClick: () -> Unit
) {
    IconButton(onClick = onClick) {
        FaIcon(
            iconPainterId = iconPainterId,
            modifier = modifier,
            contentDescription = contentDescription,
            tint = tint
        )
    }
}

@Preview(showBackground = false)
@Composable
fun FaIconPreview(){
    XhvyTheme {
        Column {
            FaIcon(iconPainterId = R.drawable.ic_dumbbell)
        }
    }
}