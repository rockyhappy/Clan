package com.devrachit.clan.common.assets.core

/**
 * ══════════════════════════════════════════════════════════════════
 * BASE ASSET ARCHITECTURE
 * ══════════════════════════════════════════════════════════════════
 *
 * All entity asset objects in the assets layer MUST implement this interface.
 *
 * Each implementor defines:
 *  - [entityId]  → The clash.ninja entity ID (e.g. 1 for Town Hall, 2 for Gold Mine)
 *  - [maxLevel]  → The highest upgrade level for this entity
 *  - [minLevel]  → The lowest level (defaults to 1)
 *  - [baseUrl]   → The CDN base path for image URLs
 *
 * The interface provides default implementations for:
 *  - [getByLevel] → Build the full image URL for a given level (clamped)
 *  - [get]        → Bracket-index operator: `TownHall[14]`
 *  - [all]        → Ordered list of all image URLs
 *  - [byLevel]    → Map of level → image URL
 *  - [isValidLevel] → Bounds check
 */
interface BaseAsset {

    val entityId: Int
    val maxLevel: Int
    val minLevel: Int get() = 1
    val baseUrl: String get() = DEFAULT_BASE_URL

    /**
     * Returns the full image URL for the given level, clamped between [minLevel] and [maxLevel].
     */
    fun getByLevel(level: Int): String {
        val clamped = level.coerceIn(minLevel, maxLevel)
        return "$baseUrl${entityId}_$clamped.png"
    }

    /**
     * Bracket-index operator: e.g. `TownHall[14]` or `GoldMine[12]`.
     */
    operator fun get(level: Int): String = getByLevel(level)

    /**
     * Ordered list of all image URLs from [minLevel] through [maxLevel].
     */
    val all: List<String> get() = (minLevel..maxLevel).map { getByLevel(it) }

    /**
     * Map of level to corresponding image URL for every valid level.
     */
    val byLevel: Map<Int, String> get() = (minLevel..maxLevel).associateWith { getByLevel(it) }

    /**
     * Returns true if the given level is within the valid range for this entity.
     */
    fun isValidLevel(level: Int): Boolean = level in minLevel..maxLevel

    companion object {
        const val DEFAULT_BASE_URL = "https://www.clash.ninja/images/entities/"
    }
}