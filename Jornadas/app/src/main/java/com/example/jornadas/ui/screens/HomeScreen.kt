package com.example.jornadas.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Map
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jornadas.R
import com.example.jornadas.ui.components.HomeButton

var memories = 0


@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        UserInfo()

        Spacer(modifier = Modifier.height(20.dp))

        if(memories == 0) NoMemories()
    }
}

@Composable
fun UserInfo(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(30.dp)
    ) {
        Text(
            text = "Olá, Usuário!",
            style = MaterialTheme.typography.labelMedium
        )
        Text(
            text = "$memories memórias criadas",
            style = MaterialTheme.typography.labelSmall
        )
    }
}

@Composable
fun NoMemories(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {
        Icon(
            painter = painterResource(R.drawable.jornadas_logo),
            contentDescription = "logo do app jornadas",
            tint = MaterialTheme.colorScheme.primary
        )

        Text(
            stringResource(R.string.no_memories),
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            stringResource(R.string.no_memories_label),
            style = MaterialTheme.typography.labelSmall,
        )
    }
}

@Composable
fun HomeBottomBar(onMapClick: () -> Unit = {}, createMemory: () -> Unit = {}) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 25.dp, vertical = 60.dp)
            .fillMaxWidth()
    ) {
        HomeButton(onclick = onMapClick, icon = Icons.Default.Map, description = "Mapa Interativo")

        HomeButton(onclick = createMemory, icon = Icons.Default.Add, description = "Criar Memória")
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}