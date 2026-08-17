# 💾 DataStore Preferences & Persistence Architecture

This document details how persistent state (such as user theme selection and onboarding status) is saved and read.

---

## 🔄 End-to-End Flow

```
UI (Toggle Action)
       │
       ▼
ThemeViewModel.toggleTheme(isSystemDark)
       │
       ▼
ToggleThemeModeUseCase.invoke(isSystemDark)
       │
       ▼
ThemeRepository.setThemeMode(mode)
       │
       ▼
ThemeDataStore.saveThemeMode(mode) ──► Preferences DataStore (Disk)
                                              │
                                              ▼
ThemeDataStore.themeModeFlow ◄────────────────┘
       │
       ▼
ThemeRepositoryImpl.themeMode
       │
       ▼
GetThemeModeUseCase.invoke()
       │
       ▼
ThemeViewModel.themeMode (StateFlow)
       │
       ▼
Compose Tree Re-composes with updated ClanTheme(darkTheme = isDark)
```

---

## 📁 Key File Locations
- **Data Source**: [`app/src/main/java/com/devrachit/clan/data/local/datastore/ThemeDataStore.kt`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/data/local/datastore/ThemeDataStore.kt)
- **Repository Interface**: [`app/src/main/java/com/devrachit/clan/domain/repository/ThemeRepository.kt`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/domain/repository/ThemeRepository.kt)
- **Repository Implementation**: [`app/src/main/java/com/devrachit/clan/data/repository/ThemeRepositoryImpl.kt`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/data/repository/ThemeRepositoryImpl.kt)
- **Use Cases**: [`app/src/main/java/com/devrachit/clan/domain/usecase/theme/`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/domain/usecase/theme/)
- **ViewModel**: [`app/src/main/java/com/devrachit/clan/presentation/theme/ThemeViewModel.kt`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/presentation/theme/ThemeViewModel.kt)
