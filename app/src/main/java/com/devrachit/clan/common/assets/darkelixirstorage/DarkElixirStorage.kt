package com.devrachit.clan.common.assets.darkelixirstorage

import com.devrachit.clan.common.assets.core.BaseAsset

/**
 * Dark Elixir Storage image assets (Entity ID: 7, Levels: 1..12).
 *
 * Implements [BaseAsset] to provide URL generation, bracket indexing,
 * and collection accessors for all 12 Dark Elixir Storage levels.
 *
 * Usage:
 * ```
 * val url = DarkElixirStorage[12]    // "https://www.clash.ninja/images/entities/7_12.png"
 * val list = DarkElixirStorage.all   // List of all 12 URLs
 * ```
 */
object DarkElixirStorage : BaseAsset {
    override val entityId: Int = 7
    override val maxLevel: Int = 12
}
