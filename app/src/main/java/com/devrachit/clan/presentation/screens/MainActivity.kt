package com.devrachit.clan.presentation.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.devrachit.clan.presentation.navigation.AuthRoute
import com.devrachit.clan.presentation.navigation.ClanNavDisplay
import com.devrachit.clan.presentation.navigation.LocalNavHost
import com.devrachit.clan.presentation.navigation.MainRoute
import com.devrachit.clan.presentation.navigation.SplashRoute
import com.devrachit.clan.presentation.navigation.rememberNavHost
import com.devrachit.clan.presentation.screens.splash.SplashViewModel
import com.devrachit.clan.presentation.viewmodels.ThemeViewModel
import com.devrachit.clan.presentation.theme.ClanTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val themeViewModel: ThemeViewModel = hiltViewModel()
            val splashViewModel: SplashViewModel = hiltViewModel()
            val themeMode by themeViewModel.themeMode.collectAsStateWithLifecycle()
            val isAuthenticated by splashViewModel.isAuthenticated.collectAsStateWithLifecycle()
            val systemDark = isSystemInDarkTheme()
            val isDarkTheme = themeMode.isDark(isSystemInDarkTheme())

            ClanTheme(darkTheme = isDarkTheme) {
                val navController = rememberNavHost(initialRoute = SplashRoute)

                CompositionLocalProvider(value = LocalNavHost provides navController) {
                    ClanNavDisplay(
                        backStack = LocalNavHost.current.backStack,
                        onToggleTheme = { themeViewModel.toggleTheme(systemDark) },
                        onNavigateToMain = { navController.replace(route = MainRoute) },
                        onNavigateToAuth = { navController.replace(route = AuthRoute) }
                    )
                }
            }
        }
    }
}