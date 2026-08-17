# 📐 Kotlin & Jetpack Compose Coding Standards

This document establishes the code quality, naming conventions, and Compose idioms for the Clan project.

---

## 1. Naming Conventions

| Artifact | Convention | Example |
| :--- | :--- | :--- |
| **Composables** | PascalCase noun or noun-phrase | `ResourceOverviewCard()`, `ClanButton()` |
| **Composables (Stateful Screens)** | PascalCase with `Screen` suffix | `SplashScreen()`, `ClanDashboardScreen()` |
| **ViewModels** | PascalCase with `ViewModel` suffix | `ThemeViewModel`, `VillageTrackerViewModel` |
| **UseCases** | PascalCase with `UseCase` suffix | `GetThemeModeUseCase`, `CalculateLootUseCase` |
| **Repositories (Interface)** | PascalCase with `Repository` suffix | `ThemeRepository`, `VillageRepository` |
| **Repositories (Impl)** | PascalCase with `RepositoryImpl` suffix | `ThemeRepositoryImpl`, `VillageRepositoryImpl` |
| **DataSources** | PascalCase with `DataStore` or `DataSource` suffix | `ThemeDataStore`, `VillageRemoteDataSource` |
| **Constants** | SCREAMING_SNAKE_CASE | `AppStrings.Common.GET_STARTED` |
| **StateFlow Properties** | camelCase noun | `val themeMode: StateFlow<ThemeMode>` |

---

## 2. Compose Best Practices

### A. Parameter Ordering in Composables
Standard order of parameters:
1. Required data parameters (e.g. `text: String`, `item: VillageItem`)
2. `modifier: Modifier = Modifier` (Always have a default value)
3. State / Callbacks (e.g. `onClick: () -> Unit`)
4. Optional styling parameters (e.g. `color: Color = ...`, `variant: ClanButtonVariant = ...`)
5. Trailing content slot (e.g. `content: @Composable () -> Unit`)

### B. Unidirectional Data Flow (UDF)
```kotlin
// ✅ Stateless Composable (Receives state & emits events)
@Composable
fun VillageHeader(
    thLevel: String,
    onUpgradeClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        ClanDisplayText(text = thLevel)
        ClanButton(
            text = AppStrings.Common.UPGRADE,
            onClick = onUpgradeClick,
            variant = ClanButtonVariant.Primary
        )
    }
}
```

### C. Do Not Call Composable Getters Inside DrawScope
Inside `Canvas { ... }` or custom `DrawScope.() -> Unit`, you cannot call `@Composable` functions or `@ReadOnlyComposable` getters (like `ClanTheme.resources.gold`). 
**Pattern**: Resolve colors in the parent composable first, then pass resolved `Color` instances to the drawing function.
