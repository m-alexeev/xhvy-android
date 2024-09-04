package com.example.xhvy.ui.components.general

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.xhvy.data.models.exerciseCategories
import com.example.xhvy.ui.theme.XhvyTheme

@Composable
fun <T> DialogWithList(
    modifier: Modifier = Modifier,
    header: String,
    selected: T?,
    onDismissRequest: () -> Unit,
    onSelect: (T?) -> Unit,
    items: List<T>,
) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        var selected by remember {
            mutableStateOf<T?>(selected)
        }
        Card(
            modifier = modifier
                .fillMaxWidth()
                .height(475.dp)
                .padding(horizontal = 12.dp)
        ) {
            Box(
                Modifier
                    .fillMaxHeight()
                    .padding(12.dp)
            ) {
                Column(
                    Modifier
                        .fillMaxWidth()
                ) {
                    Text(text = header, style = MaterialTheme.typography.titleMedium)
                    LazyColumn(
                        Modifier
                            .padding(top = 8.dp, bottom = 32.dp)
                    ) {
                        items(items = items, key = { it.toString() }) { item ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                RadioButton(
                                    selected = item == selected,
                                    onClick = { selected = item })
                                Text(item.toString())
                            }
                        }
                    }
                }
                Text(
                    text = "OK",
                    modifier = Modifier
                        .clickable {
                            onSelect(selected)
                            onDismissRequest()
                        }
                        .align(Alignment.BottomEnd),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary,

                    )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DialogWithListPreview() {
    XhvyTheme {

        DialogWithList(
            onDismissRequest = { /*TODO*/ },
            selected = null,
            items = exerciseCategories,
            onSelect = {},
            header = "Category"
        )
    }
}