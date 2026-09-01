package com.devrachit.clan.di

import android.content.Context
import com.devrachit.clan.data.local.datastore.AuthDataStore
import com.devrachit.clan.data.local.datastore.ThemeDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module providing DataStore singletons.
 *
 * Installed in [SingletonComponent] so that DataStore instances are shared
 * across the entire application lifecycle — critical because Preferences
 * DataStore must NOT have multiple instances per file.
 */
@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun provideThemeDataStore(
        @ApplicationContext context: Context
    ): ThemeDataStore = ThemeDataStore(context)

    @Provides
    @Singleton
    fun provideAuthDataStore(
        @ApplicationContext context: Context
    ): AuthDataStore = AuthDataStore(context)
}
