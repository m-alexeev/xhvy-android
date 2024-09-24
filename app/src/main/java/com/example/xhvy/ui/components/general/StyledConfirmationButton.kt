package com.example.xhvy.ui.components.general

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.xhvy.R
import com.example.xhvy.ui.theme.ExtendedTheme
import com.example.xhvy.ui.theme.XhvyTheme


@Composable
fun StyledConfirmationButton(
    modifier: Modifier = Modifier,
    onConfirm: () -> Unit,
    onCancel: () -> Unit,
    dialogContent: @Composable() () -> Unit,
    content: @Composable() () -> Unit,
) {
    var showDialog by remember { mutableStateOf(false) }

    Box {
        StyledButton(modifier, onClick = { showDialog = true }) {
            content()
        }
        AnimatedDialog(onDismissRequest = { showDialog = false }, showDialog = showDialog) {
            Column(modifier = Modifier.padding(12.dp)) {
                dialogContent()
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    StyledButton(
                        colors = ButtonDefaults.buttonColors(
                            contentColor = MaterialTheme.colorScheme.onErrorContainer,
                            containerColor = MaterialTheme.colorScheme.errorContainer
                        ), onClick = {
                            onCancel()
                            showDialog = false
                        }) {
                        Text(text = stringResource(id = R.string.action_cancel))
                    }
                    StyledButton(
                        colors = ButtonDefaults.buttonColors(
                            contentColor = ExtendedTheme.colors.success,
                            containerColor = ExtendedTheme.colors.onSuccess,
                        ), onClick = {
                            onConfirm()
                            showDialog = false
                        }) {
                        Text(text = stringResource(id = R.string.action_confirm))
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StyledConfirmationButtonPreview() {
    XhvyTheme {
        Column(Modifier.fillMaxSize()) {
            StyledConfirmationButton(
                onConfirm = { /*TODO*/ },
                onCancel = { /*TODO*/ },
                dialogContent = {
                    Text(text = "Are you sure you want to complete the workout?")
                }) {
                Text(text = "Finish")
            }
        }
    }
}