package com.devrachit.clan.domain.repository

/**
 * Domain Repository Interface for Village Data.
 */
interface VillageRepository {

    /**
     * Persists the raw village JSON string.
     */
    suspend fun saveVillageJson(json: String)

    /**
     * Retrieves the raw village JSON string.
     * Returns null if no village data is found.
     */
    suspend fun getVillageJson(): String?

    /**
     * Clears the saved village JSON data.
     */
    suspend fun clearVillageJson()

    /**
     * Checks if the village JSON data is present.
     */
    suspend fun hasVillageJson(): Boolean
}
