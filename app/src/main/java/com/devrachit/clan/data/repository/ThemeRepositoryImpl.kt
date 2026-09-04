package com.devrachit.clan.data.repository

import com.devrachit.clan.data.local.datastore.ThemeDataStore
import com.devrachit.clan.domain.model.ThemeMode
import com.devrachit.clan.domain.repository.ThemeRepository
import kotlinx.coroutines.flow.Flow

import com.devrachit.clan.di.qualifiers.ThemeStore
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Implementation of ThemeRepository that uses ThemeDataStore.
 */
@Singleton
class ThemeRepositoryImpl @Inject constructor(
    @ThemeStore private val themeDataStore: ThemeDataStore
) : ThemeRepository {

    override val themeMode: Flow<ThemeMode> = themeDataStore.themeModeFlow

    override suspend fun setThemeMode(mode: ThemeMode) {
        themeDataStore.saveThemeMode(mode)
    }
}
