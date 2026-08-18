package com.devrachit.clan.domain.usecase.village

import com.devrachit.clan.domain.usecase.core.BaseNoParamsFlowUseCase
import kotlinx.coroutines.flow.Flow

/**
 * Example 6: Reactive Flow UseCase with No Parameters.
 * Observes current stored resources across Gold, Elixir, Dark Elixir, and Gems.
 */
data class VillageResources(
    val gold: Long,
    val elixir: Long,
    val darkElixir: Long,
    val gems: Int
)

interface VillageResourceRepositoryMock {
    val resources: Flow<VillageResources>
}

class ObserveVillageResourcesUseCase(
    private val repository: VillageResourceRepositoryMock
) : BaseNoParamsFlowUseCase<VillageResources> {

    override operator fun invoke(): Flow<VillageResources> = repository.resources
}
