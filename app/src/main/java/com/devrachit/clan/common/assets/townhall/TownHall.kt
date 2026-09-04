package com.devrachit.clan.common.assets.townhall

import com.devrachit.clan.common.assets.core.BaseAsset

/**
 * Town Hall image assets (Entity ID: 1, Levels: 1..18).
 *
 * Implements [BaseAsset] to provide URL generation, bracket indexing,
 * and collection accessors for all 18 Town Hall levels.
 *
 * Usage:
 * ```
 * val url = TownHall[14]       // "https://www.clash.ninja/images/entities/1_14.png"
 * val list = TownHall.all      // List of all 18 URLs
 * val map = TownHall.byLevel   // Map<Int, String> from 1..18
 * ```
 */
object TownHall : BaseAsset {

    override val entityId: Int = 1
    override val maxLevel: Int = 18
}