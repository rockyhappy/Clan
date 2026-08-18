package com.devrachit.clan.common.assets.elixirstorage

import com.devrachit.clan.common.assets.core.BaseAsset

/**
 * Elixir Storage image assets (Entity ID: 4, Levels: 1..17).
 *
 * Implements [BaseAsset] to provide URL generation, bracket indexing,
 * and collection accessors for all 17 Elixir Storage levels.
 *
 * Usage:
 * ```
 * val url = ElixirStorage[17]    // "https://www.clash.ninja/images/entities/4_17.png"
 * val list = ElixirStorage.all   // List of all 17 URLs
 * ```
 */
object ElixirStorage : BaseAsset {
    override val entityId: Int = 6
    override val maxLevel: Int = 17
}
