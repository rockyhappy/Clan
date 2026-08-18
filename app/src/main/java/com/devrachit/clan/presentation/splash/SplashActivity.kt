package com.devrachit.clan.presentation.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateListOf
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.runtime.NavEntry
import com.devrachit.clan.presentation.auth.AuthScreen
import com.devrachit.clan.presentation.main.MainActivity
import com.devrachit.clan.presentation.navigation.AuthRoute
import com.devrachit.clan.presentation.navigation.SplashRoute
import com.devrachit.clan.presentation.theme.ClanTheme
import com.devrachit.clan.presentation.theme.ThemeViewModel

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val themeViewModel: ThemeViewModel = viewModel(
                factory = ThemeViewModel.provideFactory(this@SplashActivity)
            )
            val themeMode by themeViewModel.themeMode.collectAsState()
            val systemDark = isSystemInDarkTheme()
            val isDarkTheme = themeMode.isDark(systemDark)

            ClanTheme(darkTheme = isDarkTheme) {
                val backStack = remember { mutableStateListOf<Any>(SplashRoute) }

                NavDisplay(
                    backStack = backStack,
                    entryProvider = { key ->
                        when (key) {
                            is SplashRoute -> NavEntry(
                                key = key,
                                content = {
                                    SplashScreen(
                                        isDarkTheme = isDarkTheme,
                                        onToggleTheme = { themeViewModel.toggleTheme(systemDark) },
                                        onGetStarted = { backStack.add(AuthRoute) }
                                    )
                                }
                            )
                            is AuthRoute -> NavEntry(
                                key = key,
                                content = {
                                    AuthScreen(
                                        isDarkTheme = isDarkTheme,
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
