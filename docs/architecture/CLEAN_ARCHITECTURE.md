# 🏛️ Clean Architecture & Layer Responsibilities

This document defines the 4-layer architecture used throughout the Clan Android app.

---

## 🏗️ Layer Separation

```
app/src/main/java/com/devrachit/clan/
│
├── common/             # Cross-cutting constants & helpers
├── domain/             # Business rules & entities (Zero Android dependencies)
├── data/               # Persistent storage, API clients, Repository implementations
└── presentation/       # UI Composables, Theme, Custom Components, ViewModels
```

---

## 1. `domain/` Layer (Core Business Logic)
- **Zero Framework Coupling**: No `android.*`, `androidx.*`, or `Context` imports.
- **`model/`**: Pure Kotlin data classes and enums (`ThemeMode`, `TownHall`, `UpgradeQueue`, `WarAttack`).
- **`repository/`**: Interfaces defining data contracts (`ThemeRepository`).
- **`usecase/core/`**: Base contracts (`BaseUseCase`, `BaseNoParamsUseCase`, `BaseSuspendUseCase`, `BaseNoParamsSuspendUseCase`, `BaseFlowUseCase`, `BaseNoParamsFlowUseCase`).
- **`usecase/`**: Single-responsibility domain use cases inheriting from base contracts (e.g., `GetThemeModeUseCase : BaseNoParamsFlowUseCase<ThemeMode>`, `SetThemeModeUseCase : BaseSuspendUseCase<ThemeMode, Unit>`).

---

## 2. `data/` Layer (Data & Infrastructure)
- **`local/`**: Jetpack DataStore Preferences (`ThemeDataStore`), Room Database tables and DAOs.
- **`remote/`**: Clash of Clans official REST API client, network DTOs, interceptors.
- **`repository/`**: Implements domain repository interfaces (`ThemeRepositoryImpl`).

---

## 3. `presentation/` Layer (User Interface)
- **`theme/`**: Color tokens, Typography, Shapes, Design tokens, ThemeViewModel.
- **`components/`**: Reusable component suite (`ClanButton`, `ClanText` suite).
- **`splash/`**: Onboarding & sliding splash flow.
- **`main/`**: Village tracker dashboard.

---

## 4. `common/` Layer (Shared Helpers)
- **`constants/`**: `AppStrings.kt` (Centralized strings), `Constants.kt` (Global system keys).
- **`assets/`**: `BaseAsset` interface (`core/BaseAsset.kt`), entity implementations (`townhall/TownHall.kt`, `goldmine/GoldMine.kt`), registry (`Assets.kt`).
- **`utils/`**: Time calculation, resource formatting helpers.

