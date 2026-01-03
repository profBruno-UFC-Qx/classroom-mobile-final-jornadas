package com.example.jornadas.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun FilledButton(
    onclick: () -> Unit,
    text: String,
    icon: ImageVector? = null,
    shape: Shape = RoundedCornerShape(8.dp),
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onclick,
        shape = shape,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.scrim,
            contentColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Row() {
            if(icon != null) {
                Icon(imageVector = icon, contentDescription = text, modifier = Modifier.padding(end = 8.dp))
            }
            Text(text)
        }
    }
}