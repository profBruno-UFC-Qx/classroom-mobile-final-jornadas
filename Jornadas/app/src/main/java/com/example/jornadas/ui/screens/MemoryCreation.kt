package com.example.jornadas.ui.screens

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.jornadas.R
import com.example.jornadas.ui.components.AppTextField
import com.example.jornadas.ui.components.DescriptionTextField
import com.example.jornadas.ui.components.FilledButton
import com.example.jornadas.ui.components.OptionsBottomBar
import com.example.jornadas.viewmodels.AppViewModelProvider
import com.example.jornadas.viewmodels.memorycreation.MemoryViewModel

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun MemoryCreation(
    cancel: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MemoryViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {

    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    var imageUri by rememberSaveable { mutableStateOf<Uri?>(null) }

    val galleryLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
            if(uri != null) {
                val flag = Intent.FLAG_GRANT_READ_URI_PERMISSION
                context.contentResolver.takePersistableUriPermission(uri, flag)
                imageUri = uri
                viewModel.onImageUriChange(uri.toString())
            }
        }

    val storagePermission =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                galleryLauncher.launch("image/*")
            } else {
                Toast.makeText(context, "Permissão não concedida", Toast.LENGTH_SHORT)
                    .show()
            }
        }

    Scaffold(
        bottomBar = {
            OptionsBottomBar(
                onCreateClick = {
                    viewModel.saveMemory()
                    cancel()
                },
                text = "Criar"
            )
        }
    ) { paddingValues ->
        Column(modifier = modifier.padding(paddingValues).verticalScroll(scrollState)) {
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
                value = uiState.title,
                onValueChange = { viewModel.onTitleChange(it) },
                label = "Título*",
                modifier = Modifier
            )

            Spacer(Modifier.height(16.dp))

            DescriptionTextField(
                value = uiState.description,
                onValueChange = { viewModel.onDescriptionChange(it) },
                label = "Descrição*",
                modifier = Modifier
            )

            Spacer(Modifier.height(16.dp))

            AppTextField(
                value = uiState.date,
                onValueChange = { viewModel.onDateChange(it) },
                label = "Data*",
                leadIcon = Icons.Default.CalendarToday,
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

            // Preview da imagem
            if(imageUri != null) {
                Spacer(Modifier.height(16.dp))
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(imageUri)
                            .build(),
                        contentDescription = "Imagem selecionada",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .padding(horizontal = 16.dp)
                    )
                }

            FilledButton(
                onclick = {
                    storagePermission.launch(android.Manifest.permission.READ_MEDIA_IMAGES)
                },
                text = stringResource(R.string.image_btn),
                icon = Icons.Default.Image,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            )
        }
    }
}