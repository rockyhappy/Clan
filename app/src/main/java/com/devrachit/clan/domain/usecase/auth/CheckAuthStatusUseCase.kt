package com.devrachit.clan.domain.usecase.auth

import com.devrachit.clan.domain.repository.AuthRepository
import com.devrachit.clan.domain.usecase.core.BaseNoParamsFlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Observes the authentication state as a continuous [Flow] of [Boolean].
 *
 * Returns `true` when a non-empty auth token is stored in the DataStore,
 * `false` otherwise. The flow automatically re-emits whenever the token changes.
 */
class CheckAuthStatusUseCase(
    private val authRepository: AuthRepository
) : BaseNoParamsFlowUseCase<Boolean> {

    override operator fun invoke(): Flow<Boolean> =
        authRepository.authToken.map { token -> token.isNotEmpty() }
}
