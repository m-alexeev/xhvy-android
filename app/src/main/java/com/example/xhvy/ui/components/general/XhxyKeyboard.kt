package com.example.xhvy.ui.components.general

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.xhvy.R
import com.example.xhvy.data.models.KeyType
import com.example.xhvy.data.models.XhvyKey
import com.example.xhvy.ui.theme.XhvyTheme

@Composable
fun XhvyKeyboard(modifier: Modifier = Modifier, onKeyPress: (key: XhvyKey, label: String) -> Unit) {
    val row1 = listOf(
        XhvyKey(item = { Text(text = "1") }),
        XhvyKey(item = { Text(text = "2") }),
        XhvyKey(item = { Text(text = "3") }),
    )
    val row2 = listOf(
        XhvyKey(item = { Text(text = "4") }),
        XhvyKey(item = { Text(text = "5") }),
        XhvyKey(item = { Text(text = "6") }),
    )
    val row3 = listOf(
        XhvyKey(item = { Text(text = "7") }),
        XhvyKey(item = { Text(text = "8") }),
        XhvyKey(item = { Text(text = "9") }),
    )
    val row4 = listOf(
        XhvyKey(item = { Text(text = ".") }),
        XhvyKey(item = { Text(text = "0") }),
        XhvyKey(
            item = { FaIcon(iconPainterId = R.drawable.ic_delete) },
            keyType = KeyType.Delete
        ),
    )

    Surface {
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(modifier.weight(0.8f)) {
                Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                    row1.forEach { item ->
                        XhvyKeyboardKey(
                            modifier = Modifier.weight(1f),
                            onClick = { onKeyPress(item, "1") },
                            xhvyKey = item
                        )
                    }
                }
                Row {
                    row2.forEach { item ->
                        XhvyKeyboardKey(
                            modifier = Modifier.weight(1f),
                            onClick = { onKeyPress(item, "2") },
                            xhvyKey = item
                        )
                    }
                }
                Row {
                    row3.forEach { item ->
                        XhvyKeyboardKey(
                            modifier = Modifier.weight(1f),
                            onClick = { onKeyPress(item, "3") },
                            xhvyKey = item
                        )
                    }
                }
                Row {
                    row4.forEach { item ->
                        XhvyKeyboardKey(
                            modifier = Modifier.weight(1f),
                            onClick = { onKeyPress(item, "5") },
                            xhvyKey = item
                        )
                    }
                }
            }
            Column(modifier = Modifier.weight(.2f), horizontalAlignment = Alignment.End) {
                FaIconButton(iconPainterId = R.drawable.ic_delete, onClick = {})
                StyledButton(onClick = { /*TODO*/ }) {
                    Text(text = "Next")
                }
            }
        }
    }
}


@Preview
@Composable
fun XhvyKeyboardPreview() {
    XhvyTheme {
        XhvyKeyboard(onKeyPress = {_ , _ -> {}})
    }
}