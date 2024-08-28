package com.example.xhvy.ui.components.general

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.xhvy.R
import com.example.xhvy.ui.theme.XhvyTheme

@Composable
fun Searchbar(modifier: Modifier = Modifier, search: String, onSearchChange: (String) -> Unit) {
    TextField(
        value = search,
        placeholder = { Text(stringResource(id = R.string.search_placeholder))},
        colors = TextFieldDefaults.colors(unfocusedContainerColor = MaterialTheme.colorScheme.surface),
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
        onValueChange = onSearchChange,
        modifier = modifier
    )
}

@Preview(showBackground = false)
@Composable
fun SearchBarPreview() {
    XhvyTheme {
        Searchbar(search = "", onSearchChange = {})
    }
}