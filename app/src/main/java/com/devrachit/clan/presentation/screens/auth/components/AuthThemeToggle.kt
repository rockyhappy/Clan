package com.devrachit.clan.presentation.screens.auth.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devrachit.clan.common.constants.AppStrings
import com.devrachit.clan.presentation.components.text.ClanLabelText
import com.devrachit.clan.presentation.theme.ClanTheme

@Composable
internal fun AuthThemeToggle(
    onToggleTheme: () -> Unit,
    modifier: Modifier = Modifier
) {
    val isDarkTheme = ClanTheme.isDarkTheme

    Box(
        modifier = modifier
            .clip(ClanTheme.gameShapes.resourcePill)
            .background(ClanTheme.colors.surfaceContainerHigh)
            .border(
                width = ClanTheme.borders.thin,
                color = ClanTheme.colors.outlineVariant,
                shape = ClanTheme.gameShapes.resourcePill
            )
            .clickable { onToggleTheme() }
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        ClanLabelText(
            text = if (isDarkTheme) AppStrings.Theme.NIGHT_BASE else AppStrings.Theme.DAY_VILLAGE,
            fontSize = 12.sp,
            color = ClanTheme.colors.onSurface
        )
    }
}

@androidx.compose.ui.tooling.preview.Preview
@Composable
private fun AuthThemeTogglePreview() {
    ClanTheme {
        AuthThemeToggle(onToggleTheme = {})
    }
}
