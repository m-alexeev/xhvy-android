package com.example.xhvy.ui.components.general

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    contentDescription: String? = null,
    tint: Color = MaterialTheme.colorScheme.onSurface,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    contentPadding: PaddingValues = PaddingValues(4.dp),
    shape: Shape = RoundedCornerShape(4.dp),
    onClick: () -> Unit,
    content: @Composable() (() -> Unit)? = null
) {
    Box(
        modifier = modifier
            .clip(shape)
            .background(backgroundColor)
            .clickable { onClick() }
    ) {
        FaIcon(
            iconPainterId = iconPainterId,
            modifier = Modifier.padding(contentPadding),
            contentDescription = contentDescription,
            tint = tint,
        )
        if (content != null) {
            content()
        }
    }

}

@Composable
fun FaLabeledIcon(
    iconPainterId: Int,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    tint: Color = MaterialTheme.colorScheme.primary,
    label: @Composable() () -> Unit,
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        FaIcon(
            iconPainterId = iconPainterId,
            tint = tint,
            contentDescription = contentDescription,
            modifier = Modifier.size(18.dp)
        )
        label()
    }
}

@Preview(showBackground = true)
@Composable
fun FaIconPreview() {
    XhvyTheme {
        Row {
            FaIconButton(
                iconPainterId = R.drawable.ic_dumbbell,
                contentDescription = null,
                onClick = {})
        }
    }
}