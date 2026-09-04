package com.devrachit.clan.domain.usecase.village

import com.devrachit.clan.domain.usecase.core.BaseUseCase

/**
 * Example 1: Synchronous UseCase with Parameters.
 * Calculates estimated loot plunder based on attacker and defender Town Hall levels.
 */
class CalculateLootBonusUseCase : BaseUseCase<CalculateLootBonusUseCase.Params, Long> {

    data class Params(
        val attackerTownHall: Int,
        val defenderTownHall: Int,
        val availableLoot: Long
    )

    override operator fun invoke(params: Params): Long {
        val diff = params.defenderTownHall - params.attackerTownHall
        val penaltyMultiplier = when {
            diff >= 0 -> 1.0
            diff == -1 -> 0.8
            diff == -2 -> 0.5
            diff == -3 -> 0.25
            else -> 0.05
        }
        return (params.availableLoot * penaltyMultiplier).toLong()
    }
}
