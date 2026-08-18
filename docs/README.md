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
- **[`docs/architecture/ASSET_MANAGEMENT.md`](file:///E:/Nodejs%20Projects/Clan/docs/architecture/ASSET_MANAGEMENT.md)**: `BaseAsset` interface contract, entity implementations, and asset registry.


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

### 🤖 5. AI Agents, Rules & Skills (`.agents/`)
- **Rules**:
  - [`GEMINI.md`](file:///E:/Nodejs%20Projects/Clan/GEMINI.md) & [`AGENTS.md`](file:///E:/Nodejs%20Projects/Clan/AGENTS.md): Workspace non-negotiable rules.
  - [`.agents/rules/usecase-architecture.md`](file:///E:/Nodejs%20Projects/Clan/.agents/rules/usecase-architecture.md): BaseUseCase contract selection & domain rules.
  - [`.agents/rules/clean-architecture.md`](file:///E:/Nodejs%20Projects/Clan/.agents/rules/clean-architecture.md): Layer boundaries & dependency rules.
  - [`.agents/rules/design-system.md`](file:///E:/Nodejs%20Projects/Clan/.agents/rules/design-system.md): Zero hardcoding & ClanTheme styling rules.
  - [`.agents/rules/documentation-parity.md`](file:///E:/Nodejs%20Projects/Clan/.agents/rules/documentation-parity.md): Mandatory architecture & design docs updates.
  - [`.agents/rules/build-validation.md`](file:///E:/Nodejs%20Projects/Clan/.agents/rules/build-validation.md): Mandatory build verification.

- **Specialized Subagents**:
  - [`.agents/agents/usecase-architect.md`](file:///E:/Nodejs%20Projects/Clan/.agents/agents/usecase-architect.md): Domain UseCase & Model Architect.
  - [`.agents/agents/clan-feature-builder.md`](file:///E:/Nodejs%20Projects/Clan/.agents/agents/clan-feature-builder.md): Full-Stack Feature Engineer.
  - [`.agents/agents/compose-ui-specialist.md`](file:///E:/Nodejs%20Projects/Clan/.agents/agents/compose-ui-specialist.md): Compose & Design System Specialist.
  - [`.agents/agents/build-verifier.md`](file:///E:/Nodejs%20Projects/Clan/.agents/agents/build-verifier.md): Build & Compilation Verifier.
- **Skills**:
  - [`.agents/skills/create-usecase/SKILL.md`](file:///E:/Nodejs%20Projects/Clan/.agents/skills/create-usecase/SKILL.md): UseCase authoring guide & contracts matrix.
  - [`.agents/skills/clash-feature-architect/SKILL.md`](file:///E:/Nodejs%20Projects/Clan/.agents/skills/clash-feature-architect/SKILL.md): End-to-end Clash feature guide.
  - [`.agents/skills/clan-design-system/SKILL.md`](file:///E:/Nodejs%20Projects/Clan/.agents/skills/clan-design-system/SKILL.md): Clash Compose UI & Canvas design guide.
  - [`.agents/skills/validate-build/SKILL.md`](file:///E:/Nodejs%20Projects/Clan/.agents/skills/validate-build/SKILL.md): Gradle build verification & troubleshooting.
  - [`.agents/skills/domain-test-generator/SKILL.md`](file:///E:/Nodejs%20Projects/Clan/.agents/skills/domain-test-generator/SKILL.md): Unit testing guide for UseCases.

---

## ⚡ Golden Rule for Any Agent Modifying Code
> **Before declaring any task complete:**
> Always execute `./gradlew assembleDebug` in the terminal and confirm an exit code of `0`.

