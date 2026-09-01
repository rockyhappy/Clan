package com.devrachit.clan.domain.usecase.auth

import com.devrachit.clan.domain.repository.AuthRepository
import com.devrachit.clan.domain.usecase.core.BaseSuspendUseCase
import javax.inject.Inject

/**
 * Authenticates the user by persisting their auth token.
 */
class AuthenticateUserUseCase(
    private val authRepository: AuthRepository
) : BaseSuspendUseCase<String, Unit> {

    override suspend operator fun invoke(params: String) {
        authRepository.setAuthToken(params)
    }
}
