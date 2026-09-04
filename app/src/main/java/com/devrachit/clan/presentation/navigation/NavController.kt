package com.devrachit.clan.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack

/**
 * CompositionLocal key for the app-wide [NavController].
 *
 * Provided near the root of the composition (typically inside
 * `CompositionLocalProvider(LocalNavHost provides navController)`)
 * and consumed anywhere via `LocalNavHost.current`.
 *
 * Uses [staticCompositionLocalOf] because the NavController instance
 * is created once and never replaced during the lifetime of the
 * composition — only its internal back-stack mutates.
 */
val LocalNavHost = staticCompositionLocalOf<NavController> {
    error("NavController not provided. Wrap your composable tree with CompositionLocalProvider(LocalNavHost provides navController).")
}

/**
 * A modern, state-driven implementation of a NavController for Jetpack Navigation 3.
 *
 * This provides the familiar and powerful API of the traditional NavController 2.x,
 * while safely manipulating the raw SnapshotStateList (`NavBackStack`) required by Nav3.
 */
class NavController(
    val backStack: NavBackStack<NavKey>
) {
    /**
     * Gets the current top-most route of the back stack.
     */
    val currentRoute: NavKey?
        get() = backStack.lastOrNull()

    /**
     * Gets the previous route in the back stack.
     */
    val previousRoute: NavKey?
        get() = if (backStack.size > 1) backStack[backStack.lastIndex - 1] else null

    /**
     * Navigates to the specified route.
     */
    fun navigate(route: NavKey) {
        backStack.add(route)
    }

    /**
     * Navigates to a route while popping the back stack up to a specific destination.
     * Mimics `popUpTo` behavior from older navigation graphs.
     */
    fun navigate(route: NavKey, popUpTo: NavKey, inclusive: Boolean = false) {
        popBackStack(popUpTo, inclusive)
        backStack.add(route)
    }

    /**
     * Replaces the current top screen with a new one.
     * Useful for single-direction flows like Splash -> Auth.
     */
    fun replace(route: NavKey) {
        if (backStack.isNotEmpty()) {
            backStack.set(backStack.lastIndex, route)
        } else {
            backStack.add(route)
        }
    }

    /**
     * Attempts to pop the controller's back stack.
     * @return true if popped successfully, false if the stack is at the root (meaning the app should exit).
     */
    fun popBackStack(): Boolean {
        if (backStack.size > 1) {
            backStack.removeAt(backStack.lastIndex)
            return true
        }
        return false
    }

    /**
     * Navigates up in the application's navigation hierarchy.
     * In this implementation, it behaves identically to [popBackStack].
     */
    fun navigateUp(): Boolean = popBackStack()

    /**
     * Pops destinations off the back stack until the specified [route] is found.
     * @param route The destination to pop back to.
     * @param inclusive If true, the specified [route] itself is also popped.
     * @return true if the route was found and successfully popped to.
     */
    fun popBackStack(route: NavKey, inclusive: Boolean = false): Boolean {
        val index = backStack.indexOf(route)
        if (index != -1) {
            val targetSize = if (inclusive) index else index + 1
            while (backStack.size > targetSize) {
                backStack.removeAt(backStack.lastIndex)
            }
            return true
        }
        return false
    }

    /**
     * Clears the entire back stack and sets a new root destination.
     * Useful for logout flows.
     */
    fun clearBackStack(route: NavKey) {
        backStack.clear()
        backStack.add(route)
    }
}

