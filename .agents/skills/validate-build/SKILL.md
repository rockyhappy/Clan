---
name: validate-build
description: >-
  Procedure for compiling and validating the Clan Android application using Gradle.
  Use this skill whenever you modify code, add new UseCases, change theme tokens, or need to verify zero build errors.
---

# 🧪 Validate Build Skill

This skill outlines the standard validation protocol for building the Android project and resolving issues.

---

## ⚡ Execution Command

Run the standard Gradle assemble debug task:

```powershell
.\gradlew assembleDebug
```

Confirm that the output ends with:
```
BUILD SUCCESSFUL in Xs
```
and has exit code `0`.

---

## 🛠️ Troubleshooting Common Failures

### 1. `Unresolved reference: <ClassName>`
- **Cause**: Missing import or misspelled package.
- **Fix**: Check `package` declaration at top of file and ensure import matches exact path in `com.devrachit.clan.*`.

### 2. `Type mismatch: inferred type is ... but ... was expected`
- **Cause**: UseCase parameter or return type does not match `BaseUseCase` generic arguments.
- **Fix**: Check your `BaseUseCase<In, Out>` type declaration and match it with `operator fun invoke(params: In): Out`.

### 3. `@Composable invocations can only happen from the context of a @Composable function`
- **Cause**: Trying to access `ClanTheme.colors` inside `Canvas { ... }` or `DrawScope`.
- **Fix**: Extract color access into a local variable before the `Canvas` call:
  ```kotlin
  val surfaceColor = ClanTheme.colors.surface
  Canvas(...) {
      drawRect(color = surfaceColor)
  }
  ```

### 4. `Gradle Daemon Memory / Lock Issues`
- Run `.\gradlew --stop` and retry.
