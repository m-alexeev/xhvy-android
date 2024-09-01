package com.example.xhvy.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.xhvy.ui.components.general.FaIcon
import com.example.xhvy.ui.theme.XhvyTheme

@Composable
fun BottomNavBar(modifier: Modifier = Modifier, content: @Composable() (RowScope.() -> Unit)) {
    Surface(
        modifier = modifier.padding(bottom = 12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
            content = content
        )
    }
}


@Composable
fun BottomNavBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: @Composable (color: Color) -> Unit,
    modifier: Modifier = Modifier,
    label: @Composable() ((color: Color) -> Unit)? = null,
    selectedColor: Color = MaterialTheme.colorScheme.primary,
    unSelectedColor: Color = MaterialTheme.colorScheme.onSurface,
) {
    val color: Color = if (selected) selectedColor else unSelectedColor;

    Box(
        modifier = modifier
            .wrapContentHeight(unbounded = true)
            .size(96.dp, 96.dp)
            .clickable(
                onClick = {
                    onClick()
                },
                indication = rememberRipple(
                    bounded = false,
                    color = MaterialTheme.colorScheme.primary,
                    radius = 48.dp,
                ),
                interactionSource = remember { MutableInteractionSource() }
            ),
        contentAlignment = Alignment.Center,
    )
    {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            icon(color)
            if (label != null) {
                label(color)
            }
        }
    }
}

@Preview
@Composable
fun BottomNavBarPreview() {
    XhvyTheme {
        BottomNavBar {
            BottomNavigation.entries.forEachIndexed { index, item ->
                BottomNavBarItem(
                    selected = index % 2 == 0,
                    onClick = {},
                    icon = { color ->
                        FaIcon(
                            iconPainterId = item.iconResId,
                            contentDescription = null,
                            tint = color
                        )
                    },
                    label = { color ->
                        Text(
                            item.label,
                            style = MaterialTheme.typography.titleSmall,
                            color = color
                        )
                    }
                )
            }
        }
    }
}