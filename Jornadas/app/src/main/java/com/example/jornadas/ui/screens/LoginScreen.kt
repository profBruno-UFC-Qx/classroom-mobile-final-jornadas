package com.example.jornadas.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.jornadas.R
import com.example.jornadas.ui.components.AppTextField
import com.example.jornadas.ui.components.FilledButton

@Composable
fun LoginScreen(onLoginClick: () -> Unit, onRegisterClick: () -> Unit, modifier: Modifier = Modifier) {
    //mudar para o viewModel depois
    var mock by remember { mutableStateOf("") } //mock para os textfields
    Column (
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            modifier = Modifier.padding(horizontal = 18.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.jornadas_logo),
                    contentDescription = "logo do app jornadas",
                    tint = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = stringResource(R.string.app_name),
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.headlineLarge,
                )
                Text(
                    text = stringResource(R.string.app_description),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.labelLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(10.dp)
                )

                Spacer(modifier = Modifier.height(32.dp))

                AppTextField(
                    value = mock,
                    onValueChange = { mock = it },
                    label = "Email*",
                    modifier = Modifier
                )

                Spacer(modifier = Modifier.height(16.dp))

                AppTextField(
                    value = mock,
                    onValueChange = { mock = it },
                    label = "Senha*",
                    modifier = Modifier
                )

                Spacer(modifier = Modifier.height(16.dp))

                FilledButton(
                    onclick = onLoginClick,
                    stringResource(R.string.login),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
                
                Spacer(modifier = Modifier.height(16.dp))

                TextButton(
                    onClick = onRegisterClick
                ) {
                    Text(
                        stringResource(R.string.register_option),
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }
        }
    }
}