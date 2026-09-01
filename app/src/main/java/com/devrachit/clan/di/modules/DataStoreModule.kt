package com.devrachit.clan.di.modules

import android.content.Context
import com.devrachit.clan.data.local.datastore.AuthDataStore
import com.devrachit.clan.data.local.datastore.ThemeDataStore
import com.devrachit.clan.di.qualifiers.AuthStore
import com.devrachit.clan.di.qualifiers.ThemeStore
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
 * across the entire application lifecycle.
 */
@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    @ThemeStore
    fun provideThemeDataStore(
        @ApplicationContext context: Context
    ): ThemeDataStore = ThemeDataStore(context)

    @Provides
    @Singleton
    @AuthStore
    fun provideAuthDataStore(
        @ApplicationContext context: Context
    ): AuthDataStore = AuthDataStore(context)
}
