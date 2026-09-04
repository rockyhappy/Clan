# 🔐 Authentication — Feature Specification

This document describes the authentication mechanism used to persist and verify a user's identity.

---

## 🧠 Concept

Authentication is **token-based** using Preferences DataStore:
- A **non-empty string** stored in DataStore means the user is **authenticated**.
- An **empty string** (or absent key) means the user is **not authenticated**.

The token can represent a player tag, API key, session ID, or any identifying string.

---

## 🏛️ Architecture Layers

```
┌──────────────────────────────────────────────────────────┐
│                    Presentation Layer                      │
│  ViewModel calls CheckAuthStatusUseCase / AuthenticateUserUseCase  │
└──────────────────────┬───────────────────────────────────┘
                       │
┌──────────────────────▼───────────────────────────────────┐
│                      Domain Layer                         │
│  ┌─────────────────────┐  ┌────────────────────────────┐ │
│  │ CheckAuthStatusUseCase │ │ AuthenticateUserUseCase   │ │
│  │ BaseNoParamsFlowUseCase │ │ BaseSuspendUseCase        │ │
│  │ <Boolean>              │ │ <String, Unit>            │ │
│  └──────────┬──────────┘  └──────────┬─────────────────┘ │
│             │                        │                    │
│  ┌──────────▼────────────────────────▼─────────────────┐ │
│  │              AuthRepository (interface)              │ │
│  │  val authToken: Flow<String>                        │ │
│  │  suspend fun setAuthToken(token: String)            │ │
│  └─────────────────────┬───────────────────────────────┘ │
│                        │                                  │
│  ┌─────────────────────▼───────────────────────────────┐ │
│  │        GetAuthTokenUseCase                          │ │
│  │        BaseNoParamsFlowUseCase<String>               │ │
│  └─────────────────────────────────────────────────────┘ │
└──────────────────────────────────────────────────────────┘
                       │
┌──────────────────────▼───────────────────────────────────┐
│                      Data Layer                           │
│  ┌─────────────────────────────────────────────────────┐ │
│  │     AuthRepositoryImpl : AuthRepository             │ │
│  │     delegates to AuthDataStore                      │ │
│  └──────────┬──────────────────────────────────────────┘ │
│             │                                            │
│  ┌──────────▼──────────────────────────────────────────┐ │
│  │     AuthDataStore                                   │ │
│  │     Preferences DataStore ("auth_prefs")            │ │
│  │     Key: "key_auth_token" (stringPreferencesKey)    │ │
│  └─────────────────────────────────────────────────────┘ │
└──────────────────────────────────────────────────────────┘
```

---

## 📦 Use Cases

| UseCase | Base Contract | Input | Output | Purpose |
| :--- | :--- | :--- | :--- | :--- |
| [`CheckAuthStatusUseCase`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/domain/usecase/auth/CheckAuthStatusUseCase.kt) | `BaseNoParamsFlowUseCase<Boolean>` | — | `Flow<Boolean>` | Continuously emits `true` if a non-empty token exists, `false` otherwise |
| [`AuthenticateUserUseCase`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/domain/usecase/auth/AuthenticateUserUseCase.kt) | `BaseSuspendUseCase<String, Unit>` | `String` (token) | `Unit` | Stores the authentication token |
| [`GetAuthTokenUseCase`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/domain/usecase/auth/GetAuthTokenUseCase.kt) | `BaseNoParamsFlowUseCase<String>` | — | `Flow<String>` | Exposes the raw token value for downstream API calls |

---

## 📁 Key File Locations

- **Domain Repository**: [`AuthRepository.kt`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/domain/repository/AuthRepository.kt)
- **Data Repository**: [`AuthRepositoryImpl.kt`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/data/repository/AuthRepositoryImpl.kt)
- **DataStore**: [`AuthDataStore.kt`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/data/local/datastore/AuthDataStore.kt)
- **Use Cases**: [`domain/usecase/auth/`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/domain/usecase/auth/)

---

## 🔮 Usage Example (ViewModel)

```kotlin
class AuthViewModel(
    private val checkAuthStatusUseCase: CheckAuthStatusUseCase,
    private val authenticateUserUseCase: AuthenticateUserUseCase
) : ViewModel() {

    val isAuthenticated: StateFlow<Boolean> = checkAuthStatusUseCase()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), false)

    fun authenticate(token: String) {
        viewModelScope.launch {
            authenticateUserUseCase(token)
        }
    }
}
```
