package com.devrachit.clan.domain.usecase.village

import com.devrachit.clan.domain.usecase.core.BaseNoParamsUseCase

/**
 * Example 2: Synchronous UseCase with No Parameters.
 * Returns the default initial village configuration.
 */
data class VillageConfig(
    val maxBuilders: Int = 6,
    val maxLaboratorySlots: Int = 1,
    val defaultTownHall: Int = 16
)

class GetDefaultVillageConfigUseCase : BaseNoParamsUseCase<VillageConfig> {

    override operator fun invoke(): VillageConfig {
        return VillageConfig()
    }
}
