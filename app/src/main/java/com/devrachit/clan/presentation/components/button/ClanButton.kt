package com.devrachit.clan.presentation.components.button

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devrachit.clan.presentation.theme.ClanTheme

/**
 * Visual variants for the common ClanButton.
 */
enum class ClanButtonVariant {
    Primary,
    Secondary,
    Success,
    Danger,
    Outlined
}

/**
 * Reusable Common Clan Button Component
 *
 * Provides a clean, modern, tactile button with:
 * - Fluid animations & state handling
 * - Clean rounded corners & elevation
 * - Support for leading icons and loading spinners
 * - Adaptive contrast for Light & Dark mode
 */
@Composable
fun ClanButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    variant: ClanButtonVariant = ClanButtonVariant.Primary,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    height: Dp = 56.dp,
    shape: Shape = RoundedCornerShape(16.dp),
    leadingIcon: (@Composable () -> Unit)? = null
) {
    val containerColor = when (variant) {
        ClanButtonVariant.Primary -> ClanTheme.colors.primary
        ClanButtonVariant.Secondary -> ClanTheme.colors.surfaceContainerHigh
        ClanButtonVariant.Success -> ClanTheme.status.ready
        ClanButtonVariant.Danger -> ClanTheme.colors.error
        ClanButtonVariant.Outlined -> Color.Transparent
    }

    val contentColor = when (variant) {
        ClanButtonVariant.Primary -> ClanTheme.colors.onPrimary
        ClanButtonVariant.Secondary -> ClanTheme.colors.onSurface
        ClanButtonVariant.Success -> ClanTheme.colors.onTertiary
        ClanButtonVariant.Danger -> ClanTheme.colors.onError
        ClanButtonVariant.Outlined -> ClanTheme.colors.primary
    }

    val animatedContainerColor by animateColorAsState(
        targetValue = if (enabled) containerColor else containerColor.copy(alpha = 0.4f),
        label = "btnContainerColor"
    )

    if (variant == ClanButtonVariant.Outlined) {
        OutlinedButton(
            onClick = onClick,
            modifier = modifier
                .fillMaxWidth()
                .height(height),
            enabled = enabled && !isLoading,
            shape = shape,
            border = BorderStroke(
                width = 1.5.dp,
                color = if (enabled) ClanTheme.colors.outline else ClanTheme.colors.outlineVariant
            ),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = contentColor,
                disabledContentColor = contentColor.copy(alpha = 0.4f)
            )
        ) {
            ButtonContent(
                text = text,
                contentColor = contentColor,
                isLoading = isLoading,
                leadingIcon = leadingIcon
            )
        }
    } else {
        Button(
            onClick = onClick,
            modifier = modifier
                .fillMaxWidth()
                .height(height),
            enabled = enabled && !isLoading,
            shape = shape,
            colors = ButtonDefaults.buttonColors(
                containerColor = animatedContainerColor,
                contentColor = contentColor,
                disabledContainerColor = containerColor.copy(alpha = 0.4f),
                disabledContentColor = contentColor.copy(alpha = 0.6f)
            ),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = if (variant == ClanButtonVariant.Primary) 3.dp else 1.dp,
                pressedElevation = 0.dp
            )
        ) {
            ButtonContent(
                text = text,
                contentColor = contentColor,
                isLoading = isLoading,
                leadingIcon = leadingIcon
            )
        }
    }
}

@Composable
private fun ButtonContent(
    text: String,
    contentColor: Color,
    isLoading: Boolean,
    leadingIcon: (@Composable () -> Unit)?
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.size(20.dp),
                color = contentColor,
                strokeWidth = 2.dp
            )
            Spacer(modifier = Modifier.width(10.dp))
        } else if (leadingIcon != null) {
            leadingIcon()
            Spacer(modifier = Modifier.width(8.dp))
        }

        Text(
            text = text,
            fontSize = 16.sp,
            fontFamily = ClanTheme.fonts.heading,
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.3.sp
        )
    }
}
