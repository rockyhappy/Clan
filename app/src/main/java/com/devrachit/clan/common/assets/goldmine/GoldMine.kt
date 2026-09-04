package com.devrachit.clan.common.assets.goldmine

import com.devrachit.clan.common.assets.core.BaseAsset

/**
 * Gold Mine image assets (Entity ID: 2, Levels: 1..16).
 *
 * Implements [BaseAsset] to provide URL generation, bracket indexing,
 * and collection accessors for all 16 Gold Mine levels.
 *
 * Usage:
 * ```
 * val url = GoldMine[14]       // "https://www.clash.ninja/images/entities/2_14.png"
 * val list = GoldMine.all      // List of all 16 URLs
 * val map = GoldMine.byLevel   // Map<Int, String> from 1..16
 * ```
 */
object GoldMine : BaseAsset {

    override val entityId: Int = 2
    override val maxLevel: Int = 16
}
