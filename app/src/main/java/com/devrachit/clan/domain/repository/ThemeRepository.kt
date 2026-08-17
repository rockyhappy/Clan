package com.devrachit.clan.domain.repository

import com.devrachit.clan.domain.model.ThemeMode
import kotlinx.coroutines.flow.Flow

/**
 * Domain Repository Interface for Theme Preferences.
 *
 * Adheres strictly to Clean Architecture by having zero Android framework dependencies.
 */
interface ThemeRepository {
    /**
     * Observable stream of the current user-selected theme mode.
     */
    val themeMode: Flow<ThemeMode>

    /**
     * Persists the given theme mode.
     */
    suspend fun setThemeMode(mode: ThemeMode)
}
