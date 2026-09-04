---
name: domain-test-generator
description: >-
  Guide and templates for writing unit tests for Clan domain UseCases, Models, and business logic.
  Use this skill to create unit test suites verifying BaseUseCase implementations and edge cases.
---

# 🧪 Domain Unit Test Generator

This skill guides the creation of clean, fast, isolated unit tests for domain UseCases.

---

## 🎯 Test Standards

- **Framework**: JUnit 4 (`org.junit.Test`, `org.junit.Assert.*`).
- **Location**: `app/src/test/java/com/devrachit/clan/domain/usecase/<feature>/<UseCaseName>Test.kt`.
- **Coroutines Testing**: Use `kotlinx.coroutines.test.runTest` for suspend and flow UseCases.
- **Mocking**: Use simple fake/mock repository implementations or Kotlin interfaces.

---

## 📝 Example: Testing a Suspend UseCase

```kotlin
package com.devrachit.clan.domain.usecase.theme

import com.devrachit.clan.domain.model.ThemeMode
import com.devrachit.clan.domain.repository.ThemeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class SetThemeModeUseCaseTest {

    private val fakeRepo = object : ThemeRepository {
        val currentMode = MutableStateFlow(ThemeMode.SYSTEM)
        override val themeMode: Flow<ThemeMode> = currentMode
        override suspend fun setThemeMode(mode: ThemeMode) {
            currentMode.value = mode
        }
    }

    private val useCase = SetThemeModeUseCase(fakeRepo)

    @Test
    fun invoke_updatesRepositoryWithNewThemeMode() = runTest {
        useCase(ThemeMode.DARK)
        assertEquals(ThemeMode.DARK, fakeRepo.currentMode.value)
    }
}
```

---

## 📝 Example: Testing a Synchronous Math UseCase

```kotlin
package com.devrachit.clan.domain.usecase.village

import org.junit.Assert.assertEquals
import org.junit.Test

class CalculateLootBonusUseCaseTest {

    private val useCase = CalculateLootBonusUseCase()

    @Test
    fun sameTownHall_yieldsFullLoot() {
        val params = CalculateLootBonusUseCase.Params(
            attackerTownHall = 16,
            defenderTownHall = 16,
            availableLoot = 1_000_000L
        )
        val result = useCase(params)
        assertEquals(1_000_000L, result)
    }

    @Test
    fun lowerTownHallDefender_appliesPenalty() {
        val params = CalculateLootBonusUseCase.Params(
            attackerTownHall = 16,
            defenderTownHall = 14,
            availableLoot = 1_000_000L
        )
        val result = useCase(params)
        assertEquals(500_000L, result)
    }
}
```
