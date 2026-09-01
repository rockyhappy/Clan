package com.devrachit.clan.domain.usecase.auth

import com.devrachit.clan.domain.repository.AuthRepository
import com.devrachit.clan.domain.usecase.core.BaseNoParamsFlowUseCase
import kotlinx.coroutines.flow.Flow

/**
 * Observes the raw authentication token as a continuous [Flow] of [String].
 *
 * Emits the currently stored token string, or an empty string when no token
 * is persisted. Useful for downstream logic that needs the actual token value
 * rather than just the authenticated/unauthenticated boolean.
 */
class GetAuthTokenUseCase(
    private val authRepository: AuthRepository
) : BaseNoParamsFlowUseCase<String> {

    override operator fun invoke(): Flow<String> = authRepository.authToken
}
