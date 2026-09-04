package com.devrachit.clan.presentation.screens.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.devrachit.clan.common.constants.AppStrings
import com.devrachit.clan.presentation.components.button.ClanButton
import com.devrachit.clan.presentation.components.button.ClanButtonVariant
import com.devrachit.clan.presentation.components.text.ClanBodyText
import com.devrachit.clan.presentation.components.text.ClanDisplayText
import com.devrachit.clan.presentation.components.text.ClanHeadingText
import com.devrachit.clan.presentation.components.text.ClanLabelText
import com.devrachit.clan.presentation.components.text.ClanResourceText
import com.devrachit.clan.presentation.components.text.ClanTitleText
import com.devrachit.clan.presentation.components.text.ClanWarBannerText
import com.devrachit.clan.presentation.theme.ClanTheme
import com.devrachit.clan.presentation.viewmodels.ThemeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BaseActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val themeViewModel: ThemeViewModel = hiltViewModel()
            val themeMode by themeViewModel.themeMode.collectAsState()
            val systemDark = isSystemInDarkTheme()
            val isDarkTheme = themeMode.isDark(systemDark)

            ClanTheme(darkTheme = isDarkTheme) {
                ClanDashboardScreen(
                    onToggleTheme = { themeViewModel.toggleTheme(systemDark) }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClanDashboardScreen(
    onToggleTheme: () -> Unit = {}
) {
    val isDarkTheme = ClanTheme.isDarkTheme
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        ClanDisplayText(
                            text = AppStrings.App.TITLE,
                            fontSize = 22.sp,
                            color = ClanTheme.colors.primary,
                            letterSpacing = 1.sp
                        )
                        Spacer(modifier = Modifier.width(ClanTheme.spacing.small))
                        Box(
                            modifier = Modifier
                                .clip(ClanTheme.gameShapes.townHallBadge)
                                .background(ClanTheme.resources.gold)
                                .padding(horizontal = 8.dp, vertical = 2.dp)
                        ) {
                            ClanDisplayText(
                                text = AppStrings.App.DEFAULT_TH_LEVEL,
                                fontSize = 12.sp,
                                color = Color(0xFF23140B)
                            )
                        }
                    }
                },
                actions = {
                    // Day/Night Mode Switcher in Dashboard Top Bar
                    Box(
                        modifier = Modifier
                            .padding(end = ClanTheme.spacing.small)
                            .clip(ClanTheme.gameShapes.resourcePill)
                            .background(ClanTheme.colors.surfaceContainerHigh)
                            .border(
                                width = ClanTheme.borders.thin,
                                color = ClanTheme.colors.outlineVariant,
                                shape = ClanTheme.gameShapes.resourcePill
                            )
                            .clickable { onToggleTheme() }
                            .padding(horizontal = 10.dp, vertical = 6.dp)
                    ) {
                        ClanLabelText(
                            text = if (isDarkTheme) AppStrings.Theme.NIGHT_BASE else AppStrings.Theme.DAY_VILLAGE,
                            fontSize = 11.sp,
                            color = ClanTheme.colors.onSurface
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = ClanTheme.colors.surface,
                    titleContentColor = ClanTheme.colors.onSurface
                )
            )
        },
        containerColor = ClanTheme.colors.background
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = ClanTheme.spacing.medium),
            verticalArrangement = Arrangement.spacedBy(ClanTheme.spacing.medium)
        ) {
            item { Spacer(modifier = Modifier.height(ClanTheme.spacing.extraSmall)) }

            // 1. Resources Overview Card
            item {
                ResourceOverviewCard()
            }

            // 2. Active Builder Timers Card
            item {
                BuilderTrackerCard()
            }

            // 3. Clan War League Card
            item {
                ClanWarStatusCard()
            }

            // 4. Common Action Buttons Showcase
            item {
                ActionButtonsShowcase()
            }

            item { Spacer(modifier = Modifier.height(ClanTheme.spacing.large)) }
        }
    }
}

@Composable
fun ResourceOverviewCard() {
    Card(
        shape = ClanTheme.gameShapes.cardContainer,
        colors = CardDefaults.cardColors(containerColor = ClanTheme.colors.surfaceContainer),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(ClanTheme.spacing.medium)) {
            ClanHeadingText(
                text = AppStrings.Dashboard.VILLAGE_RESOURCES_TITLE,
                fontSize = 18.sp,
                color = ClanTheme.colors.onSurface
            )

            Spacer(modifier = Modifier.height(ClanTheme.spacing.mediumSmall))

            ResourceItem(
                name = AppStrings.Resources.GOLD,
                amount = AppStrings.Resources.SAMPLE_GOLD_COUNT,
                progress = 0.84f,
                barBrush = ClanTheme.gradients.goldBar,
                color = ClanTheme.resources.gold
            )
            Spacer(modifier = Modifier.height(ClanTheme.spacing.small))
            ResourceItem(
                name = AppStrings.Resources.ELIXIR,
                amount = AppStrings.Resources.SAMPLE_ELIXIR_COUNT,
                progress = 0.65f,
                barBrush = ClanTheme.gradients.elixirBar,
                color = ClanTheme.resources.elixir
            )
            Spacer(modifier = Modifier.height(ClanTheme.spacing.small))
            ResourceItem(
                name = AppStrings.Resources.DARK_ELIXIR,
                amount = AppStrings.Resources.SAMPLE_DARK_ELIXIR_COUNT,
                progress = 0.88f,
                barBrush = ClanTheme.gradients.darkElixirBar,
                color = ClanTheme.resources.darkElixirAccent
            )
            Spacer(modifier = Modifier.height(ClanTheme.spacing.small))
            ResourceItem(
                name = AppStrings.Resources.GEMS,
                amount = AppStrings.Resources.SAMPLE_GEMS_COUNT,
                progress = 1.0f,
                barBrush = ClanTheme.gradients.gemsBar,
                color = ClanTheme.resources.gems
            )
        }
    }
}

@Composable
fun ResourceItem(name: String, amount: String, progress: Float, barBrush: Brush, color: Color) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ClanTitleText(text = name, fontSize = 14.sp, color = color)
            ClanResourceText(text = amount, fontSize = 13.sp, color = ClanTheme.colors.onSurface)
        }
        Spacer(modifier = Modifier.height(4.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
                .clip(ClanTheme.gameShapes.progressTrack)
                .background(ClanTheme.colors.surfaceContainerHighest)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(progress)
                    .height(10.dp)
                    .clip(ClanTheme.gameShapes.progressTrack)
                    .background(barBrush)
            )
        }
    }
}

@Composable
fun BuilderTrackerCard() {
    Card(
        shape = ClanTheme.gameShapes.cardContainer,
        colors = CardDefaults.cardColors(containerColor = ClanTheme.colors.surfaceContainer),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(ClanTheme.spacing.medium)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ClanHeadingText(
                    text = AppStrings.Dashboard.BUILDERS_LAB_TITLE,
                    fontSize = 18.sp,
                    color = ClanTheme.colors.onSurface
                )
                Box(
                    modifier = Modifier
                        .clip(ClanTheme.gameShapes.resourcePill)
                        .background(ClanTheme.status.upgrading.copy(alpha = 0.15f))
                        .padding(horizontal = 8.dp, vertical = 2.dp)
                ) {
                    ClanDisplayText(
                        text = AppStrings.Common.BUSY,
                        fontSize = 11.sp,
                        color = ClanTheme.status.upgrading
                    )
                }
            }

            Spacer(modifier = Modifier.height(ClanTheme.spacing.small))

            UpgradeTimerRow(
                title = AppStrings.Dashboard.UPGRADE_EAGLE_ARTILLERY,
                timeRemaining = AppStrings.Dashboard.UPGRADE_EAGLE_TIME,
                progress = 0.72f,
                resourceColor = ClanTheme.resources.gold
            )
            UpgradeTimerRow(
                title = AppStrings.Dashboard.UPGRADE_ROOT_RIDER,
                timeRemaining = AppStrings.Dashboard.UPGRADE_ROOT_RIDER_TIME,
                progress = 0.94f,
                resourceColor = ClanTheme.resources.elixir
            )
            UpgradeTimerRow(
                title = AppStrings.Dashboard.UPGRADE_GRAND_WARDEN,
                timeRemaining = AppStrings.Dashboard.UPGRADE_GRAND_WARDEN_TIME,
                progress = 0.45f,
                resourceColor = ClanTheme.resources.elixir
            )
        }
    }
}

@Composable
fun UpgradeTimerRow(title: String, timeRemaining: String, progress: Float, resourceColor: Color) {
    Column(modifier = Modifier.padding(vertical = 6.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ClanBodyText(
                text = title,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                color = ClanTheme.colors.onSurface
            )
            ClanBodyText(
                text = timeRemaining,
                fontSize = 12.sp,
                color = resourceColor
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier
                .fillMaxWidth()
                .height(6.dp)
                .clip(ClanTheme.gameShapes.progressTrack),
            color = resourceColor,
            trackColor = ClanTheme.colors.surfaceContainerHighest
        )
    }
}

@Composable
fun ClanWarStatusCard() {
    Card(
        shape = ClanTheme.gameShapes.cardContainer,
        colors = CardDefaults.cardColors(containerColor = ClanTheme.colors.surfaceContainer),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(ClanTheme.spacing.medium)) {
            ClanWarBannerText(
                text = AppStrings.Dashboard.WAR_LEAGUE_TITLE,
                fontSize = 20.sp,
                color = ClanTheme.status.warAttack,
                letterSpacing = 0.5.sp
            )
            Spacer(modifier = Modifier.height(ClanTheme.spacing.extraSmall))
            ClanBodyText(
                text = AppStrings.Dashboard.WAR_STATUS_SUBTITLE,
                fontSize = 13.sp,
                color = ClanTheme.colors.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(ClanTheme.spacing.medium))

            ClanButton(
                text = AppStrings.Dashboard.PLAN_WAR_ATTACK_CTA,
                onClick = { /* Attack Planner */ },
                variant = ClanButtonVariant.Danger,
                height = 50.dp
            )
        }
    }
}

@Composable
fun ActionButtonsShowcase() {
    Column {
        ClanHeadingText(
            text = AppStrings.Dashboard.BUTTON_VARIANTS_TITLE,
            fontSize = 18.sp,
            color = ClanTheme.colors.onBackground
        )
        Spacer(modifier = Modifier.height(ClanTheme.spacing.small))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(ClanTheme.spacing.small)
        ) {
            ClanButton(
                text = AppStrings.Common.UPGRADE,
                onClick = {},
                variant = ClanButtonVariant.Primary,
                height = 46.dp,
                modifier = Modifier.weight(1f)
            )

            ClanButton(
                text = AppStrings.Common.STATS,
                onClick = {},
                variant = ClanButtonVariant.Secondary,
                height = 46.dp,
                modifier = Modifier.weight(1f)
            )

            ClanButton(
                text = AppStrings.Common.CANCEL,
                onClick = {},
                variant = ClanButtonVariant.Outlined,
                height = 46.dp,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ClanDashboardLightPreview() {
    ClanTheme(darkTheme = false) {
        ClanDashboardScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun ClanDashboardDarkPreview() {
    ClanTheme(darkTheme = true) {
        ClanDashboardScreen()
    }
}