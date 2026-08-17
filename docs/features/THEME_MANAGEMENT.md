# 🌓 Theme Management & Day/Night Mode

This document details how the app switches between **☀️ Day Village** (Light) and **🌙 Night Base** (Dark) themes.

---

## 🎨 Theme Specifications

- **Default State**: Aligns with system dark theme on first run (`ThemeMode.SYSTEM`).
- **User Override**: If the user taps the toggle in `SplashScreen` or `MainActivity`, the selection changes to `ThemeMode.LIGHT` or `ThemeMode.DARK` and is persisted into DataStore.
- **Persistence**: Managed through Clean Architecture (`ThemeDataStore` -> `ThemeRepository` -> `Get/Set/ToggleThemeModeUseCase` -> `ThemeViewModel`).

---

## 💻 How to Support Theme Changes in New Screens

Wrap every top-level screen with `ClanTheme(darkTheme = isDarkTheme)`:

```kotlin
val themeViewModel: ThemeViewModel = viewModel(
    factory = ThemeViewModel.provideFactory(LocalContext.current)
)
val themeMode by themeViewModel.themeMode.collectAsState()
val systemDark = isSystemInDarkTheme()
val isDarkTheme = themeMode.isDark(systemDark)

ClanTheme(darkTheme = isDarkTheme) {
    YourScreen(
        isDarkTheme = isDarkTheme,
        onToggleTheme = { themeViewModel.toggleTheme(systemDark) }
    )
}
```
