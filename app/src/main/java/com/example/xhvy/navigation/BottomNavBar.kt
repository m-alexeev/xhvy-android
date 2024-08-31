package com.example.xhvy.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.xhvy.ui.theme.XhvyTheme

@Composable
fun BottomNavBar(modifier: Modifier = Modifier, content: @Composable() (RowScope.() -> Unit)) {
    Surface(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 60.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
            content = content
        )
    }
}


@Composable
fun RowScope.BottomNavBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    label: @Composable (() -> Unit)? = null,
) {
    Box(modifier = modifier.clickable { onClick() }) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            icon()
            if (label != null) {
                label()
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
                    selected = false,
                    onClick = {},
                    icon = { Icon(item.icon, contentDescription = null) },
                    label = { Text(item.label) }
                )
            }
        }
    }
}