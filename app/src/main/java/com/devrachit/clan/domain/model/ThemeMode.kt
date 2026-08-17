package com.devrachit.clan.domain.model

/**
 * Domain entity representing the user's preferred theme mode.
 */
enum class ThemeMode {
    SYSTEM,
    LIGHT,
    DARK;

    /**
     * Resolves the effective dark mode boolean based on current system state.
     */
    fun isDark(isSystemInDarkTheme: Boolean): Boolean = when (this) {
        SYSTEM -> isSystemInDarkTheme
        LIGHT -> false
        DARK -> true
    }
}
