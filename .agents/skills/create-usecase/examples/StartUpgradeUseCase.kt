package com.devrachit.clan.domain.usecase.builder

import com.devrachit.clan.domain.usecase.core.BaseSuspendUseCase

/**
 * Example 3: Asynchronous Suspend UseCase with Parameters.
 * Starts a building upgrade on a specific builder queue.
 */
interface BuilderRepositoryMock {
    suspend fun startUpgrade(buildingId: String, durationSeconds: Long): Boolean
}

data class StartUpgradeParams(
    val buildingId: String,
    val durationSeconds: Long
)

class StartUpgradeUseCase(
    private val builderRepository: BuilderRepositoryMock
) : BaseSuspendUseCase<StartUpgradeParams, Boolean> {

    override suspend operator fun invoke(params: StartUpgradeParams): Boolean {
        return builderRepository.startUpgrade(params.buildingId, params.durationSeconds)
    }
}
