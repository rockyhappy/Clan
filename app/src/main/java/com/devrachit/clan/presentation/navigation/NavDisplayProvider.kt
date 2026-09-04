package com.devrachit.clan.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.ui.NavDisplay
import com.devrachit.clan.presentation.screens.auth.AuthScreen
import com.devrachit.clan.presentation.screens.main.ClanDashboardScreen
import com.devrachit.clan.presentation.screens.splash.SplashScreen

/**
 * Provides the [NavDisplay] wiring for the splash-flow Activity.
 *
 * Theme state is read from [ClanTheme.isDarkTheme] via CompositionLocal —
 * no need to thread `isDarkTheme` parameters through here.
 */
@Composable
fun ClanNavDisplay(
    backStack: NavBackStack<NavKey>,
    onToggleTheme: () -> Unit,
    onNavigateToMain: () -> Unit = {},
    onNavigateToAuth: () -> Unit = {},
) {
    NavDisplay(
        backStack = backStack,
        entryProvider = { key ->
            when (key) {
                is SplashRoute -> NavEntry(
                    key = key,
                    content = {
                        SplashScreen(
                            onToggleTheme = onToggleTheme,
                            onNavigateToAuth = onNavigateToAuth,
                            onNavigateToMain = onNavigateToMain
                        )
                    }
                )
                is AuthRoute -> NavEntry(
                    key = key,
                    content = {
                        AuthScreen(
                            onToggleTheme = onToggleTheme,
                            onImport = onNavigateToMain
                        )
                    }
                )
                is MainRoute -> NavEntry(
                    key = key,
                    content = {
                        ClanDashboardScreen(
                            onToggleTheme = onToggleTheme
                        )
                    }
                )
                else -> NavEntry(
                    key = key,
                    content = {
                        ClanDashboardScreen(
                            onToggleTheme = onToggleTheme
                        )
                    }
                )
            }
        }
    )
}