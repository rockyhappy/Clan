package com.devrachit.clan.presentation.screens.splash.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.devrachit.clan.presentation.theme.ClanTheme

/**
 * Animated expanding dot indicators for the pager.
 */
@Composable
internal fun SplashDotIndicators(
    currentPage: Int,
    pageCount: Int
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(pageCount) { index ->
            val isSelected = currentPage == index
            val width by animateDpAsState(
                targetValue = if (isSelected) 32.dp else 8.dp,
                animationSpec = tween(300),
                label = "dotWidth"
            )
            val color by animateColorAsState(
                targetValue = if (isSelected) ClanTheme.colors.primary else ClanTheme.colors.outlineVariant,
                animationSpec = tween(300),
                label = "dotColor"
            )
            Box(
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .height(8.dp)
                    .width(width)
                    .clip(ClanTheme.gameShapes.resourcePill)
                    .background(color)
            )
        }
    }
}

@androidx.compose.ui.tooling.preview.Preview(showBackground = true)
@Composable
private fun SplashDotIndicatorsPreview() {
    ClanTheme {
        SplashDotIndicators(currentPage = 1, pageCount = 3)
    }
}
