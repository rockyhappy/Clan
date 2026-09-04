package com.devrachit.clan.data.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.devrachit.clan.common.utils.DataStoreUtils.safeMappedEdit
import com.devrachit.clan.common.utils.DataStoreUtils.safeMappedFlow
import com.devrachit.clan.domain.model.ThemeMode
import kotlinx.coroutines.flow.Flow


/**
 * DataStore local storage implementation for persisting theme preferences.
 *
 * Uses [DataStoreUtils.safeMappedFlow] and [DataStoreUtils.safeMappedEdit]
 * to eliminate boilerplate IOException handling and enum serialization.
 */
class ThemeDataStore(private val context: Context) {

    companion object {
        private val Context.themeDataStore: DataStore<Preferences> by preferencesDataStore(name = "clan_theme_prefs")
        private val KEY_THEME_MODE = stringPreferencesKey("key_theme_mode")
    }

    val themeModeFlow: Flow<ThemeMode> = context.themeDataStore.safeMappedFlow(
        key = KEY_THEME_MODE,
        defaultValue = ThemeMode.SYSTEM,
        mapper = { ThemeMode.valueOf(it) }
    )

    suspend fun saveThemeMode(mode: ThemeMode) {
        context.themeDataStore.safeMappedEdit(
            key = KEY_THEME_MODE,
            value = mode,
            serializer = { it.name }
        )
    }
}
