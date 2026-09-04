package com.devrachit.clan.presentation.screens.auth

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devrachit.clan.common.constants.AppStrings
import com.devrachit.clan.presentation.components.button.ClanButton
import com.devrachit.clan.presentation.components.button.ClanButtonVariant
import com.devrachit.clan.presentation.components.text.ClanBodyText
import com.devrachit.clan.presentation.components.text.ClanDisplayText
import com.devrachit.clan.presentation.components.text.ClanHeadingText
import com.devrachit.clan.presentation.components.text.ClanLabelText
import com.devrachit.clan.presentation.components.text.ClanLoreText
import com.devrachit.clan.presentation.theme.ClanTheme

@Composable
fun AuthScreen(
    onToggleTheme: () -> Unit = {},
    onImport: () -> Unit
) {
    val isDarkTheme = ClanTheme.isDarkTheme
    var jsonText by remember { mutableStateOf("") }
    var showHowTo by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ClanTheme.colors.background)
    ) {
        // Top-left Theme Toggle
        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
                .statusBarsPadding()
                .padding(ClanTheme.spacing.medium)
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

        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .padding(top = ClanTheme.spacing.huge)
                .padding(horizontal = ClanTheme.spacing.large)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(ClanTheme.spacing.large))

            // Main Card
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(ClanTheme.gameShapes.cardContainer)
                    .background(ClanTheme.colors.surface)
                    .border(
                        width = ClanTheme.borders.regular,
                        color = ClanTheme.colors.outlineVariant,
                        shape = ClanTheme.gameShapes.cardContainer
                    )
                    .padding(ClanTheme.spacing.large)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .clip(ClanTheme.gameShapes.resourcePill)
                            .background(ClanTheme.resources.gold.copy(alpha = 0.15f))
                            .padding(horizontal = 12.dp, vertical = 4.dp)
                    ) {
                        ClanDisplayText(
                            text = AppStrings.Auth.IMPORT_SUBTITLE,
                            fontSize = 12.sp,
                            color = ClanTheme.resources.gold,
                            letterSpacing = 1.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(ClanTheme.spacing.small))

                    ClanHeadingText(
                        text = AppStrings.Auth.IMPORT_TITLE,
                        fontSize = 24.sp,
                        color = ClanTheme.colors.onSurface
                    )

                    Spacer(modifier = Modifier.height(ClanTheme.spacing.small))

                    ClanBodyText(
                        text = AppStrings.Auth.IMPORT_DESC,
                        fontSize = 14.sp,
                        color = ClanTheme.colors.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(ClanTheme.spacing.medium))

                    OutlinedTextField(
                        value = jsonText,
                        onValueChange = { jsonText = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        placeholder = {
                            Text(
                                text = AppStrings.Auth.JSON_PLACEHOLDER,
                                color = ClanTheme.colors.onSurfaceVariant.copy(alpha = 0.5f),
                                fontFamily = ClanTheme.fonts.body
                            )
                        },
                        label = {
                            Text(
                                text = AppStrings.Auth.JSON_LABEL,
                                color = ClanTheme.colors.primary,
                                fontFamily = ClanTheme.fonts.heading
                            )
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = ClanTheme.colors.onSurface,
                            unfocusedTextColor = ClanTheme.colors.onSurface,
                            focusedBorderColor = ClanTheme.colors.primary,
                            unfocusedBorderColor = ClanTheme.colors.outline,
                            focusedContainerColor = ClanTheme.colors.surfaceContainer,
                            unfocusedContainerColor = ClanTheme.colors.surfaceContainer
                        ),
                        shape = ClanTheme.gameShapes.cardContainer
                    )
                }
            }

            Spacer(modifier = Modifier.height(ClanTheme.spacing.large))

            // Expandable section
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(ClanTheme.gameShapes.cardContainer)
                    .background(ClanTheme.colors.surfaceContainer)
                    .clickable { showHowTo = !showHowTo }
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

            Spacer(modifier = Modifier.height(ClanTheme.spacing.huge))

            ClanButton(
                text = AppStrings.Auth.IMPORT_CTA,
                onClick = onImport,
                variant = ClanButtonVariant.Success
            )
            
            Spacer(modifier = Modifier.height(ClanTheme.spacing.large))
        }
    }
}
