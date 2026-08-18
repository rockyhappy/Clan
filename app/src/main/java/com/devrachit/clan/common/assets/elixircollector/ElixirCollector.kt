package com.devrachit.clan.common.assets.elixircollector

import com.devrachit.clan.common.assets.core.BaseAsset

/**
 * Elixir Collector image assets (Entity ID: 3, Levels: 1..16).
 *
 * Implements [BaseAsset] to provide URL generation, bracket indexing,
 * and collection accessors for all 16 Elixir Collector levels.
 *
 * Usage:
 * ```
 * val url = ElixirCollector[16]    // "https://www.clash.ninja/images/entities/3_16.png"
 * val list = ElixirCollector.all   // List of all 16 URLs
 * ```
 */
object ElixirCollector : BaseAsset {
    override val entityId: Int = 3
    override val maxLevel: Int = 16
}
