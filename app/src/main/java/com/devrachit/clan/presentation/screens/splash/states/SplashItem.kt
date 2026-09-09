package com.devrachit.clan.presentation.screens.splash.states

import androidx.annotation.DrawableRes

data class SplashItem(
    @DrawableRes val imageRes: Int,
    val title: String,
    val description: String
)