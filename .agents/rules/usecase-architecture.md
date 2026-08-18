# ⚙️ UseCase Architecture Rules

This rule defines the strict standards for authoring, structuring, and maintaining UseCases in the domain layer.

---

## 🎯 1. Contract Hierarchy

Every domain UseCase **MUST** implement one of the 6 contracts declared in [`com.devrachit.clan.domain.usecase.core.BaseUseCase`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/domain/usecase/core/BaseUseCase.kt):

```kotlin
// 1. Synchronous Computation (Takes params)
interface BaseUseCase<in In, out Out> {
    operator fun invoke(params: In): Out
}

// 2. Synchronous Computation (No params)
interface BaseNoParamsUseCase<out Out> {
    operator fun invoke(): Out
}

// 3. Suspend Operation (Takes params)
interface BaseSuspendUseCase<in In, out Out> {
    suspend operator fun invoke(params: In): Out
}

// 4. Suspend Operation (No params)
interface BaseNoParamsSuspendUseCase<out Out> {
    suspend operator fun invoke(): Out
}

// 5. Reactive Flow Stream (Takes params)
interface BaseFlowUseCase<in In, out Out> {
    operator fun invoke(params: In): Flow<Out>
}

// 6. Reactive Flow Stream (No params)
interface BaseNoParamsFlowUseCase<out Out> {
    operator fun invoke(): Flow<Out>
}
```

---

## 📋 2. Selection Matrix

Choose the appropriate base interface according to the business requirement:

| If your UseCase needs to... | Use Contract | Example |
| :--- | :--- | :--- |
| Calculate loot, troop train time, or format game stats in memory | `BaseUseCase<In, Out>` | `CalculateLootBonusUseCase` |
| Get static default configuration or app build constants | `BaseNoParamsUseCase<Out>` | `GetDefaultVillageConfigUseCase` |
| Save data, execute an API call, or mutate DataStore/DB with input | `BaseSuspendUseCase<In, Out>` | `SetThemeModeUseCase`, `StartUpgradeUseCase` |
| Reset user preferences, refresh all caches, or trigger a clean sync | `BaseNoParamsSuspendUseCase<Out>` | `ResetVillageSettingsUseCase` |
| Observe a specific building queue, player profile stream, or clan war stream by ID | `BaseFlowUseCase<In, Out>` | `ObserveUpgradeQueueUseCase` |
| Observe global app theme, active war list, or local resources stream | `BaseNoParamsFlowUseCase<Out>` | `GetThemeModeUseCase`, `ObserveVillageResourcesUseCase` |

---

## 🔒 3. Mandatory Implementation Constraints

1. **Pure Kotlin Only**:
   - Never import `android.*`, `androidx.*`, or Android `Context` into `domain/` UseCase files.
   - Flow imports must be from `kotlinx.coroutines.flow.Flow`.
2. **Single Responsibility**:
   - One UseCase class per file.
   - Do not bundle multiple unrelated operations into a single class.
3. **Repository Interface Coupling**:
   - Only inject domain repository interfaces (`ThemeRepository`, `VillageRepository`), never concrete implementation classes (`ThemeRepositoryImpl`, `ThemeDataStore`).
4. **Naming Convention**:
   - Must be named in PascalCase ending with `UseCase` suffix (e.g., `GetThemeModeUseCase`, `CalculateLootUseCase`).
5. **Operator `invoke`**:
   - Always implement `operator fun invoke(...)` to enable idiomatic `myUseCase(...)` call syntax in ViewModels.
6. **Immutability**:
   - Input parameter classes and return types must be immutable `data class`, `enum class`, or primitive types.
