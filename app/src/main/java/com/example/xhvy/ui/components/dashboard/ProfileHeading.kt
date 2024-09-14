package com.example.xhvy.ui.components.dashboard

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.xhvy.R
import com.example.xhvy.ui.components.general.FaIcon
import com.example.xhvy.ui.theme.XhvyTheme

@Composable
fun ProfileHeading(name: String, workouts: Int, modifier: Modifier = Modifier) {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.surfaceVariant),
            contentAlignment = Alignment.Center
        ) {
            FaIcon(
                iconPainterId = R.drawable.ic_user_round,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.secondary
            )
        }

        Column(modifier = Modifier.padding(start = 12.dp)) {
            Text(text = name, style = MaterialTheme.typography.titleMedium)
            Text(
                text = "$workouts Workouts",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary
//                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProfileHeading() {
    XhvyTheme {
        ProfileHeading(name = "Misha Alexeev", workouts = 312)
    }
}
