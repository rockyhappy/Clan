---
name: clash-feature-architect
description: >-
  Comprehensive guide for implementing new Clash of Clans helper and tracking features end-to-end.
  Use this skill whenever you need to add a new game feature vertical (such as Builder Management,
  War Attack Planning, Laboratory Research, Hero Equipment, Capital Raids, or Loot Calculation).
---

# 🏰 Clash Feature Architect Skill

This skill guides the end-to-end creation of new feature verticals in the Clan application following Clean Architecture principles.

---

## 🗺️ Feature Architecture Blueprint

```
Feature Flow:
┌────────────────────────────────────────────────────────┐
│ 1. common/constants/AppStrings.kt                      │
│    Add all text, labels, titles, and timer strings     │
└──────────────────────────┬─────────────────────────────┘
                           │
┌──────────────────────────▼─────────────────────────────┐
│ 2. domain/                                             │
│    • model/<Entity>.kt                                 │
│    • repository/<Feature>Repository.kt                 │
│    • usecase/<feature>/<Action>UseCase.kt              │
└──────────────────────────┬─────────────────────────────┘
                           │
┌──────────────────────────▼─────────────────────────────┐
│ 3. data/                                               │
│    • local/ or remote/ data sources                    │
│    • repository/<Feature>RepositoryImpl.kt             │
└──────────────────────────┬─────────────────────────────┘
                           │
┌──────────────────────────▼─────────────────────────────┐
│ 4. presentation/                                       │
│    • <feature>/<Feature>ViewModel.kt (StateFlow)       │
│    • <feature>/<Feature>Screen.kt (Composables)        │
└──────────────────────────┬─────────────────────────────┘
                           │
┌──────────────────────────▼─────────────────────────────┐
│ 5. Update Documentation in docs/ (MANDATORY)          │
│    • docs/features/<FEATURE_NAME>.md                   │
│    • docs/architecture/CLEAN_ARCHITECTURE.md           │
│    • docs/design_system/ (if new tokens added)         │
│    • docs/README.md                                    │
└──────────────────────────┬─────────────────────────────┘
                           │
┌──────────────────────────▼─────────────────────────────┐
│ 6. ./gradlew assembleDebug                             │
│    Verify clean build with exit code 0                 │
└────────────────────────────────────────────────────────┘

```

---

## 🛠️ Step-by-Step Implementation Guide

### Step 1: Centralize Strings in `AppStrings.kt`
Create a nested object inside [`AppStrings.kt`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/common/constants/AppStrings.kt) for your feature:

```kotlin
object BuilderTracker {
    const val TITLE = "Builder Management"
    const val ACTIVE_BUILDERS_FORMAT = "%d / %d Active"
    const val NO_UPGRADES_IN_PROGRESS = "All builders are currently free!"
    const val BOOST_BUILDERS_CTA = "Activate Builder Potion"
}
```

### Step 2: Create Domain Models & Repository Interface
In `app/src/main/java/com/devrachit/clan/domain/model/`:
```kotlin
data class BuilderTask(
    val id: String,
    val buildingName: String,
    val targetLevel: Int,
    val endTimeMillis: Long,
    val isBoosted: Boolean
)
```

In `app/src/main/java/com/devrachit/clan/domain/repository/`:
```kotlin
interface BuilderRepository {
    val activeTasks: Flow<List<BuilderTask>>
    suspend fun startTask(buildingName: String, level: Int, durationSeconds: Long)
    suspend fun cancelTask(taskId: String)
}
```

### Step 3: Author Domain UseCases
In `app/src/main/java/com/devrachit/clan/domain/usecase/builder/`:
Implement UseCases adhering to `BaseUseCase.kt`:
- `GetActiveBuilderTasksUseCase` : `BaseNoParamsFlowUseCase<List<BuilderTask>>`
- `StartBuilderTaskUseCase` : `BaseSuspendUseCase<StartTaskParams, Unit>`
- `CancelBuilderTaskUseCase` : `BaseSuspendUseCase<String, Unit>`

### Step 4: Implement Data Layer
In `app/src/main/java/com/devrachit/clan/data/repository/BuilderRepositoryImpl.kt`:
Implement the interface using local DataStore or Room database.

### Step 5: Implement Presentation Layer
In `app/src/main/java/com/devrachit/clan/presentation/builder/`:
- Create `BuilderViewModel.kt` exposing `StateFlow<BuilderUiState>`.
- Create `BuilderScreen.kt` using `<ClanHeadingText>`, `<ClanButton>`, `<ClanResourceText>`, and `ClanTheme.spacing`.

### Step 6: Validate Build
Run `./gradlew assembleDebug` in terminal to confirm success.
