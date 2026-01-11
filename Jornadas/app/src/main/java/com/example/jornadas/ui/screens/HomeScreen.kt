package com.example.jornadas.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Map
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jornadas.R
import com.example.jornadas.data.entities.Memory
import com.example.jornadas.ui.components.CardMemory
import com.example.jornadas.ui.components.HomeButton
import com.example.jornadas.viewmodels.AppViewModelProvider
import com.example.jornadas.viewmodels.home.HomeViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory),
    onEditMemory: (Memory) -> Unit = {},
) {

    val uiState = viewModel.homeUiState.collectAsState()

    val username = Firebase.auth.currentUser?.displayName ?: "Usuário"

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        UserInfo(username, memoryCount = uiState.value.memoryList.size)

        Spacer(modifier = Modifier.height(20.dp))

        if(uiState.value.memoryList.isEmpty()) {
            NoMemories()
        } else {
            LazyColumn(contentPadding = PaddingValues(bottom = 100.dp)) {
                items(items = uiState.value.memoryList, key = { it.id }) { memory ->
                    CardMemory(
                        memory = memory,
                        onEdit = onEditMemory,
                        onDelete = { viewModel.deleteMemory(memory) }
                    )
                }
            }
        }
    }
}

@Composable
fun UserInfo(username: String, memoryCount: Int, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(30.dp)
    ) {
        Text(
            text = "Olá, $username!",
            style = MaterialTheme.typography.labelMedium
        )
        Text(
            text = "Você tem $memoryCount memórias criadas",
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