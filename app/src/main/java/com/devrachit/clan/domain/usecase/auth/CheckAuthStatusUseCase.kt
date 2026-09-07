package com.devrachit.clan.domain.usecase.auth

import com.devrachit.clan.domain.repository.AuthRepository
import com.devrachit.clan.domain.repository.VillageRepository
import com.devrachit.clan.domain.usecase.core.BaseNoParamsFlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
/**
 * Observes the current authentication status.
 * Returns true if the user is authenticated (auth token exists) AND village data is present.
 */
class CheckAuthStatusUseCase(
    private val authRepository: AuthRepository,
    private val villageRepository: VillageRepository
) : BaseNoParamsFlowUseCase<Boolean> {

    override operator fun invoke(): Flow<Boolean> =
        authRepository.authToken.map { token -> 
            token.isNotEmpty() && villageRepository.hasVillageJson() 
        }
}
