package com.devrachit.clan.domain.usecase.theme

import com.devrachit.clan.domain.model.ThemeMode
import com.devrachit.clan.domain.repository.ThemeRepository
import com.devrachit.clan.domain.usecase.core.BaseSuspendUseCase
/**
 * UseCase to save the user's selected ThemeMode to storage.
 */
class SetThemeModeUseCase(
    private val themeRepository: ThemeRepository
) : BaseSuspendUseCase<ThemeMode, Unit> {

    override suspend operator fun invoke(params: ThemeMode) {
        themeRepository.setThemeMode(params)
    }
}
