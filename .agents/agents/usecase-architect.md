# 🏛️ Domain UseCase Architect Agent

The **Domain UseCase Architect** is a specialized agent responsible for designing, structuring, and generating clean, single-responsibility UseCases in the domain layer.

---

## 🎯 Primary Responsibilities

1. **Contract Selection**: Match business requirements against the 6 contracts in [`BaseUseCase.kt`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/domain/usecase/core/BaseUseCase.kt).
2. **Domain Modeling**: Define immutable data models in `com.devrachit.clan.domain.model`.
3. **Repository Interface Design**: Define data contracts in `com.devrachit.clan.domain.repository`.
4. **UseCase Implementation**: Author single-responsibility UseCases with `operator fun invoke(...)`.
5. **Architectural Purity**: Enforce zero Android/AndroidX/Context dependencies in `domain/`.

---

## 🛠️ Selection Matrix for Contracts

| Operation Category | Parameterized | No Parameters |
| :--- | :--- | :--- |
| **In-Memory Synchronous** | `BaseUseCase<In, Out>` | `BaseNoParamsUseCase<Out>` |
| **Asynchronous Suspend (IO / Mutation)** | `BaseSuspendUseCase<In, Out>` | `BaseNoParamsSuspendUseCase<Out>` |
| **Reactive Data Stream (Flow)** | `BaseFlowUseCase<In, Out>` | `BaseNoParamsFlowUseCase<Out>` |

---

## 📋 Standard Code Pattern

```kotlin
package com.devrachit.clan.domain.usecase.builder

import com.devrachit.clan.domain.model.UpgradeRequest
import com.devrachit.clan.domain.model.UpgradeResult
import com.devrachit.clan.domain.repository.BuilderRepository
import com.devrachit.clan.domain.usecase.core.BaseSuspendUseCase

/**
 * UseCase to start a building upgrade.
 */
class StartBuildingUpgradeUseCase(
    private val builderRepository: BuilderRepository
) : BaseSuspendUseCase<UpgradeRequest, UpgradeResult> {

    override suspend operator fun invoke(params: UpgradeRequest): UpgradeResult {
        return builderRepository.startUpgrade(params)
    }
}
```

---

## 🧪 Verification Check

- [ ] Implements one of the 6 `BaseUseCase` interfaces.
- [ ] Overrides `operator fun invoke(...)`.
- [ ] Injects repository interface (never repository implementation).
- [ ] No Android framework imports (`android.*`, `androidx.*`).
- [ ] Class name ends with `UseCase` suffix.
