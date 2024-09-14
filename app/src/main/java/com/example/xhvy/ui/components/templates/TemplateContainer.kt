package com.example.xhvy.ui.components.templates

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.xhvy.R
import com.example.xhvy.ui.components.general.DropdownOption
import com.example.xhvy.ui.components.general.FaIconButton
import com.example.xhvy.ui.components.general.StyledDropdownMenu


@Composable
fun <T> TemplateContainerWithDropDown(
    modifier: Modifier = Modifier,
    title: String? = null,
    dropDownOptions: List<DropdownOption<T>>? = null,
    onOptionSelected: ((DropdownOption<T>) -> Unit)? = null,
    content: @Composable () -> Unit
) {

    var expanded by rememberSaveable {
        mutableStateOf(false)
    }

    TemplateContainer {
        Row(
            modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (title != null) {
                Text(text = title, style = MaterialTheme.typography.titleMedium)
            }
            if (dropDownOptions != null && onOptionSelected != null) {
                Box {
                    FaIconButton(
                        iconPainterId = R.drawable.ic_ellipsis,
                        modifier = Modifier.size(24.dp),
                        backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                        tint = MaterialTheme.colorScheme.primary,
                        onClick = { expanded = true }
                    )
                    StyledDropdownMenu(
                        options = dropDownOptions,
                        onOptionSelected = { action ->
                            onOptionSelected(action)
                        },
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    )
                }
            }
        }
        content()
    }
}


@Composable
fun TemplateContainer(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    var expanded by rememberSaveable {
        mutableStateOf(false)
    }
    Surface(
        border = BorderStroke(0.5.dp, MaterialTheme.colorScheme.outline),
        modifier = modifier
            .height(128.dp),
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surface
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 8.dp, horizontal = 8.dp)
                .fillMaxSize()
        ) {
            content()
        }
    }
}

