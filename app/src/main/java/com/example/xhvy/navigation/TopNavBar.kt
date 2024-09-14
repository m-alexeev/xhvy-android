package com.example.xhvy.navigation

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.displayCutoutPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.xhvy.R
import com.example.xhvy.ui.components.general.FaIconButton
import com.example.xhvy.ui.theme.XhvyTheme

@Composable
fun TopNavBar(
    modifier: Modifier = Modifier,
    label: String,
    leadingButton: @Composable() (() -> Unit)? = null,
    trailingButton: @Composable() (() -> Unit)? = null,
) {
    Surface(
        modifier = modifier
            .height(72.dp)
            .fillMaxWidth(),
        shadowElevation = 12.dp,
        color = MaterialTheme.colorScheme.surface,
    ) {
        Row(
            modifier = Modifier
                .padding(end = 12.dp)
                .displayCutoutPadding(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.padding(if (leadingButton == null) 12.dp else 4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                if (leadingButton != null) {
                    leadingButton()
                }
                Text(text = label, fontSize = 24.sp)
            }
            if (trailingButton != null) {
                trailingButton()
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
fun TopNavBarPreview() {
    XhvyTheme {
        val scrollState = rememberScrollState()

        // Define the initial height and the minimum height
        val initialHeight = 200.dp
        val minHeight = 100.dp

        // Calculate the new height based on scroll position
        val height by animateDpAsState(
            targetValue = (initialHeight - scrollState.value.dp).coerceIn(minHeight, initialHeight)
        )

        Scaffold(topBar = {
            TopNavBar(
                label = "Exercises",
                trailingButton = {
                    FaIconButton(
                        iconPainterId = R.drawable.ic_dashboard,
                        contentDescription = null,
                        onClick = {})
                })
        }) {
        }
    }
}
