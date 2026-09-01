package com.devrachit.clan.presentation.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devrachit.clan.domain.model.ThemeMode
import com.devrachit.clan.domain.usecase.theme.GetThemeModeUseCase
import com.devrachit.clan.domain.usecase.theme.SetThemeModeUseCase
import com.devrachit.clan.domain.usecase.theme.ToggleThemeModeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel responsible for managing and persisting ThemeMode state.
 *
 * Dependencies are injected by Hilt — no manual ViewModelProvider.Factory needed.
 */
@HiltViewModel
class ThemeViewModel @Inject constructor(
    private val getThemeModeUseCase: GetThemeModeUseCase,
    private val setThemeModeUseCase: SetThemeModeUseCase,
    private val toggleThemeModeUseCase: ToggleThemeModeUseCase
) : ViewModel() {

    val themeMode: StateFlow<ThemeMode> = getThemeModeUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ThemeMode.SYSTEM
        )

    fun toggleTheme(isSystemDark: Boolean) {
        viewModelScope.launch {
            toggleThemeModeUseCase(isSystemDark)
        }
    }

    fun setThemeMode(mode: ThemeMode) {
        viewModelScope.launch {
            setThemeModeUseCase(mode)
        }
    }
}
