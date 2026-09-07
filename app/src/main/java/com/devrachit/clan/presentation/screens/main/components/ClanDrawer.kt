package com.devrachit.clan.presentation.screens.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devrachit.clan.common.constants.AppStrings
import com.devrachit.clan.presentation.components.text.ClanBodyText
import com.devrachit.clan.presentation.components.text.ClanDisplayText
import com.devrachit.clan.presentation.components.text.ClanHeadingText
import com.devrachit.clan.presentation.theme.ClanTheme

@Composable
fun ClanDrawer(
    username: String,
    onClose: () -> Unit,
    onLogout: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .width(260.dp)
            .background(ClanTheme.colors.background)
            .padding(top = 48.dp, start = 24.dp, end = 24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(ClanTheme.gameShapes.resourcePill)
                .background(ClanTheme.colors.surfaceContainerHigh)
                .border(
                    width = ClanTheme.borders.thin,
                    color = ClanTheme.colors.outlineVariant,
                    shape = ClanTheme.gameShapes.resourcePill
                )
                .clickable { onClose() },
            contentAlignment = Alignment.Center
        ) {
            ClanDisplayText(
                text = "X",
                color = ClanTheme.colors.onSurface,
                fontSize = 20.sp
            )
        }

        Spacer(modifier = Modifier.height(ClanTheme.spacing.large))

        // Profile Picture Placeholder
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(ClanTheme.colors.primary)
                .border(ClanTheme.borders.thick, ClanTheme.colors.outline, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            ClanDisplayText(
                text = username.take(1).uppercase(),
                color = ClanTheme.colors.onPrimary,
                fontSize = 32.sp
            )
        }

        Spacer(modifier = Modifier.height(ClanTheme.spacing.medium))

        ClanHeadingText(
            text = username,
            color = ClanTheme.colors.onBackground,
            fontSize = 24.sp
        )

        ClanBodyText(
            text = AppStrings.App.DEFAULT_TH_LEVEL,
            color = ClanTheme.resources.gold,
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(ClanTheme.spacing.large))

        HorizontalDivider(
            color = ClanTheme.colors.outlineVariant,
            thickness = 1.dp
        )

        Spacer(modifier = Modifier.height(ClanTheme.spacing.large))

        DrawerMenuItem(
            title = AppStrings.Common.PROFILE,
            onClick = { /* TODO */ }
        )

        DrawerMenuItem(
            title = AppStrings.Common.SETTINGS,
            onClick = { /* TODO */ }
        )

        Spacer(modifier = Modifier.weight(1f))

        DrawerMenuItem(
            title = AppStrings.Common.LOGOUT,
            onClick = onLogout,
            textColor = ClanTheme.colors.error
        )

        Spacer(modifier = Modifier.height(ClanTheme.spacing.huge))
    }
}

@Composable
fun DrawerMenuItem(
    title: String,
    onClick: () -> Unit,
    textColor: Color = ClanTheme.colors.onSurface
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(ClanTheme.gameShapes.gameButton)
            .clickable { onClick() }
            .padding(vertical = 12.dp, horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ClanHeadingText(
            text = title,
            color = textColor,
            fontSize = 18.sp
        )
    }
}
