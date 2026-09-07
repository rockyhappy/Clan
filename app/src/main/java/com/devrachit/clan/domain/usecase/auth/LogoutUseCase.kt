package com.devrachit.clan.domain.usecase.auth

import com.devrachit.clan.common.constants.AppStrings
import com.devrachit.clan.domain.repository.AuthRepository
import com.devrachit.clan.domain.repository.VillageRepository
import com.devrachit.clan.domain.usecase.core.BaseNoParamsSuspendUseCase

/**
 * Logs out the user by clearing the auth token and deleting the village JSON data.
 */
class LogoutUseCase(
    private val authRepository: AuthRepository,
    private val villageRepository: VillageRepository
) : BaseNoParamsSuspendUseCase<Unit> {

    override suspend fun invoke() {
        authRepository.setAuthToken(AppStrings.Auth.EMPTY_STRING)
        villageRepository.clearVillageJson()
    }
}
