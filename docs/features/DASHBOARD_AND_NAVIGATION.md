# 🧭 Dashboard & Navigation — Feature Specification

This document outlines the architecture for the main dashboard UI, 3D animated drawer, bottom navigation, and how the `NavDisplay` manages routing.

---

## 🖼️ 3D Drawer Animation Concept

The Dashboard uses a custom layered 3D-effect drawer (inspired by the `ken` repo), which avoids standard `ModalNavigationDrawer` limitations.

1. **Layer 1 (Bottom): `ClanDrawer`**
   - The side menu that sits quietly in the background, shifted slightly to the left.
2. **Layer 2 (Middle): `Main Content Wrapper`**
   - Contains the active tab (Village, Wars, Lab).
   - Translates to the right, scales down, and rounds its corners as the drawer opens using a `graphicsLayer` modifier and a `draggable` state tracking translation.
3. **Layer 3 (Top): `ClanBottomBar`**
   - The floating navigation pill.
   - Tied to the drawer's animation state: it offsets downwards (off-screen) as the drawer opens.

---

## 🏛️ Navigation Architecture

The app uses `androidx.navigation3.ui.NavDisplay` for root-level navigation, relying on a state-driven approach instead of XML graphs or heavy compose nested nav-hosts.

### Root Level Navigation (`NavDisplayProvider`)
- **`SplashRoute`**: The initial route. Evaluates auth state and automatically redirects.
- **`AuthRoute`**: Handles JSON importing.
- **`MainRoute`**: The authenticated dashboard wrapper.

### Tab Navigation (Internal State)
Within the `MainRoute` (`ClanDashboardWrapper`), navigation between **Village**, **Wars**, and **Lab** is managed via a simple `remember { mutableStateOf(DashboardTab.VILLAGE) }`. This prevents bloated backstacks for bottom navigation tabs.

---

## 📦 Key Components

| Component | Responsibility |
| :--- | :--- |
| `ClanNavDisplay` | Root level navigator connecting Splash, Auth, and Main. |
| `ClanDashboardWrapper` | The animation engine for the 3D drawer and tab state manager. |
| `ClanDashboardScreen` | The primary UI for the Village tab. |
| `ClanDrawer` | Sidebar menu containing Profile, Settings, and Logout. |
| `ClanBottomBar` | Floating pill-shaped navigation for switching tabs. |

---

## 📁 Key File Locations

- **Navigation**: `presentation/navigation/`
- **Dashboard UI**: `presentation/screens/main/`
- **Drawer/BottomBar**: `presentation/screens/main/components/`
