package com.devrachit.clan.presentation.splash

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devrachit.clan.common.assets.Assets.DarkElixirStorage
import com.devrachit.clan.common.assets.Assets.ElixirStorage
import com.devrachit.clan.common.assets.Assets.TownHall
import com.devrachit.clan.common.constants.AppStrings
import com.devrachit.clan.presentation.components.button.ClanButton
import com.devrachit.clan.presentation.components.button.ClanButtonVariant
import com.devrachit.clan.presentation.components.text.ClanBodyText
import com.devrachit.clan.presentation.components.text.ClanDisplayText
import com.devrachit.clan.presentation.components.text.ClanHeadingText
import com.devrachit.clan.presentation.components.text.ClanLabelText
import com.devrachit.clan.presentation.theme.ClanTheme
import kotlinx.coroutines.launch

@Composable
fun SplashScreen(
    isDarkTheme: Boolean = false,
    onToggleTheme: () -> Unit = {},
    onGetStarted: () -> Unit
) {
    val gold = ClanTheme.resources.gold
    val elixir = ClanTheme.resources.elixir
    val gems = ClanTheme.resources.gems

    val pages = listOf(
        SplashPage(
            title = AppStrings.Splash.PAGE_1_TITLE,
            subtitle = AppStrings.Splash.PAGE_1_SUBTITLE,
            description = AppStrings.Splash.PAGE_1_DESC,
            badgeColor = gold,
            iconUrl = TownHall[14]
        ),
        SplashPage(
            title = AppStrings.Splash.PAGE_2_TITLE,
            subtitle = AppStrings.Splash.PAGE_2_SUBTITLE,
            description = AppStrings.Splash.PAGE_2_DESC,
            badgeColor = elixir,
            iconUrl = ElixirStorage[10]
        ),
        SplashPage(
            title = AppStrings.Splash.PAGE_3_TITLE,
            subtitle = AppStrings.Splash.PAGE_3_SUBTITLE,
            description = AppStrings.Splash.PAGE_3_DESC,
            badgeColor = gems,
            iconUrl = DarkElixirStorage[10]
        )
    )

    val pagerState = rememberPagerState(pageCount = { pages.size })
    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ClanTheme.colors.background)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // ── Top Bar: Theme Toggle & Skip Button ──
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = ClanTheme.spacing.medium, vertical = ClanTheme.spacing.small),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Day/Night Theme Switcher Badge
                Box(
                    modifier = Modifier
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

                // Skip Button
                TextButton(onClick = onGetStarted) {
                    ClanLabelText(
                        text = AppStrings.Common.SKIP,
                        color = ClanTheme.colors.onSurfaceVariant,
                        fontSize = 14.sp
                    )
                }
            }

            // ── Pager Content ──
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) { page ->
                SplashPageContent(pages[page])
            }

            // ── Bottom Controls: Animated Dots & Action Button ──
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = ClanTheme.spacing.extraLarge)
                    .padding(bottom = ClanTheme.spacing.large),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Expanding Dot Indicators
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = ClanTheme.spacing.large)
                ) {
                    repeat(pages.size) { index ->
                        val isSelected = pagerState.currentPage == index
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

                // Next / Get Started Button
                val isLastPage = pagerState.currentPage == pages.size - 1
                ClanButton(
                    text = if (isLastPage) AppStrings.Common.GET_STARTED else AppStrings.Common.NEXT,
                    onClick = {
                        if (pagerState.currentPage < pages.size - 1) {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                            }
                        } else {
                            onGetStarted()
                        }
                    },
                    variant = ClanButtonVariant.Primary
                )
            }
        }
    }
}

private class SplashPage(
    val title: String,
    val subtitle: String,
    val description: String,
    val badgeColor: Color,
    val iconUrl: String
)

@Composable
private fun SplashPageContent(page: SplashPage) {
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
            coil.compose.AsyncImage(
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
