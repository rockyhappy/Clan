package com.devrachit.clan.common.utils

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavKey
import com.devrachit.clan.presentation.navigation.LocalNavHost
import com.devrachit.clan.presentation.navigation.NavController

fun NavKey.navigate(navController: NavController) {
    navController.navigate(this)
}