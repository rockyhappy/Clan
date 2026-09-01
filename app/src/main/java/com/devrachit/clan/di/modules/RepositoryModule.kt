package com.devrachit.clan.di.modules

import com.devrachit.clan.data.local.datastore.AuthDataStore
import com.devrachit.clan.data.local.datastore.ThemeDataStore
import com.devrachit.clan.data.repository.AuthRepositoryImpl
import com.devrachit.clan.data.repository.ThemeRepositoryImpl
import com.devrachit.clan.domain.repository.AuthRepository
import com.devrachit.clan.domain.repository.ThemeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module binding repository interfaces to their data-layer implementations.
 *
 * Uses `@Binds` for efficient interface binding.
 * Installed in [SingletonComponent] so repositories are application-scoped singletons.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindThemeRepository(
        themeRepositoryImpl: ThemeRepositoryImpl
    ): ThemeRepository

    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository
}