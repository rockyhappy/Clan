# 🔘 UI Components & Shape System

This document outlines the standard UI components, shapes, and metrics.

---

## 1. `<ClanButton>` Component

Located in [`app/src/main/java/com/devrachit/clan/presentation/components/button/ClanButton.kt`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/presentation/components/button/ClanButton.kt).

### Variants
| Variant | Visual Role | Default Colors |
| :--- | :--- | :--- |
| **`Primary`** | Gold Main Action | `ClanTheme.colors.primary` (`ClanGold400`/`600`) |
| **`Success`** | Ready / Confirm / Attack | `ClanTheme.status.ready` (`ClanGreen600`) |
| **`Secondary`** | Muted Card Action | `ClanTheme.colors.surfaceContainerHigh` |
| **`Danger`** | War Attack / Destructive | `ClanTheme.colors.error` (`ClanWarRed600`) |
| **`Outlined`** | Transparent Bordered | Border: `ClanTheme.colors.outline` |

### Usage Example
```kotlin
ClanButton(
    text = AppStrings.Common.UPGRADE,
    onClick = { /* handle */ },
    variant = ClanButtonVariant.Primary,
    isLoading = false,
    enabled = true
)
```

---

## 2. Shapes & Dimensions ([`Shape.kt`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/presentation/theme/Shape.kt))

- `ClanTheme.gameShapes.gameButton` → `12.dp` rounded corners
- `ClanTheme.gameShapes.actionButtonLarge` → `16.dp` rounded corners
- `ClanTheme.gameShapes.resourcePill` → Pill capsule (`50%` rounded)
- `ClanTheme.gameShapes.cardContainer` → `16.dp` rounded card
- `ClanTheme.gameShapes.modalDialog` → `24.dp` rounded modal
- `ClanTheme.gameShapes.townHallBadge` → `10.dp` rounded badge
- `ClanTheme.gameShapes.warBanner` → Cut-corner banner

---

## 3. Spacing & Borders ([`DesignTokens.kt`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/presentation/theme/DesignTokens.kt))

### Spacing Scale
- `ClanTheme.spacing.extraSmall` = `4.dp`
- `ClanTheme.spacing.small` = `8.dp`
- `ClanTheme.spacing.mediumSmall` = `12.dp`
- `ClanTheme.spacing.medium` = `16.dp`
- `ClanTheme.spacing.large` = `24.dp`
- `ClanTheme.spacing.extraLarge` = `32.dp`
- `ClanTheme.spacing.huge` = `48.dp`

### Border Thickness
- `ClanTheme.borders.thin` = `1.dp`
- `ClanTheme.borders.regular` = `1.5.dp`
- `ClanTheme.borders.thick` = `2.dp`
- `ClanTheme.borders.gameBevel` = `3.dp`
