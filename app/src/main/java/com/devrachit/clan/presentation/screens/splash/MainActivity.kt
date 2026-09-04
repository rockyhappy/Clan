package com.devrachit.clan.presentation.screens.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.devrachit.clan.common.utils.navigate
import com.devrachit.clan.presentation.navigation.AuthRoute
import com.devrachit.clan.presentation.navigation.ClanNavDisplay
import com.devrachit.clan.presentation.navigation.MainRoute
import com.devrachit.clan.presentation.navigation.SplashRoute
import com.devrachit.clan.presentation.navigation.rememberNavHost
import com.devrachit.clan.presentation.screens.main.BaseActivity
import com.devrachit.clan.presentation.screens.viewmodels.ThemeViewModel
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

                ClanNavDisplay(
                    backStack = navController.backStack,
                    isDarkTheme = isDarkTheme,
                    onToggleTheme = { themeViewModel.toggleTheme(systemDark) },
                    onNavigateToMain = { MainRoute.navigate(navController) },
                    onNavigateToAuth = { AuthRoute.navigate(navController) }
                )
            }
        }
    }
}
