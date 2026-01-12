package com.example.jornadas.ui.screens

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.net.Uri
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.jornadas.R
import com.example.jornadas.ui.components.AppTextField
import com.example.jornadas.ui.components.DescriptionTextField
import com.example.jornadas.ui.components.FilledButton
import com.example.jornadas.ui.components.OptionsBottomBar
import com.example.jornadas.viewmodels.AppViewModelProvider
import com.example.jornadas.viewmodels.memorycreation.MemoryViewModel
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun MemoryCreation(
    navController: NavController,
    cancel: () -> Unit,
    openMap: () -> Unit,
    modifier: Modifier = Modifier,
    memoryId: String? = null,
    viewModel: MemoryViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()

    val savedStateHandle = navController.currentBackStackEntry?.savedStateHandle

    val enderecoDoMapaState = savedStateHandle
        ?.getLiveData<String>("location_address")
        ?.observeAsState()

    val enderecoDoMapa = enderecoDoMapaState?.value
    LaunchedEffect(enderecoDoMapa) {
        enderecoDoMapa?.let { endereco ->
            viewModel.onLocationChange(endereco)
            savedStateHandle?.remove<String>("location_address")
        }
    }
    var isLoadingLocation by remember { mutableStateOf(false) }


    val locationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val fineLocation = permissions[Manifest.permission.ACCESS_FINE_LOCATION] ?: false
        val coarseLocation = permissions[Manifest.permission.ACCESS_COARSE_LOCATION] ?: false

        if (fineLocation || coarseLocation) {

            coroutineScope.launch {
                isLoadingLocation = true
                val address = getDeviceLocation(context)
                if (address != null) {
                    viewModel.onLocationChange(address)
                } else {
                    Toast.makeText(context, "Local não encontrado. Tente o Mapa.", Toast.LENGTH_SHORT).show()
                }
                isLoadingLocation = false
            }
        } else {
            Toast.makeText(context, "Permissão de localização negada.", Toast.LENGTH_SHORT).show()
        }
    }

    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val isEditMode = memoryId != null

    LaunchedEffect(memoryId) {
        if (memoryId != null) viewModel.loadMemory(memoryId)
    }

    LaunchedEffect(uiState.imageUri) {
        if (uiState.imageUri?.isNotEmpty() == true) imageUri = Uri.parse(uiState.imageUri)
    }

    val galleryLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri ->
        if(uri != null) {
            val flag = Intent.FLAG_GRANT_READ_URI_PERMISSION
            context.contentResolver.takePersistableUriPermission(uri, flag)
            imageUri = uri
            viewModel.onImageUriChange(uri.toString())
        }
    }

    val storagePermission = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) galleryLauncher.launch("image/*")
        else Toast.makeText(context, "Permissão de galeria negada", Toast.LENGTH_SHORT).show()
    }

    Scaffold(
        bottomBar = {
            OptionsBottomBar(
                onCreateClick = {
                    if(isEditMode) viewModel.updateMemory() else viewModel.saveMemory()
                    cancel()
                },
                text = if(isEditMode) "Salvar" else "Criar"
            )
        }
    ) { paddingValues ->
        Column(modifier = modifier.padding(paddingValues).verticalScroll(scrollState)) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = modifier.fillMaxWidth().padding(16.dp)
            ) {
                Text(
                    stringResource(R.string.new_memory),
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                IconButton(onClick = cancel) {
                    Icon(Icons.Default.Close, contentDescription = "Fechar", tint = MaterialTheme.colorScheme.primary)
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

            AppTextField(
                value = if (isLoadingLocation) "Buscando GPS..." else uiState.location,
                onValueChange = { viewModel.onLocationChange(it) },
                label = stringResource(R.string.select_location),
                leadIcon = Icons.Default.Info,
                modifier = Modifier
            )

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                FilledButton(
                    onclick = {
                        locationPermissionLauncher.launch(
                            arrayOf(
                                Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION
                            )
                        )
                    },
                    text = "GPS",
                    icon = Icons.Default.GpsFixed,
                    modifier = Modifier.padding(8.dp).weight(1f)
                )

                FilledButton(
                    onclick = openMap,
                    text = "MAPA",
                    icon = Icons.Default.Map,
                    modifier = Modifier.padding(8.dp).weight(1f)
                )
            }

            if(imageUri != null) {
                Spacer(Modifier.height(16.dp))
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current).data(imageUri).build(),
                    contentDescription = "Imagem selecionada",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth().height(200.dp).padding(horizontal = 16.dp)
                )
            }

            FilledButton(
                onclick = { storagePermission.launch(Manifest.permission.READ_MEDIA_IMAGES) },
                text = stringResource(R.string.image_btn),
                icon = Icons.Default.Image,
                modifier = Modifier.padding(8.dp).fillMaxWidth()
            )
        }
    }
}

private suspend fun getDeviceLocation(context: Context): String? {

    val hasFine = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
    val hasCoarse = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED

    if (!hasFine && !hasCoarse) return null

    return withContext(Dispatchers.IO) {
        try {
            val fusedClient = LocationServices.getFusedLocationProviderClient(context)

            var location = try { fusedClient.lastLocation.await() } catch (e: Exception) { null }

            if (location == null) {

                val priority = if (hasFine) Priority.PRIORITY_HIGH_ACCURACY else Priority.PRIORITY_BALANCED_POWER_ACCURACY
                val token = CancellationTokenSource().token
                location = fusedClient.getCurrentLocation(priority, token).await()
            }

            if (location == null) return@withContext null

            val geocoder = Geocoder(context, Locale.getDefault())
            val addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1)

            if (!addresses.isNullOrEmpty()) {
                val address = addresses[0]
                "${address.thoroughfare ?: "Rua desconhecida"}, ${address.subLocality ?: ""}"
            } else {
                "${location.latitude}, ${location.longitude}"
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}