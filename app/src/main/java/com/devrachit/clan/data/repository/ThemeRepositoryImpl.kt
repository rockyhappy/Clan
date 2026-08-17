package com.devrachit.clan.data.repository

import com.devrachit.clan.data.local.datastore.ThemeDataStore
import com.devrachit.clan.domain.model.ThemeMode
import com.devrachit.clan.domain.repository.ThemeRepository
import kotlinx.coroutines.flow.Flow

/**
 * Implementation of ThemeRepository that uses ThemeDataStore.
 */
class ThemeRepositoryImpl(
    private val themeDataStore: ThemeDataStore
) : ThemeRepository {

    override val themeMode: Flow<ThemeMode> = themeDataStore.themeModeFlow

    override suspend fun setThemeMode(mode: ThemeMode) {
        themeDataStore.saveThemeMode(mode)
    }
}
