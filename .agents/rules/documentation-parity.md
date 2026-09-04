# 📝 Compulsory Architecture & Design Documentation Updates

This rule mandates that all code, feature, and architectural changes must be synchronized with the project's documentation in `docs/`.

---

## 🎯 Mandatory Documentation Protocol

Whenever you write or modify code in this project, you **MUST** update the corresponding documentation files before completing your task.

---

## 📂 Mapping: Code Changes ➔ Required Documentation Updates

| If you modify or add... | You MUST update... |
| :--- | :--- |
| **New Domain Model, Repository, or UseCase** | 1. [`docs/architecture/CLEAN_ARCHITECTURE.md`](file:///E:/Nodejs%20Projects/Clan/docs/architecture/CLEAN_ARCHITECTURE.md)<br>2. Relevant feature document in `docs/features/<FEATURE>.md`<br>3. [`docs/README.md`](file:///E:/Nodejs%20Projects/Clan/docs/README.md) |
| **New Persistence / DataStore / Room / Cache** | 1. [`docs/architecture/DATASTORE_PERSISTENCE.md`](file:///E:/Nodejs%20Projects/Clan/docs/architecture/DATASTORE_PERSISTENCE.md)<br>2. [`docs/architecture/CLEAN_ARCHITECTURE.md`](file:///E:/Nodejs%20Projects/Clan/docs/architecture/CLEAN_ARCHITECTURE.md) |
| **New Compose Component / Button / Text** | 1. [`docs/design_system/COMPONENTS.md`](file:///E:/Nodejs%20Projects/Clan/docs/design_system/COMPONENTS.md)<br>2. [`docs/design_system/TYPOGRAPHY_GUIDE.md`](file:///E:/Nodejs%20Projects/Clan/docs/design_system/TYPOGRAPHY_GUIDE.md) |
| **New Color Token, Status, or Game Resource** | 1. [`docs/design_system/COLOR_SYSTEM.md`](file:///E:/Nodejs%20Projects/Clan/docs/design_system/COLOR_SYSTEM.md)<br>2. [`docs/design_system/DESIGN_TOKENS.md`](file:///E:/Nodejs%20Projects/Clan/docs/design_system/DESIGN_TOKENS.md) |
| **New Feature Vertical (e.g. War Planner, Lab, Builder)** | 1. Create [`docs/features/<FEATURE_NAME>.md`](file:///E:/Nodejs%20Projects/Clan/docs/features/)<br>2. Register in [`docs/README.md`](file:///E:/Nodejs%20Projects/Clan/docs/README.md) |

---

## 🚫 Enforcement

- Code submissions without matching documentation updates are considered incomplete and non-compliant.
- Ensure all file paths referenced in docs use correct relative markdown links or clickable `file:///` URLs.
