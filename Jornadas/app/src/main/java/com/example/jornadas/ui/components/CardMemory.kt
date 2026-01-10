package com.example.jornadas.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.jornadas.R
import com.example.jornadas.data.entities.Memory
import java.time.format.DateTimeFormatter

@Composable
fun CardMemory(
    memory: Memory,
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        modifier = Modifier.padding(horizontal = 18.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(memory.imageUri)
                    .crossfade(true)
                    .error(R.drawable.placeholder)
                    .fallback(R.drawable.placeholder)
                    .placeholder(R.drawable.placeholder)
                    .build(),
                contentDescription = "imagem da memória",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )
            Text(
                text = memory.title,
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.padding(top = 8.dp, bottom = 4.dp)
            )
            Text(
                text = memory.description,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Row(
                verticalAlignment = Alignment.Top,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "Location Icon",
                    modifier = Modifier
                        .size(20.dp)
                        .padding(end = 6.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = memory.location,
                    style = MaterialTheme.typography.labelSmall,
                    lineHeight = 18.sp
                )
            }
            Text(
                text = memory.date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}


