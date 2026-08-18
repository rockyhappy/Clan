# 📜 BaseUseCase Contracts Reference

This document provides in-depth technical documentation for all 6 base UseCase contracts defined in `com.devrachit.clan.domain.usecase.core.BaseUseCase`.

---

## 1. `BaseUseCase<in In, out Out>`
- **Behavior**: Synchronous computation with parameter input.
- **Method**: `operator fun invoke(params: In): Out`
- **Dispatcher Scope**: Executed synchronously on the calling thread.
- **When to use**: Pure business math, algorithm evaluations, input validation, string transformations, loot calculations.
- **Example**:
  ```kotlin
  class CalculateTroopTrainTimeUseCase : BaseUseCase<TroopList, Long> {
      override operator fun invoke(params: TroopList): Long = params.sumOf { it.trainSeconds }
  }
  ```

---

## 2. `BaseNoParamsUseCase<out Out>`
- **Behavior**: Synchronous computation with no parameter input.
- **Method**: `operator fun invoke(): Out`
- **When to use**: Static domain configuration retrieval, game version constant mapping, default model generation.
- **Example**:
  ```kotlin
  class GetDefaultVillageConfigUseCase : BaseNoParamsUseCase<VillageConfig> {
      override operator fun invoke(): VillageConfig = VillageConfig(maxBuilders = 6)
  }
  ```

---

## 3. `BaseSuspendUseCase<in In, out Out>`
- **Behavior**: Asynchronous one-shot suspend operation with parameter input.
- **Method**: `suspend operator fun invoke(params: In): Out`
- **When to use**: Database writes/reads, network requests with input, Preferences DataStore updates, player tag lookups.
- **Example**:
  ```kotlin
  class SetThemeModeUseCase(
      private val themeRepository: ThemeRepository
  ) : BaseSuspendUseCase<ThemeMode, Unit> {
      override suspend operator fun invoke(params: ThemeMode) {
          themeRepository.setThemeMode(params)
      }
  }
  ```

---

## 4. `BaseNoParamsSuspendUseCase<out Out>`
- **Behavior**: Asynchronous one-shot suspend operation with no parameters.
- **Method**: `suspend operator fun invoke(): Out`
- **When to use**: Cache clearing, global sync triggers, full data resets.
- **Example**:
  ```kotlin
  class ClearAllUserDataUseCase(
      private val userRepository: UserRepository
  ) : BaseNoParamsSuspendUseCase<Unit> {
      override suspend operator fun invoke() {
          userRepository.clearAllData()
      }
  }
  ```

---

## 5. `BaseFlowUseCase<in In, out Out>`
- **Behavior**: Reactive data stream returning Kotlin `Flow` parameterized by input.
- **Method**: `operator fun invoke(params: In): Flow<Out>`
- **When to use**: Observing dynamic database queries by entity ID, specific player profiles, single building upgrade progress.
- **Example**:
  ```kotlin
  class ObserveUpgradeQueueUseCase(
      private val builderRepository: BuilderRepository
  ) : BaseFlowUseCase<String, List<BuildingUpgrade>> {
      override operator fun invoke(params: String): Flow<List<BuildingUpgrade>> {
          return builderRepository.observeQueueForVillage(params)
      }
  }
  ```

---

## 6. `BaseNoParamsFlowUseCase<out Out>`
- **Behavior**: Reactive data stream returning Kotlin `Flow` with no parameters.
- **Method**: `operator fun invoke(): Flow<Out>`
- **When to use**: Observing global app theme, active war status, all builders summary stream.
- **Example**:
  ```kotlin
  class GetThemeModeUseCase(
      private val themeRepository: ThemeRepository
  ) : BaseNoParamsFlowUseCase<ThemeMode> {
      override operator fun invoke(): Flow<ThemeMode> = themeRepository.themeMode
  }
  ```
