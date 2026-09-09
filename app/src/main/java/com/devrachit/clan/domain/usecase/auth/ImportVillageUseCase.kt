package com.devrachit.clan.domain.usecase.auth

import com.devrachit.clan.domain.repository.AuthRepository
import com.devrachit.clan.domain.repository.VillageRepository
import com.devrachit.clan.domain.usecase.core.BaseSuspendUseCase
import com.devrachit.clan.common.constants.AppStrings
import org.json.JSONException
import org.json.JSONObject

/**
 * Parses the provided village JSON, extracts the player tag,
 * and if valid, saves both the tag and the raw JSON.
 */
class ImportVillageUseCase(
    private val authRepository: AuthRepository,
    private val villageRepository: VillageRepository
) : BaseSuspendUseCase<String, Boolean> {

    override suspend fun invoke(params: String): Boolean {
        if (params.isBlank()) return false
        
        return try {
            val jsonObject = JSONObject(params)
            val tag = jsonObject.optString(AppStrings.Auth.JSON_TAG_KEY)
            if (tag.isNotEmpty()) {
                // Save the tag as the auth token
                authRepository.setAuthToken(tag)
                // Save the raw village data
                villageRepository.saveVillageJson(params)
                true
            } else {
                false
            }
        } catch (e: JSONException) {
            false
        }
    }
}
