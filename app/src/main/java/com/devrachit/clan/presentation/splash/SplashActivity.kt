package com.devrachit.clan.presentation.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.devrachit.clan.presentation.main.MainActivity
import com.devrachit.clan.presentation.theme.ClanTheme
import com.devrachit.clan.presentation.theme.ThemeViewModel

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val themeViewModel: ThemeViewModel = viewModel(
                factory = ThemeViewModel.provideFactory(this@SplashActivity)
            )
            val themeMode by themeViewModel.themeMode.collectAsState()
            val systemDark = isSystemInDarkTheme()
            val isDarkTheme = themeMode.isDark(systemDark)

            ClanTheme(darkTheme = isDarkTheme) {
                SplashScreen(
                    isDarkTheme = isDarkTheme,
                    onToggleTheme = { themeViewModel.toggleTheme(systemDark) },
                    onFinished = {
                        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                        finish()
                    }
                )
            }
        }
    }
}
