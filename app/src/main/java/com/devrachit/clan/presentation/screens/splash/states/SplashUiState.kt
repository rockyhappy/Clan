package com.devrachit.clan.presentation.screens.splash.states

/**
 * Represents the UI state of the Splash Screen.
 *
 * The splash screen has three possible states:
 * - [Loading]: Initial state while the auth status is being determined (null from UseCase).
 * - [Authenticated]: User has a valid auth token — show the "Checking…" UI then auto-redirect.
 * - [Onboarding]: No auth token — show the onboarding horizontal pager.
 */
sealed interface SplashUiState {

    /** Auth status is still being determined. */
    data object Loading : SplashUiState

    /** User is authenticated — show the "Returning to Village…" state. */
    data object Authenticated : SplashUiState

    /** User is not authenticated — show the onboarding pager. */
    data object Onboarding : SplashUiState

    /** User data is not yet checked. */
    data object Idle : SplashUiState
}