# ✍️ Typography Guide & Custom Text Suite

The Clan app uses **5 distinct Google Fonts** matching Clash of Clans visual hierarchy, packaged into semantic `<ClanText>` composables.

---

## 🔤 1. Font Family Roles

| Font Name | Style Archetype | CoC Role | Accessible Via |
| :--- | :--- | :--- | :--- |
| **`Lilita One`** | Chunky Cartoon Sans | **Supercell Magic match** (Numbers, Stats, Badges, Splash Titles) | `ClanTheme.fonts.gameDisplay` |
| **`Luckiest Guy`** | Heavy Comic Display | **War Banners & Alerts** (Clan War titles, victory banners) | `ClanTheme.fonts.warBanner` |
| **`Fredoka`** | Geometric Rounded Sans | **Headings & Titles** (Card headers, modal titles) | `ClanTheme.fonts.heading` |
| **`Nunito`** | Warm Rounded Sans | **Body & Labels** (Descriptions, timers, settings, buttons) | `ClanTheme.fonts.body` |
| **`MedievalSharp`** | Gothic Medieval Serif | **Lore & Fantasy** (Clan descriptions, perks, milestones) | `ClanTheme.fonts.fantasyLore` |

---

## 🧱 2. Custom Text Components Reference

Located in [`app/src/main/java/com/devrachit/clan/presentation/components/text/ClanText.kt`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/presentation/components/text/ClanText.kt):

### `<ClanDisplayText>`
```kotlin
ClanDisplayText(
    text = AppStrings.App.TITLE,
    fontSize = 36.sp,
    color = ClanTheme.colors.primary
)
```

### `<ClanHeadingText>`
```kotlin
ClanHeadingText(
    text = AppStrings.Dashboard.VILLAGE_RESOURCES_TITLE,
    fontSize = 22.sp
)
```

### `<ClanTitleText>`
```kotlin
ClanTitleText(
    text = AppStrings.Resources.GOLD,
    fontSize = 14.sp
)
```

### `<ClanBodyText>`
```kotlin
ClanBodyText(
    text = AppStrings.Dashboard.WAR_STATUS_SUBTITLE,
    fontSize = 13.sp
)
```

### `<ClanLabelText>`
```kotlin
ClanLabelText(
    text = AppStrings.Common.SKIP,
    fontSize = 14.sp
)
```

### `<ClanResourceText>`
```kotlin
ClanResourceText(
    text = AppStrings.Resources.SAMPLE_GOLD_COUNT,
    color = ClanTheme.resources.gold
)
```

### `<ClanWarBannerText>`
```kotlin
ClanWarBannerText(
    text = AppStrings.Dashboard.WAR_LEAGUE_TITLE,
    fontSize = 20.sp
)
```

### `<ClanLoreText>`
```kotlin
ClanLoreText(
    text = "Ancient legends tell of a village...",
    fontSize = 14.sp
)
```
