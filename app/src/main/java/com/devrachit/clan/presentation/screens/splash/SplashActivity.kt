package com.devrachit.clan.presentation.screens.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateListOf
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.runtime.NavEntry
import com.devrachit.clan.presentation.screens.auth.AuthScreen
import com.devrachit.clan.presentation.screens.main.MainActivity
import com.devrachit.clan.presentation.navigation.AuthRoute
import com.devrachit.clan.presentation.navigation.SplashRoute
import com.devrachit.clan.presentation.theme.ClanTheme
import com.devrachit.clan.presentation.theme.ThemeViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import com.devrachit.clan.presentation.navigation.rememberNavHost

@AndroidEntryPoint
class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val themeViewModel: ThemeViewModel = hiltViewModel()
            val themeMode by themeViewModel.themeMode.collectAsStateWithLifecycle()
            val systemDark = isSystemInDarkTheme()
            val isDarkTheme = themeMode.isDark(systemDark)

            ClanTheme(darkTheme = isDarkTheme) {
                val navController = rememberNavHost(SplashRoute)

                NavDisplay(
                    backStack = navController.backStack,
                    entryProvider = { key ->
                        when (key) {
                            is SplashRoute -> NavEntry(
                                key = key,
                                content = {
                                    // Read isDarkTheme from the outer collected state —
                                    // this lambda re-executes on recomposition so it
                                    // always reflects the latest themeMode value.
                                    SplashScreen(
                                        isDarkTheme = themeMode.isDark(systemDark),
                                        onToggleTheme = { themeViewModel.toggleTheme(systemDark) },
                                        onGetStarted = { navController.replace(AuthRoute) }
                                    )
                                }
                            )
                            is AuthRoute -> NavEntry(
                                key = key,
                                content = {
                                    AuthScreen(
                                        isDarkTheme = themeMode.isDark(systemDark),
                                        onToggleTheme = { themeViewModel.toggleTheme(systemDark) },
                                        onImport = {
                                            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                                            finish()
                                        }
                                    )
                                }
                            )
                            else -> error("Unknown route")
                        }
                    }
                )
            }
        }
    }
}
