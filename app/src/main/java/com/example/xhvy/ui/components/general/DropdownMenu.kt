package com.example.xhvy.ui.components.general

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.xhvy.ui.theme.XhvyTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

sealed class DropdownMenuItemVariant {
    data object DEFAULT : DropdownMenuItemVariant()
    data object WARNING : DropdownMenuItemVariant()
    data object DESTRUCTIVE : DropdownMenuItemVariant()
}

data class DropdownOption<T>(
    val action: T,
    val label: String,
    val icon: Int?,
    val variant: DropdownMenuItemVariant = DropdownMenuItemVariant.DEFAULT,
)

@Composable
fun <T> StyledDropdownMenu(
    options: List<DropdownOption<T>>,
    onOptionSelected: (DropdownOption<T>) -> Unit,
    expanded: Boolean,
    onDismissRequest: () -> Unit,
) {
    val closeCoroutine = rememberCoroutineScope()

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismissRequest,
        Modifier.defaultMinSize(minWidth = 192.dp)
    ) {
        options.forEach { option ->
            val backgroundColor = when (option.variant) {
                is DropdownMenuItemVariant.DEFAULT -> Color.Unspecified
                DropdownMenuItemVariant.DESTRUCTIVE -> MaterialTheme.colorScheme.errorContainer
                DropdownMenuItemVariant.WARNING -> MaterialTheme.colorScheme.tertiaryContainer
            }
            DropdownMenuItem(
                modifier = Modifier.background(backgroundColor),
                text = { Text(text = option.label) },
                leadingIcon = {
                    option.icon?.let {
                        FaIcon(
                            iconPainterId = it,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                },
                onClick = {
                    closeCoroutine.launch {
                        onOptionSelected(option)
                        // Delay closure to actually show the ripple of the item being selected
                        delay(75)
                        onDismissRequest()
                    }
                },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StyledDropdownPreview() {
    var expanded by remember { mutableStateOf(false) }

    XhvyTheme(darkTheme = true) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
                .wrapContentSize(Alignment.TopStart)
        ) {
            IconButton(onClick = { expanded = true }) {
                Icon(
                    Icons.Default.MoreVert,
                    contentDescription = "Localized description",
                    tint = Color.White
                )
            }
            StyledDropdownMenu<String>(
                options = listOf(),
                onOptionSelected = {},
                expanded = expanded,
                onDismissRequest = { expanded = false })
        }
    }
}

