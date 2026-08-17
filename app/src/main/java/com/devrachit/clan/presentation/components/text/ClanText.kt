package com.devrachit.clan.presentation.components.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.devrachit.clan.presentation.theme.ClanTheme

/**
 * ══════════════════════════════════════════════════════════════════
 * CLAN CUSTOM TEXT COMPONENTS SUITE
 * ══════════════════════════════════════════════════════════════════
 *
 * Rule: Whenever adding text in the UI, use one of these semantic text
 * components rather than calling raw Text() with ad-hoc styling.
 *
 * 1. [ClanDisplayText]   → Hero text, splash titles, big badges (Lilita One)
 * 2. [ClanHeadingText]   → Screen headers, section titles (Fredoka Bold)
 * 3. [ClanTitleText]     → Card headers, item titles (Fredoka Medium)
 * 4. [ClanBodyText]      → Paragraphs, explanations, descriptions (Nunito)
 * 5. [ClanLabelText]     → Button text, chips, small badges (Nunito SemiBold)
 * 6. [ClanResourceText]  → Resource amounts, stats digits (Lilita One)
 * 7. [ClanWarBannerText] → War headlines, victory/attack banners (Luckiest Guy)
 * 8. [ClanLoreText]      → Medieval fantasy lore, clan perk notes (MedievalSharp)
 */

/**
 * Display / Hero text using chunky game display font (Lilita One).
 */
@Composable
fun ClanDisplayText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = ClanTheme.colors.onBackground,
    fontSize: TextUnit = 36.sp,
    textAlign: TextAlign? = null,
    letterSpacing: TextUnit = (-0.2).sp,
    lineHeight: TextUnit = TextUnit.Unspecified,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Clip
) {
    Text(
        text = text,
        modifier = modifier,
        color = color,
        fontSize = fontSize,
        fontFamily = ClanTheme.fonts.gameDisplay,
        fontWeight = FontWeight.Normal,
        textAlign = textAlign,
        letterSpacing = letterSpacing,
        lineHeight = lineHeight,
        maxLines = maxLines,
        overflow = overflow
    )
}

/**
 * Section and screen heading text using friendly rounded font (Fredoka Bold).
 */
@Composable
fun ClanHeadingText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = ClanTheme.colors.onBackground,
    fontSize: TextUnit = 22.sp,
    fontWeight: FontWeight = FontWeight.Bold,
    textAlign: TextAlign? = null,
    letterSpacing: TextUnit = 0.sp,
    lineHeight: TextUnit = TextUnit.Unspecified,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Clip
) {
    Text(
        text = text,
        modifier = modifier,
        color = color,
        fontSize = fontSize,
        fontFamily = ClanTheme.fonts.heading,
        fontWeight = fontWeight,
        textAlign = textAlign,
        letterSpacing = letterSpacing,
        lineHeight = lineHeight,
        maxLines = maxLines,
        overflow = overflow
    )
}

/**
 * Card titles and modal subtitle text using Fredoka Medium/SemiBold.
 */
@Composable
fun ClanTitleText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = ClanTheme.colors.onSurface,
    fontSize: TextUnit = 16.sp,
    fontWeight: FontWeight = FontWeight.SemiBold,
    textAlign: TextAlign? = null,
    letterSpacing: TextUnit = 0.15.sp,
    lineHeight: TextUnit = TextUnit.Unspecified,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Clip
) {
    Text(
        text = text,
        modifier = modifier,
        color = color,
        fontSize = fontSize,
        fontFamily = ClanTheme.fonts.heading,
        fontWeight = fontWeight,
        textAlign = textAlign,
        letterSpacing = letterSpacing,
        lineHeight = lineHeight,
        maxLines = maxLines,
        overflow = overflow
    )
}

/**
 * Body and descriptive paragraph text using ultra-readable Nunito font.
 */
@Composable
fun ClanBodyText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = ClanTheme.colors.onSurfaceVariant,
    fontSize: TextUnit = 14.sp,
    fontWeight: FontWeight = FontWeight.Normal,
    textAlign: TextAlign? = null,
    letterSpacing: TextUnit = 0.25.sp,
    lineHeight: TextUnit = 22.sp,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Clip
) {
    Text(
        text = text,
        modifier = modifier,
        color = color,
        fontSize = fontSize,
        fontFamily = ClanTheme.fonts.body,
        fontWeight = fontWeight,
        textAlign = textAlign,
        letterSpacing = letterSpacing,
        lineHeight = lineHeight,
        maxLines = maxLines,
        overflow = overflow
    )
}

/**
 * Interactive label text for buttons, chips, tabs, and tags (Nunito SemiBold/Bold).
 */
@Composable
fun ClanLabelText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = ClanTheme.colors.onSurface,
    fontSize: TextUnit = 13.sp,
    fontWeight: FontWeight = FontWeight.SemiBold,
    textAlign: TextAlign? = null,
    letterSpacing: TextUnit = 0.3.sp,
    maxLines: Int = 1,
    overflow: TextOverflow = TextOverflow.Ellipsis
) {
    Text(
        text = text,
        modifier = modifier,
        color = color,
        fontSize = fontSize,
        fontFamily = ClanTheme.fonts.body,
        fontWeight = fontWeight,
        textAlign = textAlign,
        letterSpacing = letterSpacing,
        maxLines = maxLines,
        overflow = overflow
    )
}

/**
 * Dedicated resource counts and stats numeric text using Lilita One font.
 */
@Composable
fun ClanResourceText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = ClanTheme.resources.gold,
    fontSize: TextUnit = 14.sp,
    textAlign: TextAlign? = null,
    letterSpacing: TextUnit = 0.5.sp
) {
    Text(
        text = text,
        modifier = modifier,
        color = color,
        fontSize = fontSize,
        fontFamily = ClanTheme.fonts.gameDisplay,
        fontWeight = FontWeight.Normal,
        textAlign = textAlign,
        letterSpacing = letterSpacing,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}

/**
 * War banner and high-impact action headlines using Luckiest Guy font.
 */
@Composable
fun ClanWarBannerText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = ClanTheme.status.warAttack,
    fontSize: TextUnit = 20.sp,
    textAlign: TextAlign? = null,
    letterSpacing: TextUnit = 0.5.sp,
    maxLines: Int = 2,
    overflow: TextOverflow = TextOverflow.Ellipsis
) {
    Text(
        text = text,
        modifier = modifier,
        color = color,
        fontSize = fontSize,
        fontFamily = ClanTheme.fonts.warBanner,
        fontWeight = FontWeight.Normal,
        textAlign = textAlign,
        letterSpacing = letterSpacing,
        maxLines = maxLines,
        overflow = overflow
    )
}

/**
 * Medieval fantasy lore and flavor text using MedievalSharp font.
 */
@Composable
fun ClanLoreText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = ClanTheme.colors.onSurfaceVariant,
    fontSize: TextUnit = 14.sp,
    textAlign: TextAlign? = null,
    letterSpacing: TextUnit = 0.3.sp,
    lineHeight: TextUnit = 22.sp,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Clip
) {
    Text(
        text = text,
        modifier = modifier,
        color = color,
        fontSize = fontSize,
        fontFamily = ClanTheme.fonts.fantasyLore,
        fontWeight = FontWeight.Normal,
        textAlign = textAlign,
        letterSpacing = letterSpacing,
        lineHeight = lineHeight,
        maxLines = maxLines,
        overflow = overflow
    )
}
