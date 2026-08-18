package com.devrachit.clan.common.assets

import com.devrachit.clan.common.assets.darkelixirdrill.DarkElixirDrill
import com.devrachit.clan.common.assets.darkelixirstorage.DarkElixirStorage
import com.devrachit.clan.common.assets.elixircollector.ElixirCollector
import com.devrachit.clan.common.assets.elixirstorage.ElixirStorage
import com.devrachit.clan.common.assets.goldmine.GoldMine
import com.devrachit.clan.common.assets.goldstorage.GoldStorage
import com.devrachit.clan.common.assets.townhall.TownHall

/**
 * Centralized registry for all Clash of Clans entity image assets.
 *
 * Each entity object implements [com.devrachit.clan.common.assets.core.BaseAsset]
 * and is accessible here for convenience.
 */
object Assets {
    val TownHall = com.devrachit.clan.common.assets.townhall.TownHall
    val GoldMine = com.devrachit.clan.common.assets.goldmine.GoldMine
    val ElixirCollector = com.devrachit.clan.common.assets.elixircollector.ElixirCollector
    val ElixirStorage = com.devrachit.clan.common.assets.elixirstorage.ElixirStorage
    val GoldStorage = com.devrachit.clan.common.assets.goldstorage.GoldStorage
    val DarkElixirDrill = com.devrachit.clan.common.assets.darkelixirdrill.DarkElixirDrill
    val DarkElixirStorage = com.devrachit.clan.common.assets.darkelixirstorage.DarkElixirStorage
}
