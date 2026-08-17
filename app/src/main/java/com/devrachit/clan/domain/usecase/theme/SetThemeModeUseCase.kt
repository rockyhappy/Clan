package com.devrachit.clan.domain.usecase.theme

import com.devrachit.clan.domain.model.ThemeMode
import com.devrachit.clan.domain.repository.ThemeRepository

/**
 * UseCase to save the user's selected ThemeMode into storage.
 */
class SetThemeModeUseCase(
    private val themeRepository: ThemeRepository
) {
    suspend operator fun invoke(mode: ThemeMode) {
        themeRepository.setThemeMode(mode)
    }
}
