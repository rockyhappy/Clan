
package com.devrachit.clan.domain.usecase.builder

import com.devrachit.clan.domain.usecase.core.BaseFlowUseCase
import kotlinx.coroutines.flow.Flow

/**
 * Example 5: Reactive Flow UseCase with Parameters.
 * Observes the live upgrade queue for a specific village ID.
 */
data class UpgradeItem(
    val buildingName: String,
    val level: Int,
    val remainingSeconds: Long
)

interface UpgradeQueueRepositoryMock {
    fun observeQueue(villageId: String): Flow<List<UpgradeItem>>
}

class ObserveUpgradeQueueUseCase(
    private val repository: UpgradeQueueRepositoryMock
) : BaseFlowUseCase<String, List<UpgradeItem>> {

    override operator fun invoke(params: String): Flow<List<UpgradeItem>> {
        return repository.observeQueue(params)
    }
}
