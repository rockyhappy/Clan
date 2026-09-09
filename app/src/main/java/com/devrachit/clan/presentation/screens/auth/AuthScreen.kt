package com.devrachit.clan.presentation.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.devrachit.clan.common.constants.AppStrings
import com.devrachit.clan.presentation.components.button.ClanButton
import com.devrachit.clan.presentation.components.button.ClanButtonVariant
import com.devrachit.clan.presentation.components.text.ClanBodyText
import com.devrachit.clan.presentation.screens.auth.components.AuthHelpSection
import com.devrachit.clan.presentation.screens.auth.components.AuthInputCard
import com.devrachit.clan.presentation.screens.auth.components.AuthThemeToggle
import com.devrachit.clan.presentation.screens.auth.states.AuthUiState
import com.devrachit.clan.presentation.theme.ClanTheme

@Composable
fun AuthScreen(
    onToggleTheme: () -> Unit = {},
    onImport: () -> Unit
) {
    val viewModel: AuthViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState) {
        if (uiState is AuthUiState.Success) {
            onImport()
            viewModel.resetState()
        }
    }

    AuthScreenContent(
        uiState = uiState,
        onToggleTheme = onToggleTheme,
        onImportClicked = { json -> viewModel.importVillage(json) }
    )
}

@Composable
internal fun AuthScreenContent(
    uiState: AuthUiState,
    onToggleTheme: () -> Unit,
    onImportClicked: (String) -> Unit
) {
    var jsonText by remember { mutableStateOf("") }
    var showHowTo by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ClanTheme.colors.background)
    ) {
        AuthThemeToggle(
            onToggleTheme = onToggleTheme,
            modifier = Modifier
                .align(Alignment.TopStart)
                .statusBarsPadding()
                .padding(ClanTheme.spacing.medium)
        )

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

            AuthInputCard(
                jsonText = jsonText,
                onJsonTextChanged = { jsonText = it }
            )

            Spacer(modifier = Modifier.height(ClanTheme.spacing.large))

            AuthHelpSection(
                showHowTo = showHowTo,
                onToggleShowHowTo = { showHowTo = !showHowTo }
            )

            if (uiState is AuthUiState.Error) {
                Spacer(modifier = Modifier.height(ClanTheme.spacing.medium))
                ClanBodyText(
                    text = uiState.message,
                    color = ClanTheme.colors.error
                )
            }

            Spacer(modifier = Modifier.height(ClanTheme.spacing.large))

            if (uiState is AuthUiState.Loading) {
                CircularProgressIndicator(color = ClanTheme.colors.primary)
            } else {
                ClanButton(
                    text = AppStrings.Auth.IMPORT_CTA,
                    onClick = { onImportClicked(jsonText) },
                    variant = ClanButtonVariant.Success
                )
            }
            
            Spacer(modifier = Modifier.height(ClanTheme.spacing.large))
        }
    }
}

@androidx.compose.ui.tooling.preview.Preview(showBackground = true)
@Composable
private fun AuthScreenPreview() {
    ClanTheme {
        AuthScreenContent(
            uiState = AuthUiState.Idle,
            onToggleTheme = {},
            onImportClicked = {}
        )
    }
}
