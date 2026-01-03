package com.example.jornadas.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.GpsFixed
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Map
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.jornadas.R
import com.example.jornadas.ui.components.AppTextField
import com.example.jornadas.ui.components.DescriptionTextField
import com.example.jornadas.ui.components.FilledButton

@Composable
fun MemoryCreation(cancel: () -> Unit, modifier: Modifier = Modifier) {
    var mock by remember { mutableStateOf("") }
    Column {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                stringResource(R.string.new_memory),
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary
            )
            IconButton(
                onClick = cancel
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Fechar",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
        AppTextField(
            value = mock,
            onValueChange = {mock = it},
            label = "Título*",
            modifier = Modifier
        )

        Spacer(Modifier.height(16.dp))

        DescriptionTextField(
            value = mock,
            onValueChange = {mock = it},
            label = "Descrição*",
            modifier = Modifier
        )

        Spacer(Modifier.height(16.dp))

        AppTextField(
            value = mock,
            onValueChange = {mock = it},
            label = "Data*",
            icon = Icons.Default.CalendarToday,
            modifier = Modifier
        )

        Text(
            "Localização",
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier.padding(top = 16.dp, start = 16.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(8.dp)
                .background(MaterialTheme.colorScheme.surface)
                .heightIn(min = 60.dp)
                .fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = "Info Icon",
                modifier = Modifier.padding(16.dp)
            )
            Text(
                stringResource(R.string.select_location),
                style = MaterialTheme.typography.labelMedium
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            FilledButton(
                onclick = {},
                text = "GPS",
                icon = Icons.Default.GpsFixed,
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1f)
            )
            FilledButton(
                onclick = {},
                text = "MAPA",
                icon = Icons.Default.Map,
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1f)
            )
        }
        FilledButton(
            onclick = {},
            text = stringResource(R.string.image_btn),
            icon = Icons.Default.Image,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        )
    }
}