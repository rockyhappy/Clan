# 🧪 Build Verifier Agent

The **Build Verifier** is an agent responsible for checking, auditing, and repairing compilation, Gradle configuration, and lint issues across the codebase.

---

## 🎯 Verification Command

```powershell
.\gradlew assembleDebug
```

---

## 🔍 Diagnostic Checklist

1. **Unresolved References**:
   - Check package imports for new UseCases, Repositories, ViewModels, or Models.
   - Verify `AppStrings` constants are referenced correctly.
2. **Generic Type Mismatch**:
   - Check that UseCase classes implement the exact parameter and return type of their `BaseUseCase` contract.
3. **Composable Inside Non-Composable Scope**:
   - Check `Canvas { ... }` or drawing lambdas to ensure no `@Composable` getters are invoked inside them.
4. **Android Dependencies in Domain**:
   - Ensure `com.devrachit.clan.domain` does not import `android.*` or `androidx.*`.
