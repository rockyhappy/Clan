package com.devrachit.clan.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack

/**
 * Initializes and remembers a [NavController] instance across recompositions
 * and configuration changes (device rotations).
 */
@Composable
fun rememberNavHost(initialRoute: NavKey): NavController {
    val backStack = rememberNavBackStack(initialRoute)
    return remember(backStack) {
        NavController(backStack)
    }
}

@Composable
fun NewNavHost(initialRoute: NavKey){
    val backStack = rememberNavBackStack(initialRoute)
    NavController(backStack)
}
