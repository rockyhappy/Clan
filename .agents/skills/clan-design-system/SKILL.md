---
name: clan-design-system
description: >-
  Expert guide on using the Clash of Clans design system tokens, typography suite,
  ClanButton, game shapes, gradients, and dual-theme (Day Village / Night Base) system in Jetpack Compose.
---

# 🎨 Clan Design System Guide

This skill provides comprehensive instructions for designing and styling UI composables using the Clan custom design system.

---

## 🔤 1. Typography Suite ([`ClanText.kt`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/presentation/components/text/ClanText.kt))

| Composable | Google Font | Usage |
| :--- | :--- | :--- |
| **`ClanDisplayText`** | *Lilita One* | Large hero headlines, splash screen titles, big statistics |
| **`ClanHeadingText`** | *Fredoka Bold* | Screen headers, card titles, section headers |
| **`ClanTitleText`** | *Fredoka Medium* | Subheadings, card item titles, modal subtitles |
| **`ClanBodyText`** | *Nunito Regular* | Descriptive paragraphs, upgrade timers, instructions |
| **`ClanLabelText`** | *Nunito SemiBold* | Button labels, chip tags, badges |
| **`ClanResourceText`** | *Lilita One* | Gold, Elixir, Dark Elixir, Gem counts |
| **`ClanWarBannerText`** | *Luckiest Guy* | War league announcements, attack banners |
| **`ClanLoreText`** | *MedievalSharp* | Clan perks, fantasy lore, historic logs |

```kotlin
// Example:
ClanHeadingText(
    text = AppStrings.Dashboard.VILLAGE_RESOURCES_TITLE,
    color = ClanTheme.colors.onSurface
)
```

---

## 🔘 2. Button Component ([`ClanButton.kt`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/presentation/components/button/ClanButton.kt))

Variants:
- `ClanButtonVariant.Primary`: Signature Gold 3D game button.
- `ClanButtonVariant.Success`: Green Confirm / Attack / Ready action.
- `ClanButtonVariant.Secondary`: Darker slate / neutral surface button.
- `ClanButtonVariant.Danger`: Red Cancel / War Attack / Delete button.
- `ClanButtonVariant.Outlined`: Transparent bordered button.

```kotlin
ClanButton(
    text = AppStrings.Common.UPGRADE,
    onClick = { viewModel.onUpgrade() },
    variant = ClanButtonVariant.Primary,
    isLoading = uiState.isLoading
)
```

---

## 🪙 3. Game Resource Tokens (`ClanTheme.resources`)

- `ClanTheme.resources.gold`
- `ClanTheme.resources.elixir`
- `ClanTheme.resources.darkElixir`
- `ClanTheme.resources.gems`
- `ClanTheme.resources.builderGold`
- `ClanTheme.resources.builderElixir`
- `ClanTheme.resources.clanCapitalGold`
- `ClanTheme.resources.raidMedals`
- `ClanTheme.resources.trophy`
- `ClanTheme.resources.playerLevel`

---

## 🚦 4. Game Status Tokens (`ClanTheme.status`)

- `ClanTheme.status.upgrading` (Builder / Lab timer active)
- `ClanTheme.status.ready` (Upgrade complete)
- `ClanTheme.status.boosted` (Potion boost active)
- `ClanTheme.status.warAttack` (Active war attack window)
- `ClanTheme.status.warDefense` (Clan defense prep)
- `ClanTheme.status.shield` (Village shield active)
- `ClanTheme.status.maxed` (Max level reached)
- `ClanTheme.status.rushed` (Underleveled warning)

---

## 📐 5. Spacing & Shapes

- Spacing: `ClanTheme.spacing.extraSmall` (4dp), `small` (8dp), `mediumSmall` (12dp), `medium` (16dp), `large` (24dp), `extraLarge` (32dp), `huge` (48dp).
- Shapes: `ClanTheme.gameShapes.gameButton`, `resourcePill`, `cardContainer`, `modalDialog`.
- Borders: `ClanTheme.borders.thin` (1dp), `regular` (1.5dp), `thick` (2dp), `gameBevel` (3dp).

---

## ⚠️ 6. Canvas & DrawScope Rules

**CRITICAL**: Do NOT call `@Composable` or `@ReadOnlyComposable` accessors inside `Canvas { ... }` or `drawBehind { ... }`.
Always resolve colors in the composable body before passing them to drawing lambdas:

```kotlin
// ✅ CORRECT
@Composable
fun VillageIcon(modifier: Modifier = Modifier) {
    val goldColor = ClanTheme.resources.gold
    val woodColor = ClanTheme.colors.outline
    
    Canvas(modifier = modifier.size(48.dp)) {
        drawCircle(color = goldColor)
        drawRect(color = woodColor)
    }
}
```
