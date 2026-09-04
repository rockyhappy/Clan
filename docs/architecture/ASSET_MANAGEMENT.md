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
| **Barracks** | [`barracks/Barracks.kt`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/common/assets/barracks/Barracks.kt) | `8` | `17` |
| **Dark Barracks** | [`darkbarracks/DarkBarracks.kt`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/common/assets/darkbarracks/DarkBarracks.kt) | `9` | `10` |
| **Army Camp** | [`armycamp/ArmyCamp.kt`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/common/assets/armycamp/ArmyCamp.kt) | `10` | `12` |
| **Spell Factory** | [`spellfactory/SpellFactory.kt`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/common/assets/spellfactory/SpellFactory.kt) | `11` | `7` |
| **Dark Spell Factory** | [`darkspellfactory/DarkSpellFactory.kt`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/common/assets/darkspellfactory/DarkSpellFactory.kt) | `12` | `5` |
| **Blacksmith** | [`blacksmith/Blacksmith.kt`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/common/assets/blacksmith/Blacksmith.kt) | `13` | `9` |
| **Air Defense** | [`airdefense/AirDefense.kt`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/common/assets/airdefense/AirDefense.kt) | `14` | `14` |
| **Air Sweeper** | [`airsweeper/AirSweeper.kt`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/common/assets/airsweeper/AirSweeper.kt) | `15` | `8` |
| **Archer Tower** | [`archertower/ArcherTower.kt`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/common/assets/archertower/ArcherTower.kt) | `16` | `22` |
| **Bomb Tower** | [`bombtower/BombTower.kt`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/common/assets/bombtower/BombTower.kt) | `17` | `11` |
| **Cannon** | [`cannon/Cannon.kt`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/common/assets/cannon/Cannon.kt) | `18` | `22` |
| **Clan Castle** | [`clancastle/ClanCastle.kt`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/common/assets/clancastle/ClanCastle.kt) | `19` | `12` |
| **Eagle Artillery** | [`eagleartillery/EagleArtillery.kt`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/common/assets/eagleartillery/EagleArtillery.kt) | `20` | `7` |
| **Hidden Tesla** | [`hiddentesla/HiddenTesla.kt`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/common/assets/hiddentesla/HiddenTesla.kt) | `21` | `14` |
| **Inferno Tower** | [`infernotower/InfernoTower.kt`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/common/assets/infernotower/InfernoTower.kt) | `22` | `9` |
| **Mortar** | [`mortar/Mortar.kt`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/common/assets/mortar/Mortar.kt) | `23` | `16` |
| **Scattershot** | [`scattershot/Scattershot.kt`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/common/assets/scattershot/Scattershot.kt) | `24` | `4` |
| **X-Bow** | [`xbow/XBow.kt`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/common/assets/xbow/XBow.kt) | `25` | `11` |
| **Air Bomb** | [`airbomb/AirBomb.kt`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/common/assets/airbomb/AirBomb.kt) | `26` | `11` |
| **Bomb** | [`bomb/Bomb.kt`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/common/assets/bomb/Bomb.kt) | `27` | `12` |
| **Giant Bomb** | [`giantbomb/GiantBomb.kt`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/common/assets/giantbomb/GiantBomb.kt) | `28` | `10` |
| **Seeking Air Mine** | [`seekingairmine/SeekingAirMine.kt`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/common/assets/seekingairmine/SeekingAirMine.kt) | `29` | `6` |
| **Spring Trap** | [`springtrap/SpringTrap.kt`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/common/assets/springtrap/SpringTrap.kt) | `30` | `5` |
| **Barbarian** | [`barbarian/Barbarian.kt`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/common/assets/barbarian/Barbarian.kt) | `31` | `12` |
| **Archer** | [`archer/Archer.kt`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/common/assets/archer/Archer.kt) | `32` | `12` |
| **Giant** | [`giant/Giant.kt`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/common/assets/giant/Giant.kt) | `33` | `12` |
| **Goblin** | [`goblin/Goblin.kt`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/common/assets/goblin/Goblin.kt) | `34` | `9` |
| **Wall Breaker** | [`wallbreaker/WallBreaker.kt`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/common/assets/wallbreaker/WallBreaker.kt) | `35` | `12` |
| **Balloon** | [`balloon/Balloon.kt`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/common/assets/balloon/Balloon.kt) | `36` | `11` |
| **Wizard** | [`wizard/Wizard.kt`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/common/assets/wizard/Wizard.kt) | `37` | `12` |
| **Healer** | [`healer/Healer.kt`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/common/assets/healer/Healer.kt) | `38` | `9` |
| **Dragon** | [`dragon/Dragon.kt`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/common/assets/dragon/Dragon.kt) | `39` | `11` |

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
