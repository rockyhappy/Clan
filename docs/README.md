# 📚 Clan App — Documentation Hub

Welcome to the official developer and AI agent documentation for the **Clan (Clash of Clans Helper & Progress Tracker)** Android application.

---

## 🎯 Quick Navigation

### 📜 1. Core Rules & Standards (Mandatory for all AI Agents)
- **[`docs/rules/AGENT_RULES.md`](file:///E:/Nodejs%20Projects/Clan/docs/rules/AGENT_RULES.md)**: **The 7 non-negotiable rules** (Zero hardcoded strings, zero hardcoded colors, use custom components, build verification).
- **[`docs/rules/CODING_STANDARDS.md`](file:///E:/Nodejs%20Projects/Clan/docs/rules/CODING_STANDARDS.md)**: Kotlin & Compose naming conventions, code organization, formatting.

---

### 🏛️ 2. Architecture & Patterns
- **[`docs/architecture/CLEAN_ARCHITECTURE.md`](file:///E:/Nodejs%20Projects/Clan/docs/architecture/CLEAN_ARCHITECTURE.md)**: Clean Architecture layer breakdown (`common`, `domain`, `data`, `presentation`), UDF, state flow.
- **[`docs/architecture/DATASTORE_PERSISTENCE.md`](file:///E:/Nodejs%20Projects/Clan/docs/architecture/DATASTORE_PERSISTENCE.md)**: Preferences DataStore implementation, repository pattern, and use cases.

---

### 🎨 3. Design System & Theme
- **[`docs/design_system/COLOR_SYSTEM.md`](file:///E:/Nodejs%20Projects/Clan/docs/design_system/COLOR_SYSTEM.md)**: Clash of Clans canonical colors (Gold, Elixir, Dark Elixir, Gems, Slate, Parchment, Wood) & Light/Dark Material 3 schemes.
- **[`docs/design_system/TYPOGRAPHY_GUIDE.md`](file:///E:/Nodejs%20Projects/Clan/docs/design_system/TYPOGRAPHY_GUIDE.md)**: The 5 Google Fonts (*Lilita One, Luckiest Guy, Fredoka, Nunito, MedievalSharp*) and semantic `<ClanText>` components.
- **[`docs/design_system/COMPONENTS.md`](file:///E:/Nodejs%20Projects/Clan/docs/design_system/COMPONENTS.md)**: `<ClanButton>`, shapes, badges, progress bars, and spacing scales.
- **[`docs/design_system/DESIGN_TOKENS.md`](file:///E:/Nodejs%20Projects/Clan/docs/design_system/DESIGN_TOKENS.md)**: Extended game domain tokens (`ClanTheme.resources`, `ClanTheme.gradients`, `ClanTheme.status`).

---

### 🚀 4. Feature Specifications
- **[`docs/features/SPLASH_ONBOARDING.md`](file:///E:/Nodejs%20Projects/Clan/docs/features/SPLASH_ONBOARDING.md)**: 3-page sliding onboarding pager with Canvas vector illustrations and live theme switcher.
- **[`docs/features/THEME_MANAGEMENT.md`](file:///E:/Nodejs%20Projects/Clan/docs/features/THEME_MANAGEMENT.md)**: Live Day/Night theme toggling with disk persistence.

---

## ⚡ Golden Rule for Any Agent Modifying Code
> **Before declaring any task complete:**
> Always execute `./gradlew assembleDebug` in the terminal and confirm an exit code of `0`.
