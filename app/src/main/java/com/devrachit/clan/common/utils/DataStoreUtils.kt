package com.devrachit.clan.common.utils

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

/**
 * Generic DataStore utility functions that eliminate boilerplate
 * for reading and writing Preferences DataStore values.
 *
 * Every DataStore in the project follows the same pattern:
 *   Read  → catch IOException → emit emptyPreferences → map key → runCatching → default
 *   Write → edit { preferences[key] = serialized }
 *
 * These extension functions encapsulate that pattern once with
 * type-safe generics so individual DataStore classes stay minimal.
 */
object DataStoreUtils {

    /**
     * Observes a [Preferences.Key] as a [Flow], automatically handling
     * [IOException] recovery (emits [defaultValue]) and mapping exceptions
     * within the preferences lookup (falls back to [defaultValue]).
     *
     * Usage (simple types stored directly):
     * ```
     * val tokenFlow: Flow<String> = dataStore.safeValueFlow(
     *     key = KEY_AUTH_TOKEN,
     *     defaultValue = ""
     * )
     * ```
     *
     * @param T The raw type stored in Preferences (String, Int, Boolean, etc.)
     * @param key The [Preferences.Key] to observe.
     * @param defaultValue Returned when the key is absent or an error occurs.
     */
    fun <T> DataStore<Preferences>.safeValueFlow(
        key: Preferences.Key<T>,
        defaultValue: T
    ): Flow<T> = data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            runCatching {
                preferences[key] ?: defaultValue
            }.getOrDefault(defaultValue)
        }

    /**
     * Observes a [Preferences.Key] as a [Flow] and transforms the raw
     * stored value into a domain type [R] via [mapper]. Handles
     * [IOException] recovery and mapping failures by emitting [defaultValue].
     *
     * Usage (enum stored as String):
     * ```
     * val themeModeFlow: Flow<ThemeMode> = dataStore.safeMappedFlow(
     *     key = KEY_THEME_MODE,
     *     defaultValue = ThemeMode.SYSTEM,
     *     mapper = { ThemeMode.valueOf(it) }
     * )
     * ```
     *
     * @param T The raw type stored in Preferences (e.g. String).
     * @param R The domain type to map into (e.g. ThemeMode enum).
     * @param key The [Preferences.Key] to observe.
     * @param defaultValue Returned when the key is absent, mapping fails, or an error occurs.
     * @param mapper Transforms the raw stored [T] value into [R].
     */
    fun <T, R> DataStore<Preferences>.safeMappedFlow(
        key: Preferences.Key<T>,
        defaultValue: R,
        mapper: (T) -> R
    ): Flow<R> = data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            runCatching {
                val raw = preferences[key]
                if (raw != null) mapper(raw) else defaultValue
            }.getOrDefault(defaultValue)
        }

    /**
     * Persists a raw [value] under the given [key].
     *
     * Usage:
     * ```
     * suspend fun saveToken(token: String) {
     *     dataStore.safeEdit(KEY_AUTH_TOKEN, token)
     * }
     * ```
     *
     * @param T The type of the value (String, Int, Boolean, etc.)
     * @param key The [Preferences.Key] to write.
     * @param value The value to persist.
     */
    suspend fun <T> DataStore<Preferences>.safeEdit(
        key: Preferences.Key<T>,
        value: T
    ) {
        edit { preferences ->
            preferences[key] = value
        }
    }

    /**
     * Persists a domain value by first transforming it via [serializer]
     * into the raw type [T] stored in Preferences.
     *
     * Usage (enum → String):
     * ```
     * suspend fun saveThemeMode(mode: ThemeMode) {
     *     dataStore.safeMappedEdit(KEY_THEME_MODE, mode) { it.name }
     * }
     * ```
     *
     * @param T The raw type stored in Preferences (e.g. String).
     * @param R The domain type being saved (e.g. ThemeMode).
     * @param key The [Preferences.Key] to write.
     * @param value The domain value to persist.
     * @param serializer Converts the domain value [R] into the raw type [T].
     */
    suspend fun <T, R> DataStore<Preferences>.safeMappedEdit(
        key: Preferences.Key<T>,
        value: R,
        serializer: (R) -> T
    ) {
        edit { preferences ->
            preferences[key] = serializer(value)
        }
    }
}