package com.devrachit.clan.common.constants

/**
 * Centralized String Constants for the Clan Helper App.
 *
 * Rule: NEVER hardcode user-facing or system strings directly in UI composables.
 * Always add or reference them from AppStrings.
 */
object AppStrings {

    object App {
        const val NAME = "Clan"
        const val TITLE = "CLAN HELPER"
        const val TAGLINE = "Track, plan, and dominate your Clan Wars."
        const val DEFAULT_TH_LEVEL = "TH 16"
    }

    object Common {
        const val NEXT = "Next"
        const val GET_STARTED = "Get Started"
        const val SKIP = "Skip"
        const val CANCEL = "Cancel"
        const val UPGRADE = "Upgrade"
        const val STATS = "Stats"
        const val CONFIRM = "Confirm"
        const val SAVE = "Save"
        const val DELETE = "Delete"
        const val EDIT = "Edit"
        const val BACK = "Back"
        const val DONE = "Done"
        const val BUSY = "BUSY"
        const val READY = "READY"
        const val BOOSTED = "BOOSTED"
        const val LOADING = "Loading..."
    }

    object Theme {
        const val DAY_VILLAGE = "☀️ Day Village"
        const val NIGHT_BASE = "🌙 Night Base"
    }

    object Splash {
        const val PAGE_1_TITLE = "Clash Helper & Tracker"
        const val PAGE_1_SUBTITLE = "BUILD & UPGRADE"
        const val PAGE_1_DESC = "Track upgrades, analyze army compositions, manage builder queues, and never lose track of a village upgrade."

        const val PAGE_2_TITLE = "Resource & Lab Analytics"
        const val PAGE_2_SUBTITLE = "MAX EFFICIENCY"
        const val PAGE_2_DESC = "Calculate exact loot requirements, research lab timers, and optimize your dark elixir & hero progression."

        const val PAGE_3_TITLE = "War Planning & Clan Sync"
        const val PAGE_3_SUBTITLE = "CHAMPION LEAGUE"
        const val PAGE_3_DESC = "Coordinate war attacks with clan mates, log enemy base notes, track star counts, and lead your Clan to victory!"

        const val ENTER_VILLAGE_CTA = "ENTER CLAN VILLAGE"
    }

    object Dashboard {
        const val VILLAGE_RESOURCES_TITLE = "Village Resources"
        const val BUILDERS_LAB_TITLE = "Builders & Lab (5/6 Active)"
        const val WAR_LEAGUE_TITLE = "Clan War League"
        const val WAR_STATUS_SUBTITLE = "War Day 4 • Attack Window Open (12h 45m left)"
        const val PLAN_WAR_ATTACK_CTA = "Plan War Attack"
        const val BUTTON_VARIANTS_TITLE = "Common Button Variants"

        // Sample Upgrade Timers
        const val UPGRADE_EAGLE_ARTILLERY = "Eagle Artillery Lv.7"
        const val UPGRADE_EAGLE_TIME = "1d 14h remaining"
        const val UPGRADE_ROOT_RIDER = "Root Rider Lv.3 (Lab)"
        const val UPGRADE_ROOT_RIDER_TIME = "4h 22m remaining"
        const val UPGRADE_GRAND_WARDEN = "Grand Warden Lv.70"
        const val UPGRADE_GRAND_WARDEN_TIME = "2d 08h remaining"
    }

    object Resources {
        const val GOLD = "Gold"
        const val ELIXIR = "Elixir"
        const val DARK_ELIXIR = "Dark Elixir"
        const val GEMS = "Gems"
        const val BUILDER_GOLD = "Builder Gold"
        const val BUILDER_ELIXIR = "Builder Elixir"
        const val CLAN_CAPITAL_GOLD = "Capital Gold"
        const val RAID_MEDALS = "Raid Medals"
        const val LEAGUE_MEDALS = "League Medals"
        const val TROPHIES = "Trophies"

        // Sample Resource Counts
        const val SAMPLE_GOLD_COUNT = "18,450,000 / 22M"
        const val SAMPLE_ELIXIR_COUNT = "14,200,000 / 22M"
        const val SAMPLE_DARK_ELIXIR_COUNT = "310,000 / 350K"
        const val SAMPLE_GEMS_COUNT = "4,820"
    }
}
