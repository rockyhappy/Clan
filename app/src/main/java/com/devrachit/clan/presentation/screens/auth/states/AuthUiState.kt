package com.devrachit.clan.presentation.screens.auth.states

/**
 * Represents the UI state of the Auth Screen.
 */
sealed interface AuthUiState {
    data object Idle : AuthUiState
    data object Loading : AuthUiState
    data object Success : AuthUiState
    data class Error(val message: String) : AuthUiState
}
