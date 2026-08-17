package com.devrachit.clan.presentation.theme

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.devrachit.clan.data.local.datastore.ThemeDataStore
import com.devrachit.clan.data.repository.ThemeRepositoryImpl
import com.devrachit.clan.domain.model.ThemeMode
import com.devrachit.clan.domain.usecase.theme.GetThemeModeUseCase
import com.devrachit.clan.domain.usecase.theme.SetThemeModeUseCase
import com.devrachit.clan.domain.usecase.theme.ToggleThemeModeUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * ViewModel responsible for managing and persisting ThemeMode state.
 */
class ThemeViewModel(
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

    companion object {
        /**
         * Factory helper to instantiate ThemeViewModel using manual dependency injection.
         */
        fun provideFactory(context: Context): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    val dataStore = ThemeDataStore(context.applicationContext)
                    val repository = ThemeRepositoryImpl(dataStore)
                    val getUseCase = GetThemeModeUseCase(repository)
                    val setUseCase = SetThemeModeUseCase(repository)
                    val toggleUseCase = ToggleThemeModeUseCase(repository)
                    return ThemeViewModel(getUseCase, setUseCase, toggleUseCase) as T
                }
            }
    }
}
