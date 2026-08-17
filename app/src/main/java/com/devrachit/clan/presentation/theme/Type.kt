package com.devrachit.clan.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import com.devrachit.clan.R

/**
 * Clan App Typography System
 *
 * Font families carefully curated to match the Clash of Clans aesthetic:
 *
 * 1. **Lilita One** (Display & Badges) — Chunky, heavy, cartoon rounded style.
 *    The closest free Google Font equivalent to "Supercell Magic".
 *    Used for: Hero banners, resource counts, level badges, Town Hall levels.
 *
 * 2. **Luckiest Guy** (War & Action) — Heavyweight comic game lettering.
 *    Used for: Clan War titles, Victory/Defeat banners, attack badges.
 *
 * 3. **Fredoka** (Headings & Card Titles) — Chubby, friendly, geometric rounded sans.
 *    Used for: Section headers, card titles, troop names, builder stats.
 *
 * 4. **Nunito** (Body, Labels & UI) — Warm, rounded, ultra-legible.
 *    Used for: Upgrade descriptions, time remaining, settings, forms, buttons.
 *
 * 5. **MedievalSharp** (Lore & Fantasy) — Gothic medieval fantasy serif.
 *    Used for: Clan descriptions, clan perks flavor text, historical milestones.
 */

// ══════════════════════════════════════════════
// GOOGLE FONTS PROVIDER
// ══════════════════════════════════════════════

val googleFontProvider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

// ══════════════════════════════════════════════
// FONT FAMILIES
// ══════════════════════════════════════════════

// 1. Lilita One — Supercell Magic Match
val LilitaOneFont = GoogleFont("Lilita One")
val LilitaOneFamily = FontFamily(
    Font(googleFont = LilitaOneFont, fontProvider = googleFontProvider)
)

// 2. Luckiest Guy — War & Impact Font
val LuckiestGuyFont = GoogleFont("Luckiest Guy")
val LuckiestGuyFamily = FontFamily(
    Font(googleFont = LuckiestGuyFont, fontProvider = googleFontProvider)
)

// 3. Fredoka — Headings & Subtitles (Variable Weight)
val FredokaFont = GoogleFont("Fredoka")
val FredokaFamily = FontFamily(
    Font(googleFont = FredokaFont, fontProvider = googleFontProvider, weight = FontWeight.Light),
    Font(googleFont = FredokaFont, fontProvider = googleFontProvider, weight = FontWeight.Normal),
    Font(googleFont = FredokaFont, fontProvider = googleFontProvider, weight = FontWeight.Medium),
    Font(googleFont = FredokaFont, fontProvider = googleFontProvider, weight = FontWeight.SemiBold),
    Font(googleFont = FredokaFont, fontProvider = googleFontProvider, weight = FontWeight.Bold)
)

// 4. Nunito — Body & UI Labels
val NunitoFont = GoogleFont("Nunito")
val NunitoFamily = FontFamily(
    Font(googleFont = NunitoFont, fontProvider = googleFontProvider, weight = FontWeight.Light),
    Font(googleFont = NunitoFont, fontProvider = googleFontProvider, weight = FontWeight.Normal),
    Font(googleFont = NunitoFont, fontProvider = googleFontProvider, weight = FontWeight.Medium),
    Font(googleFont = NunitoFont, fontProvider = googleFontProvider, weight = FontWeight.SemiBold),
    Font(googleFont = NunitoFont, fontProvider = googleFontProvider, weight = FontWeight.Bold),
    Font(googleFont = NunitoFont, fontProvider = googleFontProvider, weight = FontWeight.ExtraBold),
    Font(googleFont = NunitoFont, fontProvider = googleFontProvider, weight = FontWeight.Black),
    Font(googleFont = NunitoFont, fontProvider = googleFontProvider, weight = FontWeight.Normal, style = FontStyle.Italic),
    Font(googleFont = NunitoFont, fontProvider = googleFontProvider, weight = FontWeight.Bold, style = FontStyle.Italic)
)

// 5. MedievalSharp — Fantasy & Clan Lore
val MedievalSharpFont = GoogleFont("MedievalSharp")
val MedievalSharpFamily = FontFamily(
    Font(googleFont = MedievalSharpFont, fontProvider = googleFontProvider)
)

/**
 * Extended Font Families container accessible via ClanTheme.fonts
 */
@Immutable
data class ClanFontFamilies(
    val gameDisplay: FontFamily = LilitaOneFamily,
    val warBanner: FontFamily = LuckiestGuyFamily,
    val heading: FontFamily = FredokaFamily,
    val body: FontFamily = NunitoFamily,
    val fantasyLore: FontFamily = MedievalSharpFamily
)

val LocalClanFontFamilies = staticCompositionLocalOf { ClanFontFamilies() }

// ══════════════════════════════════════════════
// MATERIAL 3 TYPOGRAPHY SPECIFICATION
// ══════════════════════════════════════════════

val ClanTypography = Typography(
    // ── Display: Hero stats, splash screens, massive resource numbers ──
    displayLarge = TextStyle(
        fontFamily = LilitaOneFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 54.sp,
        lineHeight = 60.sp,
        letterSpacing = (-0.25).sp
    ),
    displayMedium = TextStyle(
        fontFamily = LilitaOneFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 42.sp,
        lineHeight = 48.sp,
        letterSpacing = 0.sp
    ),
    displaySmall = TextStyle(
        fontFamily = LilitaOneFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 34.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.sp
    ),

    // ── Headline: Section headers, Town Hall tier headers ──
    headlineLarge = TextStyle(
        fontFamily = FredokaFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = FredokaFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 26.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = FredokaFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),

    // ── Title: Card titles, item upgrade titles, modal headers ──
    titleLarge = TextStyle(
        fontFamily = FredokaFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        lineHeight = 26.sp,
        letterSpacing = 0.sp
    ),
    titleMedium = TextStyle(
        fontFamily = FredokaFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 22.sp,
        letterSpacing = 0.15.sp
    ),
    titleSmall = TextStyle(
        fontFamily = FredokaFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),

    // ── Body: Detailed explanations, timer counts, stats values ──
    bodyLarge = TextStyle(
        fontFamily = NunitoFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = NunitoFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp
    ),
    bodySmall = TextStyle(
        fontFamily = NunitoFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.4.sp
    ),

    // ── Label: Interactive buttons, chips, tabs, level pills ──
    labelLarge = TextStyle(
        fontFamily = NunitoFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.2.sp
    ),
    labelMedium = TextStyle(
        fontFamily = NunitoFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),
    labelSmall = TextStyle(
        fontFamily = NunitoFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 11.sp,
        lineHeight = 14.sp,
        letterSpacing = 0.5.sp
    )
)