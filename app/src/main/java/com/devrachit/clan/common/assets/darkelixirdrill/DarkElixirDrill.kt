package com.devrachit.clan.common.assets.darkelixirdrill

import com.devrachit.clan.common.assets.core.BaseAsset

/**
 * Dark Elixir Drill image assets (Entity ID: 6, Levels: 1..10).
 *
 * Implements [BaseAsset] to provide URL generation, bracket indexing,
 * and collection accessors for all 10 Dark Elixir Drill levels.
 *
 * Usage:
 * ```
 * val url = DarkElixirDrill[10]    // "https://www.clash.ninja/images/entities/6_10.png"
 * val list = DarkElixirDrill.all   // List of all 10 URLs
 * ```
 */
object DarkElixirDrill : BaseAsset {
    override val entityId: Int = 4
    override val maxLevel: Int = 10
}
