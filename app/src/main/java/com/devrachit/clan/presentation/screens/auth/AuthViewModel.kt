package com.devrachit.clan.presentation.screens.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devrachit.clan.common.constants.AppStrings
import com.devrachit.clan.domain.usecase.auth.ImportVillageUseCase
import com.devrachit.clan.presentation.screens.auth.states.AuthUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val importVillageUseCase: ImportVillageUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<AuthUiState>(AuthUiState.Idle)
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    fun importVillage(jsonString: String) {
        if (jsonString.isBlank()) {
            _uiState.value = AuthUiState.Error(AppStrings.Auth.ERROR_EMPTY_JSON)
            return
        }

        _uiState.value = AuthUiState.Loading

        viewModelScope.launch {
            val success = importVillageUseCase(jsonString)
            if (success) {
                _uiState.value = AuthUiState.Success
            } else {
                _uiState.value = AuthUiState.Error(AppStrings.Auth.ERROR_INVALID_JSON)
            }
        }
    }

    fun resetState() {
        _uiState.value = AuthUiState.Idle
    }
}
