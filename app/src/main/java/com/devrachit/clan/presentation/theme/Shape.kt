package com.devrachit.clan.presentation.theme

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

/**
 * Clan App Shape System
 *
 * Clash of Clans inspired component shapes:
 * - Rounded game cards & action buttons
 * - Pill-shaped resource tags & badges
 * - Optional beveled cut-corners for war banners
 */

// ══════════════════════════════════════════════
// MATERIAL 3 SHAPES SPECIFICATION
// ══════════════════════════════════════════════

val ClanShapes = Shapes(
    extraSmall = RoundedCornerShape(4.dp),
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(12.dp),
    large = RoundedCornerShape(18.dp),
    extraLarge = RoundedCornerShape(26.dp)
)

// ══════════════════════════════════════════════
// DOMAIN-SPECIFIC GAME SHAPES
// ══════════════════════════════════════════════

@Immutable
data class ClanGameShapes(
    val gameButton: Shape = RoundedCornerShape(12.dp),
    val actionButtonLarge: Shape = RoundedCornerShape(16.dp),
    val resourcePill: Shape = RoundedCornerShape(50),
    val cardContainer: Shape = RoundedCornerShape(16.dp),
    val modalDialog: Shape = RoundedCornerShape(24.dp),
    val levelBadge: Shape = CircleShape,
    val townHallBadge: Shape = RoundedCornerShape(10.dp),
    val warBanner: Shape = CutCornerShape(topStart = 0.dp, topEnd = 0.dp, bottomStart = 8.dp, bottomEnd = 8.dp),
    val progressTrack: Shape = RoundedCornerShape(6.dp)
)

val LocalClanGameShapes = staticCompositionLocalOf { ClanGameShapes() }
