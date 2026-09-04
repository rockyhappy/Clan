package com.devrachit.clan.data.repository

import com.devrachit.clan.data.local.datastore.AuthDataStore
import com.devrachit.clan.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow

import com.devrachit.clan.di.qualifiers.AuthStore
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Implementation of [AuthRepository] that delegates to [AuthDataStore]
 * for persisting and observing the authentication token.
 */
@Singleton
class AuthRepositoryImpl @Inject constructor(
    @AuthStore private val authDataStore: AuthDataStore
) : AuthRepository {

    override val authToken: Flow<String> = authDataStore.authTagStore

    override suspend fun setAuthToken(token: String) {
        authDataStore.saveAuthTag(token)
    }
}
