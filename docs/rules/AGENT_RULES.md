# 📜 Core Rules for AI Agents & Developers

This document defines the **7 non-negotiable rules** that every AI coding agent and human developer **MUST** adhere to when contributing to the Clan codebase.

---

## 🚫 1. ZERO HARDCODED STRINGS (Mandatory)
- **Rule**: NEVER write raw string literals directly inside UI composables (e.g. `Text("Upgrade")`).
- **Standard**: All user-facing, subtitle, label, and system strings **MUST** be defined in:
  - [`AppStrings.kt`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/common/constants/AppStrings.kt) (`com.devrachit.clan.common.constants.AppStrings`)
  - Or `res/values/strings.xml` when required for manifest/system intents.
- **Example**:
  ```kotlin
  // ❌ WRONG
  Text(text = "Plan War Attack")

  // ✅ CORRECT
  ClanHeadingText(text = AppStrings.Dashboard.PLAN_WAR_ATTACK_CTA)
  ```

---

## 🎨 2. ZERO HARDCODED COLORS OR DIMENSIONS (Mandatory)
- **Rule**: NEVER write raw hex codes (`Color(0xFF...)`) or arbitrary pixel values in components.
- **Standard**:
  - Colors **MUST** be retrieved from `ClanTheme.colors`, `ClanTheme.resources`, or `ClanTheme.status`.
  - Spacing **MUST** be retrieved from `ClanTheme.spacing` (`extraSmall`, `small`, `medium`, `large`, `extraLarge`, `huge`).
  - Borders **MUST** be retrieved from `ClanTheme.borders` (`thin`, `regular`, `thick`, `gameBevel`).
  - Shapes **MUST** be retrieved from `ClanTheme.gameShapes` (`gameButton`, `resourcePill`, `cardContainer`, `modalDialog`, etc.).
- **Example**:
  ```kotlin
  // ❌ WRONG
  Modifier.background(Color(0xFFF5B800)).padding(16.dp)

  // ✅ CORRECT
  Modifier.background(ClanTheme.resources.gold).padding(ClanTheme.spacing.medium)
  ```

---

## ✍️ 3. USE SEMANTIC CUSTOM TEXT COMPONENTS (Mandatory)
- **Rule**: Avoid calling generic `Text()` with ad-hoc fonts or styles.
- **Standard**: Use the dedicated custom text components from [`presentation/components/text/ClanText.kt`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/presentation/components/text/ClanText.kt):
  - **`ClanDisplayText`**: Hero headlines, splash titles, big badges (*Lilita One*).
  - **`ClanHeadingText`**: Screen headers, section headers (*Fredoka Bold*).
  - **`ClanTitleText`**: Card titles, modal subtitles (*Fredoka Medium*).
  - **`ClanBodyText`**: Descriptive text, upgrade timers, explanations (*Nunito*).
  - **`ClanLabelText`**: Small tags, button captions, chip labels (*Nunito SemiBold*).
  - **`ClanResourceText`**: Numeric stats, resource counts (*Lilita One*).
  - **`ClanWarBannerText`**: War announcements, action headers (*Luckiest Guy*).
  - **`ClanLoreText`**: Fantasy flavor text, clan perk notes (*MedievalSharp*).

---

## 🔘 4. USE COMMON BUTTON COMPONENTS
- **Rule**: Avoid constructing custom `Button { Box { ... } }` chains with manual borders and gradients.
- **Standard**: Use [`ClanButton`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/presentation/components/button/ClanButton.kt) with its semantic variants:
  - `ClanButtonVariant.Primary` (Gold primary action)
  - `ClanButtonVariant.Success` (Ready / Confirm / Attack)
  - `ClanButtonVariant.Secondary` (Muted surface action)
  - `ClanButtonVariant.Danger` (War Attack / Destructive action)
  - `ClanButtonVariant.Outlined` (Secondary bordered action)

---

## 🌗 5. DUAL-THEME PARITY (Light & Dark Mode)
- **Rule**: Every screen and component MUST look intentional in both **☀️ Day Village (Light)** and **🌙 Night Base (Dark)** modes.
- Never assume a dark background or a white font. Always reference `ClanTheme.colors.background`, `ClanTheme.colors.surface`, `ClanTheme.colors.onSurface`, etc.

---

## 🏗️ 6. CLEAN ARCHITECTURE PACKAGE RULES
- **`common/`**: System constants, `AppStrings`, base utility functions.
- **`domain/`**: Pure business models, UseCases, repository interfaces (Zero Android dependencies).
- **`data/`**: Repositories, DTOs, data sources, API clients, local storage.
- **`presentation/`**: Composables, ViewModels, Themes, Custom UI Components.
  - Sub-packages: `splash/`, `main/`, `components/`, `theme/`.

---

## 🧪 7. BUILD VALIDATION BEFORE TASK COMPLETION
- **Rule**: Any agent modifying Kotlin code or Gradle files **MUST** run `./gradlew assembleDebug` and verify exit code `0` before reporting completion to the user.
