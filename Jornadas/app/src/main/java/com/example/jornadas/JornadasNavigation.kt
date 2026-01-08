package com.example.jornadas

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.jornadas.ui.components.AppTopBar
import com.example.jornadas.ui.components.OptionsBottomBar
import com.example.jornadas.ui.screens.HomeBottomBar
import com.example.jornadas.ui.screens.HomeScreen
import com.example.jornadas.ui.screens.LoginScreen
import com.example.jornadas.ui.screens.MemoryCreation
import com.example.jornadas.ui.screens.RegisterScreen

sealed class AppScreens(val route: String) {
    data object Login: AppScreens("login")
    data object Register: AppScreens("register")
    data object Home: AppScreens("home")
    data object Map: AppScreens("map")
    data object MemoryCreation: AppScreens("memoryCreation")
}
@Composable
fun JornadasApp() {

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val showTopBar = when (currentRoute) {
        AppScreens.Login.route, AppScreens.Register.route, AppScreens.MemoryCreation.route -> false
        else -> true
    }
    val showBottomBar = when(currentRoute) {
        AppScreens.Home.route, AppScreens.MemoryCreation.route -> true
        else -> false
    }


    Scaffold(
        topBar = {
            if (showTopBar) AppTopBar(
                isSystemInDarkTheme(),
                modifier = Modifier
                    .statusBarsPadding()
                    .clip(shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            )
        },
        bottomBar = {
            if(showBottomBar) {
                when(currentRoute) {
                    AppScreens.Home.route -> { HomeBottomBar(
                        onMapClick = { navController.navigate(AppScreens.Map.route) },
                        createMemory = { navController.navigate(AppScreens.MemoryCreation.route) }
                    )}
                    AppScreens.MemoryCreation.route -> {
                        OptionsBottomBar(
                            onCreateClick = {  },
                            text = "Criar",
                        )
                    }
                }

            }

        },
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = AppScreens.Login.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = AppScreens.Login.route) {
                LoginScreen(
                    onLoginClick = {
                        navController.navigate(AppScreens.Home.route) {
                            popUpTo(AppScreens.Login.route) { inclusive = true }
                        }
                    },
                    onRegisterClick = {
                        navController.navigate(AppScreens.Register.route)
                    }
                )
            }
            composable(route = AppScreens.Register.route) {
                RegisterScreen(
                    onRegisterClick = {
                        navController.popBackStack()
                    },
                    onLoginClick = {
                        navController.popBackStack()
                    },
                )
            }
            composable(route = AppScreens.Home.route) {
                HomeScreen()
            }
            composable(route = AppScreens.MemoryCreation.route) {
                MemoryCreation(cancel = { navController.popBackStack() })
            }
        }
    }

}