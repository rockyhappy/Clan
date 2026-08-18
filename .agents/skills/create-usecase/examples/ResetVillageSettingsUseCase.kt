package com.devrachit.clan.domain.usecase.village

import com.devrachit.clan.domain.usecase.core.BaseNoParamsSuspendUseCase

/**
 * Example 4: Asynchronous Suspend UseCase with No Parameters.
 * Resets all village tracking caches and local preferences.
 */
interface VillageSettingsRepositoryMock {
    suspend fun resetSettings()
}

class ResetVillageSettingsUseCase(
    private val repository: VillageSettingsRepositoryMock
) : BaseNoParamsSuspendUseCase<Unit> {

    override suspend operator fun invoke() {
        repository.resetSettings()
    }
}
