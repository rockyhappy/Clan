package com.devrachit.clan.presentation.screens.splash.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.devrachit.clan.common.constants.AppStrings
import com.devrachit.clan.presentation.components.button.ClanButton
import com.devrachit.clan.presentation.components.button.ClanButtonVariant
import com.devrachit.clan.presentation.theme.ClanTheme

/**
 * Bottom controls: expanding dot indicators and the Next / Get Started button.
 */
@Composable
internal fun SplashBottomControls(
    pagerState: PagerState,
    pageCount: Int,
    onNextPage: () -> Unit,
    onGetStarted: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = ClanTheme.spacing.extraLarge)
            .padding(bottom = ClanTheme.spacing.large),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Expanding Dot Indicators
        SplashDotIndicators(
            currentPage = pagerState.currentPage,
            pageCount = pageCount
        )

        Spacer(modifier = Modifier.height(ClanTheme.spacing.large))

        // Next / Get Started Button
        val isLastPage = pagerState.currentPage == pageCount - 1
        ClanButton(
            text = if (isLastPage) AppStrings.Common.GET_STARTED else AppStrings.Common.NEXT,
            onClick = {
                if (!isLastPage) {
                    onNextPage()
                } else {
                    onGetStarted()
                }
            },
            variant = ClanButtonVariant.Primary
        )
    }
}
