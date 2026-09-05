package com.devrachit.clan.data.local.datastore

import android.content.Context
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.devrachit.clan.common.constants.AppStrings.Auth.EMPTY_STRING
import com.devrachit.clan.common.utils.DataStoreUtils.safeEdit
import com.devrachit.clan.common.utils.DataStoreUtils.safeValueFlow
import kotlinx.coroutines.flow.Flow

/**
 * DataStore local storage implementation for persisting auth credentials.
 *
 * Uses [DataStoreUtils.safeValueFlow] and [DataStoreUtils.safeEdit]
 * to eliminate boilerplate IOException handling.
 */
class AuthDataStore(private val context: Context) {

    companion object {
        private val Context.authDataStore by preferencesDataStore(com.devrachit.clan.common.constants.AppStrings.Storage.AUTH_PREFS)
        private val KEY_AUTH_TOKEN = stringPreferencesKey(com.devrachit.clan.common.constants.AppStrings.Storage.KEY_AUTH_TOKEN)
    }

    val authTagStore: Flow<String> = context.authDataStore.safeValueFlow(
        key = KEY_AUTH_TOKEN,
        defaultValue = EMPTY_STRING
    )

    suspend fun saveAuthTag(mode: String) {
        context.authDataStore.safeEdit(
            key = KEY_AUTH_TOKEN,
            value = mode
        )
    }
}
