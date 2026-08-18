# 📦 Entity Asset Architecture

This document describes the `BaseAsset` interface pattern for Clash of Clans entity image assets, modeled after the `BaseUseCase` interface pattern used in the domain layer.

---

## 🏛️ Interface: `BaseAsset`

Location: [`common/assets/core/BaseAsset.kt`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/common/assets/core/BaseAsset.kt)

Just like `BaseUseCase` defines the contract every UseCase must implement, `BaseAsset` defines the contract every entity asset must implement.

### Contract

```kotlin
interface BaseAsset {
    val entityId: Int                        // clash.ninja entity ID
    val maxLevel: Int                        // highest upgrade level
    val minLevel: Int                        // lowest level (default: 1)
    val baseUrl: String                      // CDN base path (default provided)

    fun getByLevel(level: Int): String       // URL for a level (clamped)
    operator fun get(level: Int): String     // bracket syntax: Asset[14]
    val all: List<String>                    // all URLs ordered
    val byLevel: Map<Int, String>            // level → URL map
    fun isValidLevel(level: Int): Boolean    // bounds check
}
```

All methods except `entityId` and `maxLevel` have default implementations.

---

## 🏗️ Implementing a New Entity Asset

Each entity is a Kotlin `object` that implements `BaseAsset` and provides only `entityId` and `maxLevel`:

```kotlin
object TownHall : BaseAsset {
    override val entityId: Int = 1
    override val maxLevel: Int = 18
}
```

This mirrors how a UseCase implements `BaseUseCase`:
```kotlin
class GetThemeModeUseCase(...) : BaseNoParamsFlowUseCase<ThemeMode> {
    override operator fun invoke(): Flow<ThemeMode> = ...
}
```

---

## 📋 Current Entity Assets

| Entity | Location | Entity ID | Max Level |
| :--- | :--- | :--- | :--- |
| **Town Hall** | [`townhall/TownHall.kt`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/common/assets/townhall/TownHall.kt) | `1` | `18` |
| **Gold Mine** | [`goldmine/GoldMine.kt`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/common/assets/goldmine/GoldMine.kt) | `2` | `16` |
| **Elixir Collector** | [`elixircollector/ElixirCollector.kt`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/common/assets/elixircollector/ElixirCollector.kt) | `3` | `16` |
| **Dark Elixir Drill** | [`darkelixirdrill/DarkElixirDrill.kt`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/common/assets/darkelixirdrill/DarkElixirDrill.kt) | `4` | `10` |
| **Gold Storage** | [`goldstorage/GoldStorage.kt`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/common/assets/goldstorage/GoldStorage.kt) | `5` | `17` |
| **Elixir Storage** | [`elixirstorage/ElixirStorage.kt`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/common/assets/elixirstorage/ElixirStorage.kt) | `6` | `17` |
| **Dark Elixir Storage** | [`darkelixirstorage/DarkElixirStorage.kt`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/common/assets/darkelixirstorage/DarkElixirStorage.kt) | `7` | `12` |

### Registry

All assets are accessible via [`Assets.kt`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/common/assets/Assets.kt):

```kotlin
Assets.TownHall[14]     // "https://www.clash.ninja/images/entities/1_14.png"
Assets.GoldMine[14]     // "https://www.clash.ninja/images/entities/2_14.png"
Assets.TownHall.all     // List of 18 URLs
Assets.GoldMine.byLevel // Map<Int, String> for 1..16
```

---

## 📂 Directory Structure

```
common/assets/
├── core/
│   └── BaseAsset.kt          ← Interface contract
├── townhall/
│   └── TownHall.kt           ← Implements BaseAsset (entityId=1)
├── goldmine/
│   └── GoldMine.kt           ← Implements BaseAsset (entityId=2)
└── Assets.kt                 ← Centralized registry
```
