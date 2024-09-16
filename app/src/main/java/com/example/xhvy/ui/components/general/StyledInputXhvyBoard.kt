package com.example.xhvy.ui.components.general

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import com.example.xhvy.data.models.KeyType
import com.example.xhvy.data.models.XhvyKey
import com.example.xhvy.ui.theme.XhvyTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StyledInputXhvyBoard(value: String, onValueChange: (String) -> Unit) {

    val bottomSheetState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            skipHiddenState = false,
            initialValue = SheetValue.Hidden
        )
    )
    val coroutineScope = rememberCoroutineScope()
    val keyboardController = LocalSoftwareKeyboardController.current

    BottomSheetScaffold(
        sheetContent = {
            XhvyKeyboard(onKeyPress = { key: XhvyKey, label: String ->
                if (key.keyType == KeyType.Input) {
                    onValueChange(value + label)
                } else {
                    if (value.isNotEmpty()) {
                        onValueChange(value.drop(1))
                    }
                }
            })
        },
        scaffoldState = bottomSheetState
    ) {
        StyledInput(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.onFocusChanged { focusState ->
                if (focusState.isFocused) {
                    coroutineScope.launch {
                        bottomSheetState.bottomSheetState.expand()
                    }
                    keyboardController?.hide() // Hide the system keyboard
                }
                if (focusState.hasFocus) {
                    coroutineScope.launch {
                        bottomSheetState.bottomSheetState.expand()
                    }
                    keyboardController?.hide() // Hide the system keyboard
                }
            })
    }

}

@Preview
@Composable
fun StyledInputXhvyBoardPreview() {
    var text by remember { mutableStateOf("") }
    XhvyTheme {
        Column {
            StyledInputXhvyBoard(
                value = text,
                onValueChange = { newValue -> text = newValue }  // Update the state here
            )
            Text(text = text)
        }
    }
}

