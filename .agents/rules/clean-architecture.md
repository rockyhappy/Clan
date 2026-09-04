# 🏛️ Clean Architecture Layer Rules

This rule enforces layer separation, dependency flow, and package responsibilities for the Clan codebase.

---

## 🧭 Dependency Direction

The Clan project enforces strict **Unidirectional Dependency Flow** towards the core domain:

```
[ presentation/ ] ────► [ domain/ ] ◄──── [ data/ ]
        │                     ▲                 │
        │                     │                 │
        └──────────────► [ common/ ] ◄──────────┘
```

- **`domain/`** depends on **NOTHING** except pure Kotlin and Kotlin Coroutines/Flow standard library.
- **`presentation/`** depends on `domain/` (UseCases, Domain Models) and `common/`.
- **`data/`** depends on `domain/` (implements repository interfaces, maps data models to domain models) and `common/`.
- **`common/`** contains cross-cutting constants, strings, and helpers.

---

## 📦 Layer Standards

### 1. `domain/`
- **Location**: `com.devrachit.clan.domain`
- **Forbidden**: `android.*`, `androidx.*`, `android.content.Context`, `Compose`, `DataStore`, `Room`, `Retrofit`.
- **Contents**:
  - `model/`: Pure Kotlin data classes (e.g. `ThemeMode`, `TownHall`, `BuilderStatus`, `WarAttack`).
  - `repository/`: Abstract interfaces (e.g. `ThemeRepository`, `VillageRepository`, `WarRepository`).
  - `usecase/core/`: The 6 base contracts (`BaseUseCase`, `BaseSuspendUseCase`, `BaseFlowUseCase`, etc.).
  - `usecase/<feature>/`: Single-responsibility use cases.

### 2. `data/`
- **Location**: `com.devrachit.clan.data`
- **Contents**:
  - `local/`: DataStore preferences (`ThemeDataStore`), Room entities, DAOs, local database.
  - `remote/`: Clash of Clans API clients, DTOs, network interceptors.
  - `repository/`: Implementations of domain repository interfaces (e.g. `ThemeRepositoryImpl`).
- **Rule**: Map all local/remote data models into clean domain entities before emitting them to the repository interface.

### 3. `presentation/`
- **Location**: `com.devrachit.clan.presentation`
- **Contents**:
  - `theme/`: Design tokens, colors, typography, shapes, and `ThemeViewModel`.
  - `components/`: Reusable Compose components (`ClanButton`, `ClanText` suite).
  - `<feature>/`: Feature screens (e.g. `splash/`, `main/`, `dashboard/`).
- **Rule**: ViewModels should only communicate with domain UseCases, never directly with DataStores, DAOs, or Repositories.

### 4. `common/`
- **Location**: `com.devrachit.clan.common`
- **Contents**:
  - `constants/AppStrings.kt`: Centralized string catalog.
  - `constants/Constants.kt`: Storage keys, preference keys, system constants.
  - `utils/`: Time, math, and text formatting utilities.
