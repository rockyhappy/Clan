# 🎨 Clan Design System & UI Rules

This rule enforces visual styling, typography, colors, and component usage in all Jetpack Compose UI code.

---

## 🚫 1. Absolute Zero Hardcoding Policy

1. **Zero Hardcoded Strings**:
   - Every string must be defined inside [`AppStrings.kt`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/common/constants/AppStrings.kt) (or XML strings if needed for system intents).
   - Format: `AppStrings.<Feature>.<STRING_KEY>`.

2. **Zero Hardcoded Colors & Dimensions**:
   - No `Color(0x...)` in UI composables.
   - Use `ClanTheme.colors`, `ClanTheme.resources`, or `ClanTheme.status`.
   - Spacing: `ClanTheme.spacing` (`extraSmall` 4dp, `small` 8dp, `mediumSmall` 12dp, `medium` 16dp, `large` 24dp, `extraLarge` 32dp, `huge` 48dp).
   - Borders: `ClanTheme.borders` (`thin` 1dp, `regular` 1.5dp, `thick` 2dp, `gameBevel` 3dp).
   - Shapes: `ClanTheme.gameShapes` (`gameButton`, `resourcePill`, `cardContainer`, `modalDialog`, etc.).

---

## ✍️ 2. Semantic Typography Suite

Always use semantic text composables from [`com.devrachit.clan.presentation.components.text.ClanText`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/presentation/components/text/ClanText.kt):

- **`ClanDisplayText`** → Lilita One (Hero splash titles, massive stats, primary headings).
- **`ClanHeadingText`** → Fredoka Bold (Screen headers, section headers).
- **`ClanTitleText`** → Fredoka Medium (Card titles, modal subtitles).
- **`ClanBodyText`** → Nunito (General descriptions, timers, body text).
- **`ClanLabelText`** → Nunito SemiBold (Button labels, badge tags, chips).
- **`ClanResourceText`** → Lilita One (Resource counters: Gold, Elixir, Gems, Medals).
- **`ClanWarBannerText`** → Luckiest Guy (War League headers, attack alerts).
- **`ClanLoreText`** → MedievalSharp (Flavor text, clan perks, historic logs).

---

## 🔘 3. Button Standards

Use [`ClanButton`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/presentation/components/button/ClanButton.kt):
- `ClanButtonVariant.Primary` (Gold primary action)
- `ClanButtonVariant.Success` (Green confirm/ready/attack action)
- `ClanButtonVariant.Secondary` (Surface high action)
- `ClanButtonVariant.Danger` (Red war attack / destructive action)
- `ClanButtonVariant.Outlined` (Transparent outlined action)

---

## 🌗 4. Dual-Theme Parity

All screens, cards, dialogs, and components **MUST** be verified in both:
- **☀️ Day Village (Light Mode)**: Parchment canvas, wood containers, dark high-contrast ink typography.
- **🌙 Night Base (Dark Mode)**: Midnight obsidian canvas, slate cards, luminous neon resource glows.
