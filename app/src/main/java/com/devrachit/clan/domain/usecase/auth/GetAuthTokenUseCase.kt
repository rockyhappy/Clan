package com.devrachit.clan.domain.usecase.auth

import com.devrachit.clan.domain.repository.AuthRepository
import com.devrachit.clan.domain.usecase.core.BaseNoParamsFlowUseCase
import kotlinx.coroutines.flow.Flow
/**
 * Retrieves the raw auth token string.
 */
class GetAuthTokenUseCase(
    private val authRepository: AuthRepository
) : BaseNoParamsFlowUseCase<String> {

    override operator fun invoke(): Flow<String> = authRepository.authToken
}
