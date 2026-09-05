# 🏰 Clan Android Project — AI Agent & Developer Rules

This document defines the core architecture, non-negotiable rules, and development guidelines for the **Clan (Clash of Clans Helper & Progress Tracker)** Android project.

---

## 🎯 The 7 Non-Negotiable Rules

1. **🚫 ZERO HARDCODED STRINGS**
   - NEVER write raw string literals anywhere in the codebase (including composables, ViewModels, UseCases, or Repositories).
   - ALL user-facing, subtitle, label, error, filenames, JSON keys, and system strings **MUST** be defined in [`AppStrings.kt`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/common/constants/AppStrings.kt) or `res/values/strings.xml`.

2. **🎨 ZERO HARDCODED COLORS OR DIMENSIONS**
   - NEVER write raw hex codes (`Color(0xFF...)`) or arbitrary pixel values in components.
   - Colors **MUST** come from `ClanTheme.colors`, `ClanTheme.resources`, or `ClanTheme.status`.
   - Spacing **MUST** come from `ClanTheme.spacing` (`extraSmall`, `small`, `medium`, `large`, `extraLarge`, `huge`).
   - Borders **MUST** come from `ClanTheme.borders` (`thin`, `regular`, `thick`, `gameBevel`).
   - Shapes **MUST** come from `ClanTheme.gameShapes` (`gameButton`, `resourcePill`, `cardContainer`, `modalDialog`, etc.).

3. **✍️ USE SEMANTIC CUSTOM TEXT COMPONENTS**
   - Do NOT use generic `Text()` with ad-hoc fonts or styles.
   - Use the semantic suite from [`presentation/components/text/ClanText.kt`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/presentation/components/text/ClanText.kt):
     - `ClanDisplayText` (Lilita One — Hero titles, splash headlines, big numbers)
     - `ClanHeadingText` (Fredoka Bold — Section headers, card titles)
     - `ClanTitleText` (Fredoka Medium — Subheadings, list item titles)
     - `ClanBodyText` (Nunito — Explanatory text, timers, descriptions)
     - `ClanLabelText` (Nunito SemiBold — Chips, badges, button labels)
     - `ClanResourceText` (Lilita One — Currency counters, numeric statistics)
     - `ClanWarBannerText` (Luckiest Guy — War league announcements, battle headers)
     - `ClanLoreText` (MedievalSharp — Clan perks, fantasy lore, notes)

4. **🔘 USE COMMON BUTTON COMPONENTS**
   - Avoid building custom `Button { Box { ... } }` chains with manual borders and gradients.
   - Use [`ClanButton`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/presentation/components/button/ClanButton.kt) with semantic variants: `Primary`, `Success`, `Secondary`, `Danger`, `Outlined`.

5. **🌗 DUAL-THEME PARITY (Light & Dark Mode)**
   - Every screen and component MUST look intentional in both **☀️ Day Village (Light)** and **🌙 Night Base (Dark)** modes.
   - Never assume a dark background or a white font. Always reference `ClanTheme.colors.background`, `ClanTheme.colors.surface`, `ClanTheme.colors.onSurface`, etc.

6. **🏛️ CLEAN ARCHITECTURE COMPLIANCE**
   - **`common/`**: System constants, `AppStrings`, utilities.
   - **`domain/`**: Pure Kotlin models, repository interfaces, and use cases inheriting from `BaseUseCase.kt` (Zero Android dependencies).
   - **`data/`**: Repositories (`*RepositoryImpl`), DataStores, Room database, network clients.
   - **`presentation/`**: Composables, ViewModels, Theme tokens, Custom UI Components.

7. **🧪 BUILD VERIFICATION BEFORE TASK COMPLETION**
   - Any agent modifying code or build configs **MUST** run `./gradlew assembleDebug` and confirm exit code `0` before completing a task.

8. **📝 COMPULSORY ARCHITECTURE & DESIGN DOCUMENTATION UPDATES**
   - After writing or modifying ANY feature, domain model, usecase, repository, or UI composable, you **MUST** update the corresponding architecture, design system, and feature documents in [`docs/`](file:///E:/Nodejs%20Projects/Clan/docs) (`docs/features/`, `docs/architecture/`, `docs/design_system/`, and `docs/README.md`).
   - Code changes without corresponding documentation updates are strictly prohibited.


---

## ⚡ UseCase Implementation Architecture

When authoring new domain UseCases, you **MUST** implement one of the 6 contracts in [`domain.usecase.core.BaseUseCase`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/domain/usecase/core/BaseUseCase.kt):

| Operation Type | With Parameters | No Parameters |
| :--- | :--- | :--- |
| **Synchronous Computation** | `BaseUseCase<In, Out>` | `BaseNoParamsUseCase<Out>` |
| **Asynchronous Suspend** | `BaseSuspendUseCase<In, Out>` | `BaseNoParamsSuspendUseCase<Out>` |
| **Reactive Flow Stream** | `BaseFlowUseCase<In, Out>` | `BaseNoParamsFlowUseCase<Out>` |

### Rules for UseCases:
- **Single Responsibility**: Each UseCase file represents exactly ONE business operation.
- **Operator `invoke`**: Must override `operator fun invoke(...)` matching the base interface.
- **Constructor Injection**: Inject repository interfaces, never concrete implementations.
- **Pure Kotlin**: No Android framework imports in `domain` package.
- **Immutability**: Input parameters and returned models must be immutable data classes.
