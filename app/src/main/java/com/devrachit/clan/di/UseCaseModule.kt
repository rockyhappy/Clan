package com.devrachit.clan.di

import com.devrachit.clan.domain.repository.AuthRepository
import com.devrachit.clan.domain.repository.ThemeRepository
import com.devrachit.clan.domain.usecase.auth.AuthenticateUserUseCase
import com.devrachit.clan.domain.usecase.auth.CheckAuthStatusUseCase
import com.devrachit.clan.domain.usecase.auth.GetAuthTokenUseCase
import com.devrachit.clan.domain.usecase.theme.GetThemeModeUseCase
import com.devrachit.clan.domain.usecase.theme.SetThemeModeUseCase
import com.devrachit.clan.domain.usecase.theme.ToggleThemeModeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Hilt module providing domain UseCase instances.
 *
 * UseCases are intentionally NOT scoped — a new instance is created per injection
 * point. They are lightweight wrappers with no mutable state, so sharing a
 * singleton offers no benefit and would complicate testing.
 */
@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    // ── Theme UseCases ──

    @Provides
    fun provideGetThemeModeUseCase(
        themeRepository: ThemeRepository
    ): GetThemeModeUseCase = GetThemeModeUseCase(themeRepository)

    @Provides
    fun provideSetThemeModeUseCase(
        themeRepository: ThemeRepository
    ): SetThemeModeUseCase = SetThemeModeUseCase(themeRepository)

    @Provides
    fun provideToggleThemeModeUseCase(
        themeRepository: ThemeRepository
    ): ToggleThemeModeUseCase = ToggleThemeModeUseCase(themeRepository)

    // ── Auth UseCases ──

    @Provides
    fun provideCheckAuthStatusUseCase(
        authRepository: AuthRepository
    ): CheckAuthStatusUseCase = CheckAuthStatusUseCase(authRepository)

    @Provides
    fun provideAuthenticateUserUseCase(
        authRepository: AuthRepository
    ): AuthenticateUserUseCase = AuthenticateUserUseCase(authRepository)

    @Provides
    fun provideGetAuthTokenUseCase(
        authRepository: AuthRepository
    ): GetAuthTokenUseCase = GetAuthTokenUseCase(authRepository)
}
