package com.devrachit.clan.domain.usecase.theme

import com.devrachit.clan.domain.model.ThemeMode
import com.devrachit.clan.domain.repository.ThemeRepository
import kotlinx.coroutines.flow.first

/**
 * UseCase to toggle theme mode between Light and Dark.
 */
class ToggleThemeModeUseCase(
    private val themeRepository: ThemeRepository
) {
    suspend operator fun invoke(isSystemDark: Boolean) {
        val currentMode = themeRepository.themeMode.first()
        val currentIsDark = currentMode.isDark(isSystemDark)
        val newMode = if (currentIsDark) ThemeMode.LIGHT else ThemeMode.DARK
        themeRepository.setThemeMode(newMode)
    }
}
