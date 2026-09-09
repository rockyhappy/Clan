package com.devrachit.clan.presentation.screens.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devrachit.clan.common.constants.AppStrings
import com.devrachit.clan.presentation.components.text.ClanLabelText
import com.devrachit.clan.presentation.theme.ClanTheme

enum class DashboardTab {
    VILLAGE, WARS, LAB
}

@Composable
fun ClanBottomBar(
    currentTab: DashboardTab,
    onTabSelected: (DashboardTab) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp)
            .clip(CircleShape)
            .background(ClanTheme.colors.surfaceContainerHighest)
            .border(
                width = ClanTheme.borders.thin,
                color = ClanTheme.colors.outlineVariant,
                shape = CircleShape
            )
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BottomBarItem(
            title = AppStrings.Common.VILLAGE,
            isSelected = currentTab == DashboardTab.VILLAGE,
            onClick = { onTabSelected(DashboardTab.VILLAGE) },
            modifier = Modifier.weight(1f)
        )
        BottomBarItem(
            title = AppStrings.Common.WARS,
            isSelected = currentTab == DashboardTab.WARS,
            onClick = { onTabSelected(DashboardTab.WARS) },
            modifier = Modifier.weight(1f)
        )
        BottomBarItem(
            title = AppStrings.Common.LAB,
            isSelected = currentTab == DashboardTab.LAB,
            onClick = { onTabSelected(DashboardTab.LAB) },
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun BottomBarItem(
    title: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val backgroundColor = if (isSelected) ClanTheme.colors.primary else Color.Transparent
    val textColor = if (isSelected) ClanTheme.colors.onPrimary else ClanTheme.colors.onSurfaceVariant

    Box(
        modifier = modifier
            .height(40.dp)
            .clip(CircleShape)
            .background(backgroundColor)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        ClanLabelText(
            text = title,
            color = textColor,
            fontSize = 14.sp
        )
    }
}
