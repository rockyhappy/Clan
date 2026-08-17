package com.devrachit.clan.presentation.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Extended Design Tokens for the Clan Helper App.
 *
 * Provides domain-specific design values for Clash of Clans tracking:
 * - Resource colors (Gold, Elixir, Dark Elixir, Gems, Raid Medals, Capital Gold)
 * - Game Surface colors (Wood, Stone, Parchment, Grass)
 * - Gradients (Action buttons, resource progress bars, war banners)
 * - Status colors (Upgrade in progress, ready, boosted, maxed, rushed)
 * - Layout metrics (Spacing, Borders, Elevations)
 */

// ══════════════════════════════════════════════
// 1. GAME RESOURCES
// ══════════════════════════════════════════════

@Immutable
data class ClanResourceColors(
    val gold: Color,
    val goldHighlight: Color,
    val goldDark: Color,
    val elixir: Color,
    val elixirHighlight: Color,
    val elixirDark: Color,
    val darkElixir: Color,
    val darkElixirAccent: Color,
    val gems: Color,
    val gemsHighlight: Color,
    val builderGold: Color,
    val builderElixir: Color,
    val clanCapitalGold: Color,
    val raidMedals: Color,
    val leagueMedals: Color,
    val trophy: Color,
    val playerLevel: Color,
    val starBonus: Color
)

val LightResourceColors = ClanResourceColors(
    gold = ClanGold400,
    goldHighlight = ClanGold200,
    goldDark = ClanGold700,
    elixir = ClanElixir400,
    elixirHighlight = ClanElixir300,
    elixirDark = ClanElixir700,
    darkElixir = ClanDarkElixir700,
    darkElixirAccent = ClanDarkElixir300,
    gems = ClanGreen400,
    gemsHighlight = ClanGreen300,
    builderGold = ClanGold600,
    builderElixir = ClanDarkElixir400,
    clanCapitalGold = ClanGold700,
    raidMedals = Color(0xFF00ACC1),
    leagueMedals = Color(0xFFFF6F00),
    trophy = ClanGold500,
    playerLevel = Color(0xFF00A8FF),
    starBonus = ClanGold300
)

val DarkResourceColors = ClanResourceColors(
    gold = ClanGold300,
    goldHighlight = ClanGold100,
    goldDark = ClanGold600,
    elixir = ClanElixir300,
    elixirHighlight = ClanElixir200,
    elixirDark = ClanElixir600,
    darkElixir = ClanDarkElixir300,
    darkElixirAccent = ClanDarkElixir200,
    gems = ClanGreen300,
    gemsHighlight = ClanGreen200,
    builderGold = ClanGold400,
    builderElixir = ClanDarkElixir300,
    clanCapitalGold = ClanGold400,
    raidMedals = Color(0xFF26C6DA),
    leagueMedals = Color(0xFFFFB300),
    trophy = ClanGold400,
    playerLevel = Color(0xFF4FC3F7),
    starBonus = ClanGold200
)

// ══════════════════════════════════════════════
// 2. GAME SURFACES
// ══════════════════════════════════════════════

@Immutable
data class ClanSurfaceColors(
    val woodDark: Color,
    val woodMedium: Color,
    val woodLight: Color,
    val woodDeep: Color,
    val parchment: Color,
    val parchmentLight: Color,
    val stoneDark: Color,
    val stoneMedium: Color,
    val stoneBorder: Color,
    val grassGreen: Color
)

val LightSurfaceColors = ClanSurfaceColors(
    woodDark = ClanWoodDark,
    woodMedium = ClanWoodMedium,
    woodLight = ClanWoodLight,
    woodDeep = ClanWoodDeep,
    parchment = ClanParchment100,
    parchmentLight = ClanParchment50,
    stoneDark = ClanSlate700,
    stoneMedium = ClanSlate500,
    stoneBorder = ClanSlate300,
    grassGreen = Color(0xFF4E9B28)
)

val DarkSurfaceColors = ClanSurfaceColors(
    woodDark = Color(0xFF1F120A),
    woodMedium = ClanWoodDark,
    woodLight = ClanWoodMedium,
    woodDeep = Color(0xFF130A05),
    parchment = ClanSlate800,
    parchmentLight = ClanSlate700,
    stoneDark = ClanSlate950,
    stoneMedium = ClanSlate800,
    stoneBorder = ClanSlate600,
    grassGreen = Color(0xFF2E6B18)
)

// ══════════════════════════════════════════════
// 3. GAME GRADIENTS
// ══════════════════════════════════════════════

@Immutable
data class ClanGradients(
    val attackButton: Brush,
    val cancelButton: Brush,
    val goldButton: Brush,
    val infoButton: Brush,
    val elixirButton: Brush,
    val darkElixirButton: Brush,
    val goldBar: Brush,
    val elixirBar: Brush,
    val darkElixirBar: Brush,
    val gemsBar: Brush,
    val cardHeaderWood: Brush,
    val cardHeaderStone: Brush
)

val LightGradients = ClanGradients(
    attackButton = Brush.verticalGradient(listOf(Color(0xFF78E028), Color(0xFF44A611))),
    cancelButton = Brush.verticalGradient(listOf(ClanWarRed400, ClanWarRed600)),
    goldButton = Brush.verticalGradient(listOf(ClanGold300, ClanGold500)),
    infoButton = Brush.verticalGradient(listOf(ClanShieldBlue300, ClanShieldBlue500)),
    elixirButton = Brush.verticalGradient(listOf(ClanElixir300, ClanElixir500)),
    darkElixirButton = Brush.verticalGradient(listOf(ClanDarkElixir400, ClanDarkElixir600)),
    goldBar = Brush.horizontalGradient(listOf(ClanGold200, ClanGold400, ClanGold700)),
    elixirBar = Brush.horizontalGradient(listOf(ClanElixir300, ClanElixir400, ClanElixir700)),
    darkElixirBar = Brush.horizontalGradient(listOf(ClanDarkElixir300, ClanDarkElixir500, ClanDarkElixir800)),
    gemsBar = Brush.horizontalGradient(listOf(ClanGreen300, ClanGreen400, ClanGreen700)),
    cardHeaderWood = Brush.verticalGradient(listOf(ClanWoodMedium, ClanWoodDark)),
    cardHeaderStone = Brush.verticalGradient(listOf(ClanSlate500, ClanSlate700))
)

val DarkGradients = ClanGradients(
    attackButton = Brush.verticalGradient(listOf(Color(0xFF69DF20), Color(0xFF3B930E))),
    cancelButton = Brush.verticalGradient(listOf(ClanWarRed300, ClanWarRed500)),
    goldButton = Brush.verticalGradient(listOf(ClanGold200, ClanGold400)),
    infoButton = Brush.verticalGradient(listOf(ClanShieldBlue300, ClanShieldBlue500)),
    elixirButton = Brush.verticalGradient(listOf(ClanElixir200, ClanElixir400)),
    darkElixirButton = Brush.verticalGradient(listOf(ClanDarkElixir300, ClanDarkElixir500)),
    goldBar = Brush.horizontalGradient(listOf(ClanGold100, ClanGold300, ClanGold600)),
    elixirBar = Brush.horizontalGradient(listOf(ClanElixir200, ClanElixir300, ClanElixir600)),
    darkElixirBar = Brush.horizontalGradient(listOf(ClanDarkElixir200, ClanDarkElixir400, ClanDarkElixir700)),
    gemsBar = Brush.horizontalGradient(listOf(ClanGreen200, ClanGreen300, ClanGreen600)),
    cardHeaderWood = Brush.verticalGradient(listOf(ClanWoodDark, Color(0xFF1C1008))),
    cardHeaderStone = Brush.verticalGradient(listOf(ClanSlate700, ClanSlate900))
)

// ══════════════════════════════════════════════
// 4. STATUS & TRACKER STATES
// ══════════════════════════════════════════════

@Immutable
data class ClanStatusColors(
    val upgrading: Color,
    val ready: Color,
    val boosted: Color,
    val warAttack: Color,
    val warDefense: Color,
    val shield: Color,
    val warning: Color,
    val maxed: Color,
    val rushed: Color
)

val LightStatusColors = ClanStatusColors(
    upgrading = ClanShieldBlue500,
    ready = ClanGreen600,
    boosted = Color(0xFFFF9100),
    warAttack = ClanWarRed600,
    warDefense = ClanShieldBlue600,
    shield = ClanShieldBlue400,
    warning = Color(0xFFFF8F00),
    maxed = ClanGold500,
    rushed = ClanWarRed500
)

val DarkStatusColors = ClanStatusColors(
    upgrading = ClanShieldBlue300,
    ready = ClanGreen400,
    boosted = Color(0xFFFFB74D),
    warAttack = ClanWarRed400,
    warDefense = ClanShieldBlue300,
    shield = ClanShieldBlue300,
    warning = Color(0xFFFFB74D),
    maxed = ClanGold300,
    rushed = ClanWarRed400
)

// ══════════════════════════════════════════════
// 5. SPACING, BORDERS & ELEVATIONS
// ══════════════════════════════════════════════

@Immutable
data class ClanSpacing(
    val none: Dp = 0.dp,
    val extraSmall: Dp = 4.dp,
    val small: Dp = 8.dp,
    val mediumSmall: Dp = 12.dp,
    val medium: Dp = 16.dp,
    val mediumLarge: Dp = 20.dp,
    val large: Dp = 24.dp,
    val extraLarge: Dp = 32.dp,
    val huge: Dp = 48.dp,
    val massive: Dp = 64.dp
)

@Immutable
data class ClanBorders(
    val thin: Dp = 1.dp,
    val regular: Dp = 1.5.dp,
    val thick: Dp = 2.dp,
    val gameBevel: Dp = 3.dp,
    val heavyBevel: Dp = 4.dp
)

@Immutable
data class ClanElevations(
    val none: Dp = 0.dp,
    val level1: Dp = 2.dp,
    val level2: Dp = 4.dp,
    val level3: Dp = 8.dp,
    val level4: Dp = 12.dp,
    val level5: Dp = 16.dp
)

// ══════════════════════════════════════════════
// 6. COMPOSITION LOCALS
// ══════════════════════════════════════════════

val LocalClanResourceColors = staticCompositionLocalOf { LightResourceColors }
val LocalClanSurfaceColors = staticCompositionLocalOf { LightSurfaceColors }
val LocalClanGradients = staticCompositionLocalOf { LightGradients }
val LocalClanStatusColors = staticCompositionLocalOf { LightStatusColors }
val LocalClanSpacing = staticCompositionLocalOf { ClanSpacing() }
val LocalClanBorders = staticCompositionLocalOf { ClanBorders() }
val LocalClanElevations = staticCompositionLocalOf { ClanElevations() }
