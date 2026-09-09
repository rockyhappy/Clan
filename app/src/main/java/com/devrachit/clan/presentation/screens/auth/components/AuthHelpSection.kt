package com.devrachit.clan.presentation.screens.auth.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.sp
import com.devrachit.clan.common.constants.AppStrings
import com.devrachit.clan.presentation.components.text.ClanBodyText
import com.devrachit.clan.presentation.components.text.ClanHeadingText
import com.devrachit.clan.presentation.components.text.ClanLoreText
import com.devrachit.clan.presentation.theme.ClanTheme

@Composable
internal fun AuthHelpSection(
    showHowTo: Boolean,
    onToggleShowHowTo: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(ClanTheme.gameShapes.cardContainer)
            .background(ClanTheme.colors.surfaceContainer)
            .clickable { onToggleShowHowTo() }
            .padding(ClanTheme.spacing.medium)
    ) {
        Column {
            ClanHeadingText(
                text = AppStrings.Auth.HOW_TO_GET_JSON_TITLE,
                fontSize = 18.sp,
                color = ClanTheme.colors.primary
            )

            AnimatedVisibility(visible = showHowTo) {
                Column(modifier = Modifier.padding(top = ClanTheme.spacing.small)) {
                    ClanBodyText(text = AppStrings.Auth.HOW_TO_STEP_1, color = ClanTheme.colors.onSurface)
                    Spacer(modifier = Modifier.height(ClanTheme.spacing.extraSmall))
                    ClanBodyText(text = AppStrings.Auth.HOW_TO_STEP_2, color = ClanTheme.colors.onSurface)
                    Spacer(modifier = Modifier.height(ClanTheme.spacing.extraSmall))
                    ClanBodyText(text = AppStrings.Auth.HOW_TO_STEP_3, color = ClanTheme.colors.onSurface)
                    Spacer(modifier = Modifier.height(ClanTheme.spacing.extraSmall))
                    ClanBodyText(text = AppStrings.Auth.HOW_TO_STEP_4, color = ClanTheme.colors.onSurface)
                    Spacer(modifier = Modifier.height(ClanTheme.spacing.extraSmall))
                    ClanBodyText(text = AppStrings.Auth.HOW_TO_STEP_5, color = ClanTheme.colors.onSurface)
                    
                    Spacer(modifier = Modifier.height(ClanTheme.spacing.medium))
                    
                    ClanLoreText(
                        text = AppStrings.Auth.SCREENSHOTS_COMING_SOON,
                        color = ClanTheme.colors.onSurfaceVariant,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}

@androidx.compose.ui.tooling.preview.Preview
@Composable
private fun AuthHelpSectionPreview() {
    ClanTheme {
        AuthHelpSection(
            showHowTo = true,
            onToggleShowHowTo = {}
        )
    }
}
