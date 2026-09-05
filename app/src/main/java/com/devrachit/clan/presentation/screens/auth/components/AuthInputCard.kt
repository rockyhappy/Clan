package com.devrachit.clan.presentation.screens.auth.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devrachit.clan.common.constants.AppStrings
import com.devrachit.clan.presentation.components.text.ClanBodyText
import com.devrachit.clan.presentation.components.text.ClanDisplayText
import com.devrachit.clan.presentation.components.text.ClanHeadingText
import com.devrachit.clan.presentation.theme.ClanTheme

@Composable
internal fun AuthInputCard(
    jsonText: String,
    onJsonTextChanged: (String) -> Unit
) {
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
                onValueChange = onJsonTextChanged,
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
}

@androidx.compose.ui.tooling.preview.Preview
@Composable
private fun AuthInputCardPreview() {
    ClanTheme {
        AuthInputCard(
            jsonText = "",
            onJsonTextChanged = {}
        )
    }
}
