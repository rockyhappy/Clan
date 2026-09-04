package com.devrachit.clan.presentation.screens.splash.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.devrachit.clan.common.constants.AppStrings
import com.devrachit.clan.presentation.components.text.ClanLabelText
import com.devrachit.clan.presentation.theme.ClanTheme

/**
 * Top bar with theme toggle and skip button — used in onboarding state.
 */
@Composable
internal fun SplashTopBar(
    onToggleTheme: () -> Unit,
    onSkip: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = ClanTheme.spacing.medium, vertical = ClanTheme.spacing.small),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        SplashThemeToggle(
            onToggleTheme = onToggleTheme
        )

        // Skip Button
        TextButton(onClick = onSkip) {
            ClanLabelText(
                text = AppStrings.Common.SKIP,
                color = ClanTheme.colors.onSurfaceVariant,
                fontSize = 14.sp
            )
        }
    }
}
