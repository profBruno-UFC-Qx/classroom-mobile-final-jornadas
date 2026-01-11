package com.example.jornadas

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.compose.JornadasTheme
import com.example.jornadas.ui.components.AppTopBar
import com.example.jornadas.ui.screens.HomeBottomBar
import com.example.jornadas.ui.screens.HomeScreen
import com.example.jornadas.ui.screens.LoginScreen
import com.example.jornadas.ui.screens.MemoryCreation
import com.example.jornadas.ui.screens.RegisterScreen
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

sealed class AppScreens(val route: String) {
    data object Login: AppScreens("login")
    data object Register: AppScreens("register")
    data object Home: AppScreens("home")
    data object Map: AppScreens("map")
    data object MemoryCreation: AppScreens("memoryCreation")

    data object EditMemory : AppScreens("editMemory")
}
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun JornadasApp() {

    var isDarkMode by remember { mutableStateOf(true) }
    val auth = Firebase.auth

    JornadasTheme(darkTheme = isDarkMode) {
        val navController = rememberNavController()
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val showTopBar = when (currentRoute) {
            AppScreens.Login.route, AppScreens.Register.route, AppScreens.MemoryCreation.route -> false
            else -> true
        }
        val showBottomBar = when(currentRoute) {
            AppScreens.Home.route -> true
            else -> false
        }


        Scaffold(
            topBar = {
                if (showTopBar) AppTopBar(
                    modifier = Modifier
                        .statusBarsPadding()
                        .clip(shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)),
                    isDarkMode = isDarkMode,
                    onThemeChange = { isDarkMode = !isDarkMode },
                    onSignOut = {
                        auth.signOut()
                        navController.navigate(AppScreens.Login.route) {
                            popUpTo(0) { inclusive = true}
                        }
                    }
                )
            },
            bottomBar = {
                if(showBottomBar) {
                    HomeBottomBar(
                        onMapClick = { navController.navigate(AppScreens.Map.route) },
                        createMemory = { navController.navigate(AppScreens.MemoryCreation.route) }
                    )
                }
            },
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize()
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = if(auth.currentUser != null) {
                    AppScreens.Home.route
                } else {
                    AppScreens.Login.route
                },
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
                    HomeScreen(
                        onEditMemory = { memory ->
                            navController.navigate("${AppScreens.MemoryCreation.route}?memoryId=${memory.id}")
                        }
                    )
                }
                composable(
                    route = "${AppScreens.MemoryCreation.route}?memoryId={memoryId}",
                    arguments = listOf(navArgument("memoryId") { type = NavType.StringType; nullable = true; defaultValue = null })
                ) { backStackEntry ->
                    val memoryId = backStackEntry.arguments?.getString("memoryId")
                    MemoryCreation(
                        cancel = { navController.popBackStack() },
                        memoryId = memoryId
                    )
                }
            }
        }
    }
}