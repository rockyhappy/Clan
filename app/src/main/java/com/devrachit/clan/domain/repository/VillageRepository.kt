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
}
