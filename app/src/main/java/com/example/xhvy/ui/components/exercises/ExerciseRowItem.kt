package com.example.xhvy.ui.components.exercises

import androidx.compose.foundation.Image
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.xhvy.R
import com.example.xhvy.data.models.Exercise
import com.example.xhvy.ui.theme.XhvyTheme
import com.example.xhvy.ui.view_models.getExercises

@Composable
fun ExerciseRowItem(exercise: Exercise, modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp, 6.dp)
    ) {
        Box(modifier = Modifier
            .size(64.dp)
            .clip(CircleShape)
            .background(color = Color.Gray)) {
            Image(
                painter = painterResource(id = R.drawable.sample),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.matchParentSize()
            )
        }
        Column(
            Modifier
                .padding(12.dp)
                .fillMaxWidth()) {
            Text(text = exercise.name, style = MaterialTheme.typography.titleMedium)
            Text(
                text = exercise.category.toString(),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ExerciseRowPreview() {
    XhvyTheme {
        ExerciseRowItem(getExercises[0])
    }
}
