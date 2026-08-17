package com.devrachit.clan.presentation.splash

import androidx.annotation.DrawableRes

data class SplashItem(
    @DrawableRes val imageRes: Int,
    val title: String,
    val description: String
)
