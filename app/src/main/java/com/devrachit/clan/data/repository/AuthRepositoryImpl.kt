package com.devrachit.clan.data.repository

import com.devrachit.clan.data.local.datastore.AuthDataStore
import com.devrachit.clan.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow

/**
 * Implementation of [AuthRepository] that delegates to [AuthDataStore]
 * for persisting and observing the authentication token.
 */
class AuthRepositoryImpl(
    private val authDataStore: AuthDataStore
) : AuthRepository {

    override val authToken: Flow<String> = authDataStore.authTagStore

    override suspend fun setAuthToken(token: String) {
        authDataStore.saveAuthTag(token)
    }
}
