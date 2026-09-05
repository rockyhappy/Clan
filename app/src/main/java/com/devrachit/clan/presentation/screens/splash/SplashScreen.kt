package com.devrachit.clan.presentation.screens.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.devrachit.clan.presentation.screens.splash.components.SplashCheckingContent
import com.devrachit.clan.presentation.screens.splash.components.SplashOnboardingContent
import com.devrachit.clan.presentation.screens.splash.states.SplashUiState
import com.devrachit.clan.presentation.theme.ClanTheme
import kotlinx.coroutines.delay

/**
 * Root splash screen composable.
 *
 * Observes [SplashViewModel.splashUiState] and renders the appropriate
 * sub-screen based on the auth state:
 * - [SplashUiState.Loading] / [SplashUiState.Authenticated] → [SplashCheckingContent]
 * - [SplashUiState.Onboarding] → [SplashOnboardingContent]
 *
 * When authenticated, it auto-redirects to Main after a brief delay.
 */
@Composable
fun SplashScreen(
    onToggleTheme: () -> Unit = {},
    onNavigateToAuth: () -> Unit = {},
    onNavigateToMain: () -> Unit = {}
) {
    val splashViewModel: SplashViewModel = hiltViewModel()
    val uiState by splashViewModel.splashUiState.collectAsStateWithLifecycle()
    
    // Auto-redirect when authenticated
    if (uiState is SplashUiState.Authenticated) {
        LaunchedEffect(Unit) {
            delay(1500L)
            onNavigateToMain()
        }
    }

    SplashScreenContent(
        uiState = uiState,
        onToggleTheme = onToggleTheme,
        onNavigateToAuth = onNavigateToAuth
    )
}

@Composable
internal fun SplashScreenContent(
    uiState: SplashUiState,
    onToggleTheme: () -> Unit,
    onNavigateToAuth: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ClanTheme.colors.background)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        when (uiState) {
            is SplashUiState.Loading,
            is SplashUiState.Idle,
            is SplashUiState.Authenticated -> {
                SplashCheckingContent(
                    onToggleTheme = onToggleTheme
                )
            }
            is SplashUiState.Onboarding -> {
                SplashOnboardingContent(
                    onToggleTheme = onToggleTheme,
                    onNavigateToAuth = onNavigateToAuth
                )
            }
        }
    }
}

@androidx.compose.ui.tooling.preview.Preview(showBackground = true)
@Composable
private fun SplashScreenCheckingPreview() {
    ClanTheme(darkTheme = false) {
        SplashScreenContent(
            uiState = SplashUiState.Loading,
            onToggleTheme = {},
            onNavigateToAuth = {}
        )
    }
}

@androidx.compose.ui.tooling.preview.Preview(showBackground = true)
@Composable
private fun SplashScreenOnboardingPreview() {
    ClanTheme(darkTheme = true) {
        SplashScreenContent(
            uiState = SplashUiState.Onboarding,
            onToggleTheme = {},
            onNavigateToAuth = {}
        )
    }
}
