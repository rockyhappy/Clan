package com.devrachit.clan.presentation.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

/**
 * Clan Theme Root Composable
 *
 * Provides a unified design system throughout the entire application:
 * - Material 3 theme integration with Light & Dark Clash of Clans color schemes
 * - 5 Clash of Clans-inspired Google Fonts (Lilita One, Luckiest Guy, Fredoka, Nunito, MedievalSharp)
 * - Game design tokens (Resources, Surfaces, Gradients, Status badges, Spacings, Borders, Elevations)
 * - Edge-to-edge status bar & navigation bar formatting
 *
 * Usage:
 * ```kotlin
 * ClanTheme {
 *     // Standard M3 components automatically adopt the palette
 *     Text(
 *         text = "Town Hall 16",
 *         style = MaterialTheme.typography.headlineMedium,
 *         color = ClanTheme.colors.primary
 *     )
 *
 *     // Access game design tokens seamlessly
 *     Box(
 *         modifier = Modifier
 *             .background(ClanTheme.gradients.goldButton, shape = ClanTheme.gameShapes.gameButton)
 *             .padding(ClanTheme.spacing.medium)
 *     ) {
 *         Text(
 *             text = "Upgrade for 12,000,000",
 *             fontFamily = ClanTheme.fonts.gameDisplay,
 *             color = ClanTheme.resources.gold
 *         )
 *     }
 * }
 * ```
 */
/**
 * CompositionLocal key tracking whether the active theme is dark (Night Base)
 * or light (Day Village).
 *
 * Provided automatically by [ClanTheme] — consumed via `ClanTheme.isDarkTheme`.
 */
val LocalDarkTheme = staticCompositionLocalOf { false }

@Composable
fun ClanTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) ClanDarkColorScheme else ClanLightColorScheme
    val resourceColors = if (darkTheme) DarkResourceColors else LightResourceColors
    val surfaceColors = if (darkTheme) DarkSurfaceColors else LightSurfaceColors
    val gradients = if (darkTheme) DarkGradients else LightGradients
    val statusColors = if (darkTheme) DarkStatusColors else LightStatusColors
    val fontFamilies = ClanFontFamilies()
    val gameShapes = ClanGameShapes()
    val spacing = ClanSpacing()
    val borders = ClanBorders()
    val elevations = ClanElevations()

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            window.navigationBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).apply {
                isAppearanceLightStatusBars = !darkTheme
                isAppearanceLightNavigationBars = !darkTheme
            }
        }
    }

    CompositionLocalProvider(
        LocalDarkTheme provides darkTheme,
        LocalClanResourceColors provides resourceColors,
        LocalClanSurfaceColors provides surfaceColors,
        LocalClanGradients provides gradients,
        LocalClanStatusColors provides statusColors,
        LocalClanFontFamilies provides fontFamilies,
        LocalClanGameShapes provides gameShapes,
        LocalClanSpacing provides spacing,
        LocalClanBorders provides borders,
        LocalClanElevations provides elevations
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = ClanTypography,
            shapes = ClanShapes,
            content = content
        )
    }
}

/**
 * Unified `ClanTheme` Accessor
 *
 * Direct access to all design system properties without boilerplate.
 */
object ClanTheme {
    /**
     * Whether the current composition is using the dark (Night Base) theme.
     *
     * Use this anywhere instead of threading `isDarkTheme: Boolean` parameters:
     * ```kotlin
     * val label = if (ClanTheme.isDarkTheme) AppStrings.Theme.NIGHT_BASE
     *             else AppStrings.Theme.DAY_VILLAGE
     * ```
     */
    val isDarkTheme: Boolean
        @Composable
        @ReadOnlyComposable
        get() = LocalDarkTheme.current

    /**
     * Standard Material 3 color scheme tokens.
     */
    val colors: ColorScheme
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.colorScheme

    /**
     * Standard Material 3 typography definitions.
     */
    val typography: Typography
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.typography

    /**
     * Standard Material 3 shape definitions.
     */
    val shapes: Shapes
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.shapes

    /**
     * Game resources: Gold, Elixir, Dark Elixir, Gems, Raid Medals, Capital Gold, Trophies, Level.
     */
    val resources: ClanResourceColors
        @Composable
        @ReadOnlyComposable
        get() = LocalClanResourceColors.current

    /**
     * Game surfaces: Wood panels, Stone slate, Parchment backgrounds, Grass.
     */
    val surfaces: ClanSurfaceColors
        @Composable
        @ReadOnlyComposable
        get() = LocalClanSurfaceColors.current

    /**
     * Game gradients: Attack green, cancel red, gold upgrade, info blue, elixir & gems bars.
     */
    val gradients: ClanGradients
        @Composable
        @ReadOnlyComposable
        get() = LocalClanGradients.current

    /**
     * Game status colors: Upgrading, ready, boosted, war attack/defense, shield, maxed, rushed.
     */
    val status: ClanStatusColors
        @Composable
        @ReadOnlyComposable
        get() = LocalClanStatusColors.current

    /**
     * Font families: gameDisplay (Lilita One), warBanner (Luckiest Guy), heading (Fredoka), body (Nunito), fantasyLore (MedievalSharp).
     */
    val fonts: ClanFontFamilies
        @Composable
        @ReadOnlyComposable
        get() = LocalClanFontFamilies.current

    /**
     * Custom game component shapes: game buttons, resource pills, card containers, dialogs, badges.
     */
    val gameShapes: ClanGameShapes
        @Composable
        @ReadOnlyComposable
        get() = LocalClanGameShapes.current

    /**
     * Layout spacing scale (extraSmall = 4.dp to massive = 64.dp).
     */
    val spacing: ClanSpacing
        @Composable
        @ReadOnlyComposable
        get() = LocalClanSpacing.current

    /**
     * Border thickness scale (thin = 1.dp to heavyBevel = 4.dp).
     */
    val borders: ClanBorders
        @Composable
        @ReadOnlyComposable
        get() = LocalClanBorders.current

    /**
     * Elevation scale (level1 = 2.dp to level5 = 16.dp).
     */
    val elevations: ClanElevations
        @Composable
        @ReadOnlyComposable
        get() = LocalClanElevations.current
}