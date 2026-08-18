# 🏰 Clan Feature Builder Agent

The **Clan Feature Builder** is a full-stack Android subagent responsible for taking a new Clash of Clans helper/tracker feature from conception to full end-to-end implementation.

---

## 🧭 Layer-by-Layer Execution Workflow

```
1. Common (AppStrings.kt)
       │
       ▼
2. Domain (Model + Repository Interface + BaseUseCase)
       │
       ▼
3. Data (DataStore / Room / API + RepositoryImpl)
       │
       ▼
4. Presentation (ViewModel + StateFlow + ClanTheme UI)
       │
       ▼
5. Documentation Parity (MANDATORY update to docs/ files)
       │
       ▼
6. Build Verification (./gradlew assembleDebug)

```

---

## 📋 Step-by-Step Feature Runbook

### 1. Define Strings in `AppStrings.kt`
Add all user-facing titles, subtitles, button labels, descriptions, and resource names to [`AppStrings.kt`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/common/constants/AppStrings.kt).

### 2. Domain Layer
- Create domain entity in `com.devrachit.clan.domain.model` (e.g. `WarAttackPlan.kt`).
- Create interface in `com.devrachit.clan.domain.repository` (e.g. `WarRepository.kt`).
- Create one or more UseCases in `com.devrachit.clan.domain.usecase.<feature>/` extending `BaseUseCase`.

### 3. Data Layer
- Implement local persistence in `com.devrachit.clan.data.local` or network client in `com.devrachit.clan.data.remote`.
- Implement `*RepositoryImpl.kt` in `com.devrachit.clan.data.repository`.

### 4. Presentation Layer
- Implement `<Feature>ViewModel.kt` exposing immutable `StateFlow<UiState>`.
- Implement `<Feature>Screen.kt` using `<ClanButton>`, `<ClanText>` suite, and `ClanTheme` tokens.

### 5. Build Verification
- Execute `./gradlew assembleDebug` and verify exit code 0.
