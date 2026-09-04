package com.devrachit.clan.presentation.screens.splash.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.devrachit.clan.common.assets.Assets.DarkElixirStorage
import com.devrachit.clan.common.assets.Assets.ElixirStorage
import com.devrachit.clan.common.assets.Assets.TownHall
import com.devrachit.clan.common.constants.AppStrings
import com.devrachit.clan.presentation.screens.splash.states.SplashPage
import com.devrachit.clan.presentation.theme.ClanTheme
import kotlinx.coroutines.launch

/**
 * Displayed for unauthenticated users — the full onboarding
 * horizontal pager with dot indicators and Get Started button.
 */
@Composable
internal fun SplashOnboardingContent(
    onToggleTheme: () -> Unit,
    onNavigateToAuth: () -> Unit
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

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // ── Top Bar: Theme Toggle & Skip Button ──
        SplashTopBar(
            onToggleTheme = onToggleTheme,
            onSkip = onNavigateToAuth
        )

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
        SplashBottomControls(
            pagerState = pagerState,
            pageCount = pages.size,
            onNextPage = {
                coroutineScope.launch {
                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                }
            },
            onGetStarted = onNavigateToAuth
        )
    }
}
