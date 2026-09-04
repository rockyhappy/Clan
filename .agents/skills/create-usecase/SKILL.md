---
name: create-usecase
description: >-
  Expert guide and generator for creating new domain UseCases in the Clan Android application.
  Use this skill whenever you need to create, refactor, or structure any new domain UseCase,
  select the appropriate BaseUseCase base contract, implement repository bindings, or write unit tests for domain logic.
---

# ⚙️ Create Domain UseCase Skill

This skill provides a standardized runbook and automated tools to implement clean, single-responsibility UseCases in the Clan domain layer adhering to [`com.devrachit.clan.domain.usecase.core.BaseUseCase`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/domain/usecase/core/BaseUseCase.kt).

---

## 🎯 Step 1: Select the Proper Base Contract

Every domain UseCase must implement exactly ONE base interface. Use the following decision tree:

```
Is it a continuous stream of updates over time?
├── YES
│   ├── Takes a Parameter? ──► BaseFlowUseCase<In, Out>
│   └── No Parameter?      ──► BaseNoParamsFlowUseCase<Out>
└── NO (One-time call)
    ├── Is it an Asynchronous / IO / Network / Suspend operation?
    │   ├── Takes a Parameter? ──► BaseSuspendUseCase<In, Out>
    │   └── No Parameter?      ──► BaseNoParamsSuspendUseCase<Out>
    └── Is it a Synchronous In-Memory Computation?
        ├── Takes a Parameter? ──► BaseUseCase<In, Out>
        └── No Parameter?      ──► BaseNoParamsUseCase<Out>
```

For full contract specifications and type signatures, see [Contracts Reference](./references/contracts.md).

---

## 📁 Step 2: Determine Package & File Location

UseCases reside in:
`app/src/main/java/com/devrachit/clan/domain/usecase/<feature_name>/<VerbNoun>UseCase.kt`

Examples:
- `domain/usecase/theme/GetThemeModeUseCase.kt`
- `domain/usecase/theme/SetThemeModeUseCase.kt`
- `domain/usecase/village/GetVillageOverviewUseCase.kt`
- `domain/usecase/builder/StartBuildingUpgradeUseCase.kt`
- `domain/usecase/war/ObserveActiveWarUseCase.kt`

---

## 🏗️ Step 3: Implement the UseCase

### Template for Synchronous UseCase:
```kotlin
package com.devrachit.clan.domain.usecase.village

import com.devrachit.clan.domain.model.LootCalculation
import com.devrachit.clan.domain.usecase.core.BaseUseCase

/**
 * Calculates estimated loot plunder based on attacker and defender Town Hall levels.
 */
class CalculateLootBonusUseCase : BaseUseCase<CalculateLootBonusUseCase.Params, LootCalculation> {

    data class Params(
        val attackerThLevel: Int,
        val defenderThLevel: Int,
        val availableLoot: Long
    )

    override operator fun invoke(params: Params): LootCalculation {
        val penaltyMultiplier = when (params.defenderThLevel - params.attackerThLevel) {
            in 0..Int.MAX_VALUE -> 1.0
            -1 -> 0.8
            -2 -> 0.5
            -3 -> 0.25
            else -> 0.05
        }
        val calculatedLoot = (params.availableLoot * penaltyMultiplier).toLong()
        return LootCalculation(loot = calculatedLoot, multiplier = penaltyMultiplier)
    }
}
```

### Template for Suspend UseCase:
```kotlin
package com.devrachit.clan.domain.usecase.builder

import com.devrachit.clan.domain.model.UpgradeRequest
import com.devrachit.clan.domain.model.UpgradeResult
import com.devrachit.clan.domain.repository.BuilderRepository
import com.devrachit.clan.domain.usecase.core.BaseSuspendUseCase

/**
 * Starts a building upgrade with a free builder.
 */
class StartUpgradeUseCase(
    private val builderRepository: BuilderRepository
) : BaseSuspendUseCase<UpgradeRequest, UpgradeResult> {

    override suspend operator fun invoke(params: UpgradeRequest): UpgradeResult {
        return builderRepository.startUpgrade(params)
    }
}
```

### Template for Reactive Flow UseCase:
```kotlin
package com.devrachit.clan.domain.usecase.war

import com.devrachit.clan.domain.model.ClanWar
import com.devrachit.clan.domain.repository.WarRepository
import com.devrachit.clan.domain.usecase.core.BaseFlowUseCase
import kotlinx.coroutines.flow.Flow

/**
 * Observes live updates for a specific Clan War by war ID.
 */
class ObserveClanWarUseCase(
    private val warRepository: WarRepository
) : BaseFlowUseCase<String, ClanWar> {

    override operator fun invoke(params: String): Flow<ClanWar> {
        return warRepository.observeWar(params)
    }
}
```

---

## ⚡ Step 4: Use the Generator Script (Optional)

You can run the included Python generator script to scaffold a new UseCase:

```bash
python .agents/skills/create-usecase/scripts/generate_usecase.py --feature village --name CalculateLootBonus --contract BaseUseCase --in Params --out LootCalculation
```

See [examples/](./examples/) for complete working implementations of all 6 archetypes.

---

## 🧪 Step 5: Verification

1. Verify pure Kotlin imports (no `android.*`).
2. Run `./gradlew assembleDebug` to verify the build compiles cleanly.
