package com.devrachit.clan.presentation.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

/**
 * Clan App Color System
 * Official Clash of Clans inspired palette for Light & Dark mode.
 *
 * Theme Archetype:
 * - Light Mode: Warm Medieval Parchment & Polished Brass/Gold tones with Rich Wood & Forest accents.
 * - Dark Mode: Deep Obsidian/Midnight Slate & Glow Gold with Dark Elixir Violet & Neon Gem highlights.
 */

// ══════════════════════════════════════════════
// 1. BRAND PALETTE (Clash of Clans Canonical)
// ══════════════════════════════════════════════

// Gold & Amber (Primary game currency, trophy gold, level badges)
val ClanGold50 = Color(0xFFFFF9E6)
val ClanGold100 = Color(0xFFFFECB3)
val ClanGold200 = Color(0xFFFFDF80)
val ClanGold300 = Color(0xFFFFD14D)
val ClanGold400 = Color(0xFFFFC800) // Canonical COC Game Gold
val ClanGold500 = Color(0xFFF5A623) // Rich Amber Gold
val ClanGold600 = Color(0xFFD98A00)
val ClanGold700 = Color(0xFFB87008) // Deep Bevel Gold
val ClanGold800 = Color(0xFF8F5400)
val ClanGold900 = Color(0xFF5A3400)

// Elixir Magenta & Violet (Standard troop currency & magic)
val ClanElixir50 = Color(0xFFFDE8F6)
val ClanElixir100 = Color(0xFFFBC6EB)
val ClanElixir200 = Color(0xFFF78DD8)
val ClanElixir300 = Color(0xFFFF77D1) // Highlight glow
val ClanElixir400 = Color(0xFFEA1B8D) // Iconic Elixir Magenta
val ClanElixir500 = Color(0xFFD0117A)
val ClanElixir600 = Color(0xFFAC0B64)
val ClanElixir700 = Color(0xFF7A0C47) // Shadow tone
val ClanElixir800 = Color(0xFF550631)
val ClanElixir900 = Color(0xFF33021C)

// Dark Elixir Violet & Obsidian (Hero upgrades, dark spells)
val ClanDarkElixir50 = Color(0xFFF3EDFB)
val ClanDarkElixir100 = Color(0xFFDDCBF5)
val ClanDarkElixir200 = Color(0xFFC0A0EE)
val ClanDarkElixir300 = Color(0xFFA072E6)
val ClanDarkElixir400 = Color(0xFF8045DD)
val ClanDarkElixir500 = Color(0xFF6328C2)
val ClanDarkElixir600 = Color(0xFF4C1CA0)
val ClanDarkElixir700 = Color(0xFF371279)
val ClanDarkElixir800 = Color(0xFF240A53)
val ClanDarkElixir900 = Color(0xFF140431)

// Gems & Village Grass (Emerald greens & nature)
val ClanGreen50 = Color(0xFFE8F8F0)
val ClanGreen100 = Color(0xFFC4EFD9)
val ClanGreen200 = Color(0xFF9BE5BF)
val ClanGreen300 = Color(0xFF69F0AE) // Gem facet highlight
val ClanGreen400 = Color(0xFF00D856) // Canonical COC Gem Green
val ClanGreen500 = Color(0xFF00B849)
val ClanGreen600 = Color(0xFF00933A)
val ClanGreen700 = Color(0xFF00702C)
val ClanGreen800 = Color(0xFF004F1F)
val ClanGreen900 = Color(0xFF003013)

// Stone & Slate (War maps, defensive walls, base plates)
val ClanSlate50 = Color(0xFFF0F3F8)
val ClanSlate100 = Color(0xFFD6DFEB)
val ClanSlate200 = Color(0xFFB0BFD4)
val ClanSlate300 = Color(0xFF839BB9)
val ClanSlate400 = Color(0xFF597AA2)
val ClanSlate500 = Color(0xFF3F5C82)
val ClanSlate600 = Color(0xFF2F4664)
val ClanSlate700 = Color(0xFF203046)
val ClanSlate800 = Color(0xFF152030)
val ClanSlate900 = Color(0xFF0D141F)
val ClanSlate950 = Color(0xFF070B12)

// Parchment & Warm Sand (Light theme canvases, stat cards)
val ClanParchment50 = Color(0xFFFFFDF9)
val ClanParchment100 = Color(0xFFFFF8ED) // Canonical Parchment
val ClanParchment200 = Color(0xFFF5EBE1)
val ClanParchment300 = Color(0xFFEADCCF)
val ClanParchment400 = Color(0xFFD7C2AE)
val ClanParchment500 = Color(0xFFBC9E85)
val ClanParchment600 = Color(0xFF9E7E64)
val ClanParchment700 = Color(0xFF785B45)
val ClanParchment800 = Color(0xFF523B2A)
val ClanParchment900 = Color(0xFF302116)

// Wood & Fortification (Dialog frames, button bevels)
val ClanWoodLight = Color(0xFFD7BFA8)
val ClanWoodMedium = Color(0xFF6B3E23)
val ClanWoodDark = Color(0xFF3A2315)
val ClanWoodDeep = Color(0xFF23140B)

// War & Alert Crimson (Clan Wars, destructive alerts, error)
val ClanWarRed50 = Color(0xFFFFECEC)
val ClanWarRed100 = Color(0xFFFFCECE)
val ClanWarRed200 = Color(0xFFFFA2A2)
val ClanWarRed300 = Color(0xFFFF7070)
val ClanWarRed400 = Color(0xFFFF4B4B)
val ClanWarRed500 = Color(0xFFE52020)
val ClanWarRed600 = Color(0xFFC01111)
val ClanWarRed700 = Color(0xFF9B0A0A)
val ClanWarRed800 = Color(0xFF6F0404)
val ClanWarRed900 = Color(0xFF450101)

// Shield & Info Blue (Guard shields, builder base, info tags)
val ClanShieldBlue300 = Color(0xFF80B7F7)
val ClanShieldBlue400 = Color(0xFF4298F5)
val ClanShieldBlue500 = Color(0xFF1976D2)
val ClanShieldBlue600 = Color(0xFF11569C)
val ClanShieldBlue700 = Color(0xFF0D47A1)

// ══════════════════════════════════════════════
// 2. MATERIAL 3 COLOR SCHEMES
// ══════════════════════════════════════════════

/**
 * Light Color Scheme:
 * Warm medieval aesthetic — parchment/linen surfaces, dark wood framing,
 * gold primary buttons & accents, dark legible typography.
 */
val ClanLightColorScheme = lightColorScheme(
    primary = ClanGold600,
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = ClanGold100,
    onPrimaryContainer = ClanGold900,
    inversePrimary = ClanGold400,

    secondary = ClanWoodMedium,
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = ClanParchment300,
    onSecondaryContainer = ClanWoodDeep,

    tertiary = ClanElixir500,
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = ClanElixir50,
    onTertiaryContainer = ClanElixir900,

    background = ClanParchment100,
    onBackground = ClanParchment900,

    surface = ClanParchment50,
    onSurface = ClanParchment900,
    surfaceVariant = ClanParchment200,
    onSurfaceVariant = ClanParchment800,
    surfaceTint = ClanGold600,
    inverseSurface = ClanSlate800,
    inverseOnSurface = ClanParchment100,

    surfaceContainerLowest = Color(0xFFFFFFFF),
    surfaceContainerLow = ClanParchment100,
    surfaceContainer = ClanParchment200,
    surfaceContainerHigh = ClanParchment300,
    surfaceContainerHighest = ClanParchment400,

    outline = ClanParchment600,
    outlineVariant = ClanParchment300,

    error = ClanWarRed600,
    onError = Color(0xFFFFFFFF),
    errorContainer = ClanWarRed100,
    onErrorContainer = ClanWarRed900,

    scrim = Color(0x99000000)
)

/**
 * Dark Color Scheme:
 * Obsidian & Slate nocturnal base with luminous Gold, Elixir Magenta,
 * and Gem Green highlights — true to late-night war battle raids.
 */
val ClanDarkColorScheme = darkColorScheme(
    primary = ClanGold400,
    onPrimary = Color(0xFF331D00),
    primaryContainer = ClanGold700,
    onPrimaryContainer = ClanGold100,
    inversePrimary = ClanGold600,

    secondary = ClanSlate300,
    onSecondary = ClanSlate900,
    secondaryContainer = ClanSlate700,
    onSecondaryContainer = ClanSlate100,

    tertiary = ClanElixir300,
    onTertiary = ClanElixir900,
    tertiaryContainer = ClanElixir700,
    onTertiaryContainer = ClanElixir100,

    background = ClanSlate950,
    onBackground = ClanParchment100,

    surface = ClanSlate900,
    onSurface = ClanParchment100,
    surfaceVariant = ClanSlate800,
    onSurfaceVariant = ClanSlate200,
    surfaceTint = ClanGold400,
    inverseSurface = ClanParchment200,
    inverseOnSurface = ClanSlate900,

    surfaceContainerLowest = Color(0xFF04070C),
    surfaceContainerLow = ClanSlate950,
    surfaceContainer = ClanSlate900,
    surfaceContainerHigh = ClanSlate800,
    surfaceContainerHighest = ClanSlate700,

    outline = ClanSlate400,
    outlineVariant = ClanSlate700,

    error = ClanWarRed300,
    onError = ClanWarRed900,
    errorContainer = ClanWarRed800,
    onErrorContainer = ClanWarRed100,

    scrim = Color(0xCC000000)
)