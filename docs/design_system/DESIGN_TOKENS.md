# 🛡️ Domain Design Tokens Reference

Domain design tokens represent game-specific concepts provided across the UI via `CompositionLocal` and the `ClanTheme` accessor.

---

## 1. Resource Tokens (`ClanTheme.resources`)

| Property | Meaning | Light Mode | Dark Mode |
| :--- | :--- | :--- | :--- |
| `.gold` | Main Gold Currency | `ClanGold400` (`#FFC800`) | `ClanGold300` (`#FFD14D`) |
| `.elixir` | Main Elixir Currency | `ClanElixir400` (`#EA1B8D`) | `ClanElixir300` (`#FF77D1`) |
| `.darkElixir` | Dark Elixir Base | `ClanDarkElixir700` (`#371279`) | `ClanDarkElixir300` (`#A072E6`) |
| `.gems` | Green Emerald Gems | `ClanGreen400` (`#00D856`) | `ClanGreen300` (`#69F0AE`) |
| `.builderGold` | Builder Base Gold | `ClanGold600` | `ClanGold400` |
| `.builderElixir` | Builder Base Elixir | `ClanDarkElixir400` | `ClanDarkElixir300` |
| `.clanCapitalGold` | Capital Raid Gold | `ClanGold700` | `ClanGold400` |
| `.raidMedals` | Raid Medal Cyan | `#00ACC1` | `#26C6DA` |
| `.trophy` | League Trophy Gold | `ClanGold500` | `ClanGold400` |
| `.playerLevel` | Player XP Blue | `#00A8FF` | `#4FC3F7` |

---

## 2. Status Tokens (`ClanTheme.status`)

| Property | Meaning | Color Sample |
| :--- | :--- | :--- |
| `.upgrading` | Builder / Lab busy timer | `ClanShieldBlue500` / `300` |
| `.ready` | Upgrade completed / ready to claim | `ClanGreen600` / `400` |
| `.boosted` | Clock tower / Builder potion boost | `#FF9100` / `#FFB74D` |
| `.warAttack` | Active war attack window open | `ClanWarRed600` / `400` |
| `.warDefense` | Clan defense preparation | `ClanShieldBlue600` / `300` |
| `.shield` | Village guard active | `ClanShieldBlue400` / `300` |
| `.maxed` | Structure at max Town Hall level | `ClanGold500` / `300` |
| `.rushed` | Under-leveled structure warning | `ClanWarRed500` / `400` |

---

## 3. Gradients (`ClanTheme.gradients`)

- `ClanTheme.gradients.attackButton` (Green action bevel gradient)
- `ClanTheme.gradients.goldButton` (Gold action bevel gradient)
- `ClanTheme.gradients.cancelButton` (Red alert bevel gradient)
- `ClanTheme.gradients.goldBar` (Gold horizontal progress bar)
- `ClanTheme.gradients.elixirBar` (Elixir horizontal progress bar)
- `ClanTheme.gradients.darkElixirBar` (Dark elixir progress bar)
- `ClanTheme.gradients.gemsBar` (Emerald gems progress bar)
