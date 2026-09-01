package com.devrachit.clan.domain.usecase.auth

import com.devrachit.clan.domain.repository.AuthRepository
import com.devrachit.clan.domain.usecase.core.BaseSuspendUseCase

/**
 * Authenticates the user by persisting the provided token string
 * into the auth DataStore.
 *
 * Pass the authentication token (e.g. player tag, API key, or session ID)
 * as the input parameter. A non-empty stored token is considered "authenticated".
 */
class AuthenticateUserUseCase(
    private val authRepository: AuthRepository
) : BaseSuspendUseCase<String, Unit> {

    override suspend operator fun invoke(params: String) {
        authRepository.setAuthToken(params)
    }
}
