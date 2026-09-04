package com.devrachit.clan.di.qualifiers

import javax.inject.Qualifier

/**
 * Qualifier for the Theme DataStore.
 */
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ThemeStore

/**
 * Qualifier for the Auth DataStore.
 */
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthStore
