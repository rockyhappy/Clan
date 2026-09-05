package com.devrachit.clan.presentation.screens.splash.states

import androidx.compose.ui.graphics.Color

/**
 * Represents a single page in the onboarding horizontal pager.
 */
internal data class SplashPage(
    val title: String,
    val subtitle: String,
    val description: String,
    val badgeColor: Color,
    val iconUrl: String
)