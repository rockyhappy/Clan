# 🚀 Splash, Auth & Navigation Flow

This document details the implementation of the 3-page sliding onboarding splash screen and the village import (Auth) screen, connected via Navigation 3.

---

## 📱 User Experience

### 1. Splash Screen
* **Animation**: 3-page `HorizontalPager` with an animated dot indicator.
* **Content**:
  * **Slide 1**: *Clash Helper & Tracker* — Village upgrades overview.
  * **Slide 2**: *Resource & Lab Analytics* — Loot & laboratory timers.
  * **Slide 3**: *War Planning & Clan Sync* — Clan War League coordination.
* **Top Controls**:
  * **☀️/🌙 Theme Switcher**: Toggle between Day Village and Night Base live.
  * **Skip**: Skips the pager and navigates to the Auth screen.
* **Bottom Controls**:
  * Animated expanding dot indicators.
  * **Next / Get Started** button (`ClanButton` in Primary variant).

### 2. Auth Screen (Village Import)
* **Goal**: Allows the user to import their Clash of Clans village state via JSON.
* **Content**:
  * Multiline text field to paste the JSON exported from the game.
  * Collapsible "How to get your village JSON?" instruction guide.
  * "IMPORT VILLAGE" button navigating to `MainActivity`.
* **Top Controls**:
  * **☀️/🌙 Theme Switcher**: Toggle between Day Village and Night Base live.

---

## 🧭 Navigation Setup

The app utilizes **Jetpack Navigation 3 (`androidx.navigation3`)** to manage the flow within the `SplashActivity`.

* **Backstack**: Managed via `remember { mutableStateListOf<Any>(SplashRoute) }`.
* **Display**: `NavDisplay` is used to map `SplashRoute` and `AuthRoute` (defined as `@Serializable` data objects) to their respective composable screens (`SplashScreen` and `AuthScreen`).

---

## 📁 Key File Locations
* **Routes**: [`app/src/main/java/com/devrachit/clan/presentation/navigation/Routes.kt`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/presentation/navigation/Routes.kt)
* **Hosting Activity**: [`app/src/main/java/com/devrachit/clan/presentation/splash/SplashActivity.kt`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/presentation/splash/SplashActivity.kt)
* **Splash Screen Composable**: [`app/src/main/java/com/devrachit/clan/presentation/splash/SplashScreen.kt`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/presentation/splash/SplashScreen.kt)
* **Auth Screen Composable**: [`app/src/main/java/com/devrachit/clan/presentation/auth/AuthScreen.kt`](file:///E:/Nodejs%20Projects/Clan/app/src/main/java/com/devrachit/clan/presentation/auth/AuthScreen.kt)
