package com.devrachit.clan.presentation.screens.splash.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.devrachit.clan.common.assets.Assets.TownHall
import com.devrachit.clan.common.constants.AppStrings
import com.devrachit.clan.presentation.components.text.ClanBodyText
import com.devrachit.clan.presentation.components.text.ClanDisplayText
import com.devrachit.clan.presentation.components.text.ClanHeadingText
import com.devrachit.clan.presentation.theme.ClanTheme

/**
 * Displayed when the app is verifying auth status or the user is
 * already authenticated. Shows a Town Hall icon with a pulsing
 * animation, a progress indicator, and descriptive text.
 */
@Composable
internal fun SplashCheckingContent(
    onToggleTheme: () -> Unit
) {
    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val scale by infiniteTransition.animateFloat(
        initialValue = 0.95f,
        targetValue = 1.05f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 800, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "iconPulse"
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // ── Theme Toggle (top-left positioned) ──
        SplashThemeToggle(
            onToggleTheme = onToggleTheme,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = ClanTheme.spacing.medium,
                    vertical = ClanTheme.spacing.small
                )
        )

        Spacer(modifier = Modifier.weight(1f))

        // ── Pulsing Town Hall icon ──
        Box(
            modifier = Modifier
                .size(180.dp)
                .scale(scale)
                .clip(ClanTheme.gameShapes.modalDialog)
                .background(ClanTheme.colors.surfaceContainer)
                .border(
                    width = ClanTheme.borders.regular,
                    color = ClanTheme.colors.outlineVariant.copy(alpha = 0.5f),
                    shape = ClanTheme.gameShapes.modalDialog
                ),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = TownHall[14],
                contentDescription = AppStrings.Splash.CHECKING_TITLE,
                modifier = Modifier.size(120.dp)
            )
        }

        Spacer(modifier = Modifier.height(ClanTheme.spacing.large))

        // ── Subtitle badge ──
        Box(
            modifier = Modifier
                .clip(ClanTheme.gameShapes.resourcePill)
                .background(ClanTheme.resources.gold.copy(alpha = 0.15f))
                .padding(horizontal = ClanTheme.spacing.medium, vertical = ClanTheme.spacing.extraSmall)
        ) {
            ClanDisplayText(
                text = AppStrings.Splash.CHECKING_SUBTITLE,
                fontSize = 12.sp,
                color = ClanTheme.resources.gold,
                letterSpacing = 1.sp
            )
        }

        Spacer(modifier = Modifier.height(ClanTheme.spacing.small))

        // ── Title ──
        ClanHeadingText(
            text = AppStrings.Splash.CHECKING_TITLE,
            fontSize = 22.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(ClanTheme.spacing.small))

        // ── Description ──
        ClanBodyText(
            text = AppStrings.Splash.CHECKING_DESC,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            lineHeight = 22.sp,
            modifier = Modifier.padding(horizontal = ClanTheme.spacing.extraLarge)
        )

        Spacer(modifier = Modifier.height(ClanTheme.spacing.large))

        // ── Loading spinner ──
        CircularProgressIndicator(
            color = ClanTheme.colors.primary,
            trackColor = ClanTheme.colors.surfaceContainerHighest,
            strokeWidth = ClanTheme.borders.regular,
            modifier = Modifier.size(40.dp)
        )

        Spacer(modifier = Modifier.weight(1f))
    }
}

@androidx.compose.ui.tooling.preview.Preview(showBackground = true)
@Composable
private fun SplashCheckingContentPreview() {
    ClanTheme(darkTheme = true) {
        SplashCheckingContent(onToggleTheme = {})
    }
}
