package com.devrachit.clan.presentation.screens.splash.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.devrachit.clan.presentation.components.text.ClanBodyText
import com.devrachit.clan.presentation.components.text.ClanDisplayText
import com.devrachit.clan.presentation.components.text.ClanHeadingText
import com.devrachit.clan.presentation.screens.splash.states.SplashPage
import com.devrachit.clan.presentation.theme.ClanTheme


/**
 * Single onboarding page content — icon, subtitle badge, title, and description.
 */
@Composable
internal fun SplashPageContent(page: SplashPage) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = ClanTheme.spacing.extraLarge),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Game-styled illustration frame
        Box(
            modifier = Modifier
                .size(230.dp)
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
                model = page.iconUrl,
                contentDescription = page.title,
                modifier = Modifier.size(150.dp)
            )
        }

        Spacer(modifier = Modifier.height(ClanTheme.spacing.large))

        // Subtitle badge
        Box(
            modifier = Modifier
                .clip(ClanTheme.gameShapes.resourcePill)
                .background(page.badgeColor.copy(alpha = 0.15f))
                .padding(horizontal = 12.dp, vertical = 4.dp)
        ) {
            ClanDisplayText(
                text = page.subtitle,
                fontSize = 12.sp,
                color = page.badgeColor,
                letterSpacing = 1.sp
            )
        }

        Spacer(modifier = Modifier.height(ClanTheme.spacing.small))

        // Main Title
        ClanHeadingText(
            text = page.title,
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            lineHeight = 30.sp
        )

        Spacer(modifier = Modifier.height(ClanTheme.spacing.mediumSmall))

        // Description Body
        ClanBodyText(
            text = page.description,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            lineHeight = 22.sp
        )
    }
}

@androidx.compose.ui.tooling.preview.Preview(showBackground = true)
@Composable
private fun SplashPageContentPreview() {
    ClanTheme {
        SplashPageContent(
            page = SplashPage(
                title = "Clash Helper & Tracker",
                subtitle = "BUILD & UPGRADE",
                description = "Track upgrades, analyze army compositions, manage builder queues, and never lose track of a village upgrade.",
                badgeColor = ClanTheme.resources.gold,
                iconUrl = com.devrachit.clan.common.assets.Assets.TownHall[14]
            )
        )
    }
}
