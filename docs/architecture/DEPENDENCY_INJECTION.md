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

### [`DataStoreModule`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/di/modules/DataStoreModule.kt)
- **Scope**: `@Singleton` (critical — DataStore must be single-instance per file)
- **Provides**: `ThemeDataStore`, `AuthDataStore`
- Injects `@ApplicationContext` for DataStore initialization
- Uses custom qualifiers `@ThemeStore` and `@AuthStore` to distinctly identify the provided instances.

### [`RepositoryModule`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/di/modules/RepositoryModule.kt)
- **Scope**: `@Singleton` via `@Binds`
- **Binds**: `ThemeRepository` → `ThemeRepositoryImpl`, `AuthRepository` → `AuthRepositoryImpl`
- Uses `@Binds` in an `abstract class` to efficiently link interfaces to implementations without object creation overhead. Note that the implementation classes (`ThemeRepositoryImpl`, `AuthRepositoryImpl`) use `@Inject` constructors and request the specific DataStore dependencies using the custom qualifiers defined in `DataStoreModule`.

### [`UseCaseModule`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/di/modules/UseCaseModule.kt)
- **Scope**: Unscoped (new instance per injection point)
- **Provides**: All domain UseCases (`GetThemeModeUseCase`, `SetThemeModeUseCase`, `ToggleThemeModeUseCase`, `CheckAuthStatusUseCase`, `AuthenticateUserUseCase`, `GetAuthTokenUseCase`)
- UseCases are stateless wrappers — no benefit to singleton scoping. They are provided here to keep the domain layer completely clean of dependency injection annotations.

---

## 🔑 Key Annotations Used

| Annotation | Location | Purpose |
| :--- | :--- | :--- |
| `@HiltAndroidApp` | `ClanApplication` | Triggers Hilt code generation, creates application-level component |
| `@AndroidEntryPoint` | `SplashActivity`, `MainActivity` | Enables field injection and Hilt ViewModel support in Activities |
| `@HiltViewModel` | `ThemeViewModel` | Enables `hiltViewModel()` composable function for ViewModel creation |
| `@Inject constructor` | `ThemeViewModel`, `ThemeRepositoryImpl`, `AuthRepositoryImpl` | Constructor injection — Hilt provides all dependencies automatically |
| `@Module` + `@InstallIn` | DI modules | Declares provider methods and their component scope |
| `@Singleton` | DataStores, Repositories | Ensures single instance across the application lifecycle |
| `@ApplicationContext` | DataStore constructors | Qualifier to inject the application `Context` rather than an Activity context |
| `@Qualifier` | DataStoreModule | Used to differentiate bindings using custom qualifiers like `@ThemeStore` and `@AuthStore` |

---

## 🏷️ When to Use Qualifiers

Qualifiers are used when Hilt needs to provide multiple bindings for the exact same type or to add semantic meaning to specific bindings.

We use the built-in `@ApplicationContext` qualifier to tell Hilt which `Context` to provide to our DataStores.

We also use custom qualifiers in `DataStoreQualifiers.kt`:
```kotlin
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ThemeStore

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthStore
```
These are applied in `DataStoreModule` and then consumed in `ThemeRepositoryImpl` and `AuthRepositoryImpl` to explicitly request those exact bindings.

---

## 📁 Key File Locations

- **Application**: [`ClanApplication.kt`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/ClanApplication.kt)
- **DI Modules**: [`di/modules/`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/di/modules/)
  - [`DataStoreModule.kt`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/di/modules/DataStoreModule.kt)
  - [`RepositoryModule.kt`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/di/modules/RepositoryModule.kt)
  - [`UseCaseModule.kt`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/di/modules/UseCaseModule.kt)
- **Qualifiers**: [`di/qualifiers/`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/di/qualifiers/)
  - [`DataStoreQualifiers.kt`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/di/qualifiers/DataStoreQualifiers.kt)

---

## 🆕 Adding a New Feature with Hilt

1. **Domain**: Create repository interface + UseCase classes.
2. **Data**: Create repository implementation with `@Inject constructor`.
3. **DI**: 
   - Add `@Binds` in `RepositoryModule` for the repository.
   - Add `@Provides` in `UseCaseModule` for the use cases.
4. **Presentation**: Create `@HiltViewModel` ViewModel with `@Inject constructor`.
5. **Activity**: Ensure the hosting Activity has `@AndroidEntryPoint`.
6. **Compose**: Use `hiltViewModel()` to obtain the ViewModel in composables.
