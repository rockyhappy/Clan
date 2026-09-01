package com.devrachit.clan.domain.repository

import kotlinx.coroutines.flow.Flow

/**
 * Domain Repository Interface for Authentication Preferences.
 *
 * Adheres strictly to Clean Architecture by having zero Android framework dependencies.
 */
interface AuthRepository {

    /**
     * Observable stream of the currently stored authentication token.
     * Emits an empty string when no token is stored.
     */
    val authToken: Flow<String>

    /**
     * Persists the given authentication token string.
     */
    suspend fun setAuthToken(token: String)
}
