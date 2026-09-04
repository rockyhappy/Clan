# 💾 DataStore Preferences & Persistence Architecture

This document details how persistent state (such as user theme selection, auth tokens, and onboarding status) is saved and read using Jetpack Preferences DataStore.

---

## 🧩 Generic Utility Layer — `DataStoreUtils`

All DataStore read/write operations share the same boilerplate:
- **Read**: catch `IOException` → emit `emptyPreferences()` → `map` key with `runCatching` → fallback to default.
- **Write**: `edit { preferences[key] = value }`.

[`DataStoreUtils.kt`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/common/utils/DataStoreUtils.kt) provides **4 generic extension functions** on `DataStore<Preferences>` that encapsulate this pattern once:

| Function | Purpose | Type Parameters |
| :--- | :--- | :--- |
| `safeValueFlow(key, default)` | Observe a key as a `Flow<T>` directly | `<T>` — raw Preferences type |
| `safeMappedFlow(key, default, mapper)` | Observe + transform to a domain type | `<T, R>` — raw → domain |
| `safeEdit(key, value)` | Write a raw value | `<T>` — raw Preferences type |
| `safeMappedEdit(key, value, serializer)` | Transform domain → raw, then write | `<T, R>` — raw ← domain |

### Benefits
- **Zero duplicated boilerplate** — IOException handling and runCatching written once.
- **Type-safe generics** — compiler enforces key/value type alignment.
- **Adding a new DataStore** requires only declaring keys and calling the utility functions.

---

## 🔄 End-to-End Flow (Theme Example)

```
UI (Toggle Action)
       │
       ▼
ThemeViewModel.toggleTheme(isSystemDark)
       │
       ▼
ToggleThemeModeUseCase.invoke(isSystemDark)
       │
       ▼
ThemeRepository.setThemeMode(mode)
       │
       ▼
ThemeDataStore.saveThemeMode(mode)
  └──► DataStoreUtils.safeMappedEdit(key, mode) { it.name }
         └──► Preferences DataStore (Disk)
                        │
                        ▼
ThemeDataStore.themeModeFlow
  └──► DataStoreUtils.safeMappedFlow(key, SYSTEM) { ThemeMode.valueOf(it) }
       │
       ▼
ThemeRepositoryImpl.themeMode
       │
       ▼
GetThemeModeUseCase.invoke()
       │
       ▼
ThemeViewModel.themeMode (StateFlow)
       │
       ▼
Compose Tree Re-composes with updated ClanTheme(darkTheme = isDark)
```

---

## 📦 Existing DataStore Implementations

### ThemeDataStore
- **File**: [`ThemeDataStore.kt`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/data/local/datastore/ThemeDataStore.kt)
- **Preferences file**: `clan_theme_prefs`
- **Key**: `key_theme_mode` (`stringPreferencesKey`)
- **Uses**: `safeMappedFlow` (String → `ThemeMode`), `safeMappedEdit` (`ThemeMode` → String)

### AuthDataStore
- **File**: [`AuthDataStore.kt`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/data/local/datastore/AuthDataStore.kt)
- **Preferences file**: `auth_prefs`
- **Key**: `key_auth_token` (`stringPreferencesKey`)
- **Uses**: `safeValueFlow` (direct String), `safeEdit` (direct String)

---

## 📁 Key File Locations
- **Generic Utilities**: [`app/src/main/java/com/devrachit/clan/common/utils/DataStoreUtils.kt`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/common/utils/DataStoreUtils.kt)
- **Theme Data Source**: [`app/src/main/java/com/devrachit/clan/data/local/datastore/ThemeDataStore.kt`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/data/local/datastore/ThemeDataStore.kt)
- **Auth Data Source**: [`app/src/main/java/com/devrachit/clan/data/local/datastore/AuthDataStore.kt`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/data/local/datastore/AuthDataStore.kt)
- **Repository Interface**: [`app/src/main/java/com/devrachit/clan/domain/repository/ThemeRepository.kt`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/domain/repository/ThemeRepository.kt)
- **Repository Implementation**: [`app/src/main/java/com/devrachit/clan/data/repository/ThemeRepositoryImpl.kt`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/data/repository/ThemeRepositoryImpl.kt)
- **Use Cases**: [`app/src/main/java/com/devrachit/clan/domain/usecase/theme/`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/domain/usecase/theme/)
- **ViewModel**: [`app/src/main/java/com/devrachit/clan/presentation/theme/ThemeViewModel.kt`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/presentation/theme/ThemeViewModel.kt)

---

## 🆕 Adding a New DataStore

1. Create a new class in `data/local/datastore/` (e.g. `SettingsDataStore`).
2. Declare a companion with `preferencesDataStore(name)` and your preference keys.
3. Use `DataStoreUtils.safeValueFlow` / `safeMappedFlow` for reads.
4. Use `DataStoreUtils.safeEdit` / `safeMappedEdit` for writes.
5. No need to duplicate IOException handling — the utility layer owns it.
