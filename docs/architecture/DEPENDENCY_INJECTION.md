# 💉 Dependency Injection — Dagger Hilt Architecture

This document describes the Dagger Hilt dependency injection setup used throughout the Clan Android application.

---

## 🎯 Overview

The project uses **Dagger Hilt 2.60.1** with **KSP** (Kotlin Symbol Processing) for compile-time DI code generation. Hilt is the recommended DI framework for Android and integrates seamlessly with Jetpack ViewModels, Compose, and the existing Clean Architecture layers.

---

## 🏛️ Architecture

```
┌─────────────────────────────────────────────────────────────┐
│                    @HiltAndroidApp                           │
│                    ClanApplication                           │
│                                                             │
│  ┌─────────────────────────────────────────────────────────┐ │
│  │              SingletonComponent                         │ │
│  │                                                         │ │
│  │  DataStoreModule    ──► ThemeDataStore, AuthDataStore    │ │
│  │  RepositoryModule   ──► ThemeRepository, AuthRepository │ │
│  │  UseCaseModule      ──► All domain UseCases             │ │
│  └─────────────────────────────────────────────────────────┘ │
│                           │                                  │
│  ┌────────────────────────▼────────────────────────────────┐ │
│  │              ActivityComponent                           │ │
│  │                                                         │ │
│  │  @AndroidEntryPoint SplashActivity                      │ │
│  │  @AndroidEntryPoint MainActivity                        │ │
│  │                                                         │ │
│  │  ┌───────────────────────────────────────────────────┐  │ │
│  │  │          ViewModelComponent                       │  │ │
│  │  │                                                   │  │ │
│  │  │  @HiltViewModel ThemeViewModel                    │  │ │
│  │  │  (Future: AuthViewModel, DashboardViewModel, ...) │  │ │
│  │  └───────────────────────────────────────────────────┘  │ │
│  └─────────────────────────────────────────────────────────┘ │
└─────────────────────────────────────────────────────────────┘
```

---

## 📦 Hilt Modules

### [`DataStoreModule`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/di/DataStoreModule.kt)
- **Scope**: `@Singleton` (critical — DataStore must be single-instance per file)
- **Provides**: `ThemeDataStore`, `AuthDataStore`
- Injects `@ApplicationContext` for DataStore initialization

### [`RepositoryModule`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/di/RepositoryModule.kt)
- **Scope**: `@Singleton`
- **Binds**: `ThemeRepository` → `ThemeRepositoryImpl`, `AuthRepository` → `AuthRepositoryImpl`

### [`UseCaseModule`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/di/UseCaseModule.kt)
- **Scope**: Unscoped (new instance per injection point)
- **Provides**: All domain UseCases (`GetThemeModeUseCase`, `SetThemeModeUseCase`, `ToggleThemeModeUseCase`, `CheckAuthStatusUseCase`, `AuthenticateUserUseCase`, `GetAuthTokenUseCase`)
- UseCases are stateless wrappers — no benefit to singleton scoping

---

## 🔑 Key Annotations Used

| Annotation | Location | Purpose |
| :--- | :--- | :--- |
| `@HiltAndroidApp` | `ClanApplication` | Triggers Hilt code generation, creates application-level component |
| `@AndroidEntryPoint` | `SplashActivity`, `MainActivity` | Enables field injection and Hilt ViewModel support in Activities |
| `@HiltViewModel` | `ThemeViewModel` | Enables `hiltViewModel()` composable function for ViewModel creation |
| `@Inject constructor` | `ThemeViewModel` | Constructor injection — Hilt provides all dependencies automatically |
| `@Module` + `@InstallIn` | DI modules | Declares provider methods and their component scope |
| `@Singleton` | DataStores, Repositories | Ensures single instance across the application lifecycle |
| `@ApplicationContext` | Module providers | Injects the application `Context` for DataStore creation |

---

## 📁 Key File Locations

- **Application**: [`ClanApplication.kt`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/ClanApplication.kt)
- **DI Modules**: [`di/`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/di/)
  - [`DataStoreModule.kt`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/di/DataStoreModule.kt)
  - [`RepositoryModule.kt`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/di/RepositoryModule.kt)
  - [`UseCaseModule.kt`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/di/UseCaseModule.kt)

---

## 🆕 Adding a New Feature with Hilt

1. **Domain**: Create repository interface + UseCase classes.
2. **Data**: Create repository implementation.
3. **DI**: Add `@Provides` methods in the appropriate module (or create a new feature module).
4. **Presentation**: Create `@HiltViewModel` ViewModel with `@Inject constructor`.
5. **Activity**: Ensure the hosting Activity has `@AndroidEntryPoint`.
6. **Compose**: Use `hiltViewModel()` to obtain the ViewModel in composables.

### Example — Adding a new ViewModel

```kotlin
@HiltViewModel
class MyFeatureViewModel @Inject constructor(
    private val myUseCase: MyUseCase
) : ViewModel() {
    // ...
}

// In Composable:
val viewModel: MyFeatureViewModel = hiltViewModel()
```
