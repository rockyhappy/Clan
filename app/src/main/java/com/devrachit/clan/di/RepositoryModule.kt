package com.devrachit.clan.di

import com.devrachit.clan.data.local.datastore.AuthDataStore
import com.devrachit.clan.data.local.datastore.ThemeDataStore
import com.devrachit.clan.data.repository.AuthRepositoryImpl
import com.devrachit.clan.data.repository.ThemeRepositoryImpl
import com.devrachit.clan.domain.repository.AuthRepository
import com.devrachit.clan.domain.repository.ThemeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module binding repository interfaces to their data-layer implementations.
 *
 * Installed in [SingletonComponent] so repositories are application-scoped singletons.
 */
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideThemeRepository(
        themeDataStore: ThemeDataStore
    ): ThemeRepository = ThemeRepositoryImpl(themeDataStore)

    @Provides
    @Singleton
    fun provideAuthRepository(
        authDataStore: AuthDataStore
    ): AuthRepository = AuthRepositoryImpl(authDataStore)
}
