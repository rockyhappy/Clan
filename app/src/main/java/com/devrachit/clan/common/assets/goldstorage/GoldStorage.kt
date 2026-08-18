package com.devrachit.clan.common.assets.goldstorage

import com.devrachit.clan.common.assets.core.BaseAsset

/**
 * Gold Storage image assets (Entity ID: 5, Levels: 1..17).
 *
 * Implements [BaseAsset] to provide URL generation, bracket indexing,
 * and collection accessors for all 17 Gold Storage levels.
 *
 * Usage:
 * ```
 * val url = GoldStorage[17]    // "https://www.clash.ninja/images/entities/5_17.png"
 * val list = GoldStorage.all   // List of all 17 URLs
 * ```
 */
object GoldStorage : BaseAsset {
    override val entityId: Int = 5
    override val maxLevel: Int = 17
}
