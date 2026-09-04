package com.devrachit.clan.common.utils

import androidx.navigation3.runtime.NavKey
import com.devrachit.clan.presentation.navigation.NavController

fun NavKey.navigate(navController: NavController) {
    navController.navigate(this)
}