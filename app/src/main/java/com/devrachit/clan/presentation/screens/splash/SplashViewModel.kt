package com.devrachit.clan.presentation.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devrachit.clan.domain.usecase.auth.CheckAuthStatusUseCase
import com.devrachit.clan.presentation.screens.splash.states.SplashUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    checkAuthStatusUseCase: CheckAuthStatusUseCase
) : ViewModel() {


    val isAuthenticated: StateFlow<Boolean?> = checkAuthStatusUseCase()
        .map { it }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )


    val splashUiState: StateFlow<SplashUiState> = isAuthenticated
        .map { authStatus ->
            when (authStatus) {
                null  -> SplashUiState.Loading
                true  -> SplashUiState.Authenticated
                false -> SplashUiState.Onboarding
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = SplashUiState.Loading
        )
}
