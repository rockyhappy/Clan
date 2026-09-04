package com.devrachit.clan.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.ui.NavDisplay
import com.devrachit.clan.presentation.screens.auth.AuthScreen
import com.devrachit.clan.presentation.screens.main.ClanDashboardScreen
import com.devrachit.clan.presentation.screens.splash.SplashScreen
import com.devrachit.clan.domain.model.ThemeMode

/**
 * Provides the [NavDisplay] wiring for the splash-flow Activity.
 *
 * All Activity-scoped values (theme state, navigation controller, Activity
 * callbacks) are passed in as parameters so this composable stays
 * framework-agnostic and testable.
 */
@Composable
fun ClanNavDisplay(
    backStack: NavBackStack<NavKey>,
    isDarkTheme: Boolean,
    onToggleTheme: () -> Unit,
    onNavigateToMain: () -> Unit ={},
    onNavigateToAuth: () -> Unit ={},
) {
    NavDisplay(
        backStack = backStack,
        entryProvider = { key ->
            when (key) {
                is SplashRoute -> NavEntry(
                    key = key,
                    content = {
                        SplashScreen(
                            isDarkTheme = isDarkTheme,
                            onToggleTheme = onToggleTheme,
                            onGetStarted = onNavigateToAuth,
                            onNavigateToMain = onNavigateToMain
                        )
                    }
                )
                is AuthRoute -> NavEntry(
                    key = key,
                    content = {
                        AuthScreen(
                            isDarkTheme = isDarkTheme,
                            onToggleTheme = onToggleTheme,
                            onImport = onNavigateToMain
                        )
                    }
                )
                is MainRoute -> NavEntry(
                    key = key,
                    content = {
                        ClanDashboardScreen(
                            isDarkTheme = isDarkTheme,
                            onToggleTheme = onToggleTheme
                        )
                    }
                )
                else -> NavEntry(
                    key = key,
                    content = {
                        ClanDashboardScreen(
                            isDarkTheme = isDarkTheme,
                            onToggleTheme = onToggleTheme
                        )
                    }
                )
            }
        }
    )
}