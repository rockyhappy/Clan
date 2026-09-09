# 🔐 Authentication — Feature Specification

This document describes the authentication mechanism used to persist and verify a user's identity and village data.

---

## 🧠 Concept

Authentication is **token-based** using Preferences DataStore combined with **local storage**:
- A user is **authenticated** if and only if a **non-empty string** is stored in DataStore AND the `village_data.json` file is present in internal storage.
- A user is **not authenticated** if either the token is missing or the village data file is missing.

The token currently represents the player's tag extracted from the village JSON data.

---

## 🏛️ Architecture Layers

```
┌──────────────────────────────────────────────────────────┐
│                    Presentation Layer                      │
│  ViewModels call Auth / Logout UseCases                    │
└──────────────────────┬───────────────────────────────────┘
                       │
┌──────────────────────▼───────────────────────────────────┐
│                      Domain Layer                         │
│  ┌─────────────────────┐  ┌────────────────────────────┐ │
│  │ CheckAuthStatusUseCase │ │   ImportVillageUseCase      │ │
│  │ BaseNoParamsFlowUseCase │ │ BaseSuspendUseCase        │ │
│  │ <Boolean>              │ │ <String, Boolean>         │ │
│  └──────────┬──────────┘  └──────────┬─────────────────┘ │
│             │                        │                    │
│             │                        │  ┌───────────────┐ │
│             │                        │  │ LogoutUseCase │ │
│             │                        │  └──────┬────────┘ │
│             │                        │         │          │
│  ┌──────────▼────────────────────────▼─────────▼───────┐ │
│  │    AuthRepository & VillageRepository (interfaces)  │ │
│  └─────────────────────┬───────────────────────────────┘ │
│                        │                                  │
└──────────────────────────────────────────────────────────┘
                       │
┌──────────────────────▼───────────────────────────────────┐
│                      Data Layer                           │
│  ┌─────────────────────────────────────────────────────┐ │
│  │     AuthRepositoryImpl : AuthRepository             │ │
│  │     delegates to AuthDataStore                      │ │
│  │                                                     │ │
│  │     VillageRepositoryImpl : VillageRepository       │ │
│  │     delegates to File Storage                       │ │
│  └─────────────────────────────────────────────────────┘ │
└──────────────────────────────────────────────────────────┘
```

---

## 📦 Use Cases

| UseCase | Base Contract | Input | Output | Purpose |
| :--- | :--- | :--- | :--- | :--- |
| `CheckAuthStatusUseCase` | `BaseNoParamsFlowUseCase<Boolean>` | — | `Flow<Boolean>` | Continuously emits `true` if a non-empty token exists AND the village JSON file is present, `false` otherwise |
| `ImportVillageUseCase` | `BaseSuspendUseCase<String, Boolean>` | `String` (JSON) | `Boolean` | Parses the village JSON, stores the tag in DataStore, saves the JSON to file, and returns true on success |
| `LogoutUseCase` | `BaseNoParamsSuspendUseCase<Unit>` | — | `Unit` | Clears the auth token from DataStore and deletes the village JSON file |
| `GetAuthTokenUseCase` | `BaseNoParamsFlowUseCase<String>` | — | `Flow<String>` | Exposes the raw token value for downstream API calls |

---

## 📁 Key File Locations

- **Domain Repositories**: `AuthRepository.kt`, `VillageRepository.kt`
- **Data Repositories**: `AuthRepositoryImpl.kt`, `VillageRepositoryImpl.kt`
- **DataStore**: `AuthDataStore.kt`
- **Use Cases**: `domain/usecase/auth/`
