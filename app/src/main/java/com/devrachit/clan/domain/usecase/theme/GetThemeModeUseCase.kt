package com.devrachit.clan.domain.usecase.theme

import com.devrachit.clan.domain.model.ThemeMode
import com.devrachit.clan.domain.repository.ThemeRepository
import com.devrachit.clan.domain.usecase.core.BaseNoParamsFlowUseCase
import kotlinx.coroutines.flow.Flow

/**
 * UseCase to observe the user's selected ThemeMode from storage.
 */
class GetThemeModeUseCase(
    private val themeRepository: ThemeRepository
) : BaseNoParamsFlowUseCase<ThemeMode> {

    override operator fun invoke(): Flow<ThemeMode> = themeRepository.themeMode
}
