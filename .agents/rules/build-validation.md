# 🧪 Build Validation Rules

This rule defines mandatory build verification procedures for AI agents and developers.

---

## ⚡ Mandatory Verification Gate

Before completing any task, closing a session, or declaring feature readiness, the agent **MUST**:

1. Run `./gradlew assembleDebug` in the project root directory.
2. Confirm the command exits with code `0` and outputs `BUILD SUCCESSFUL`.
3. If the build fails:
   - Carefully inspect compiler/lint error messages.
   - Fix all compilation errors, unresolved references, type mismatches, or missing imports.
   - Re-run `./gradlew assembleDebug` until a clean build is achieved.

---

## 🔍 Common Issues to Watch For

- **Import resolution**: Ensure newly created UseCases, Repositories, or Strings are properly imported with their full package names.
- **DrawScope / Canvas**: Ensure no `@Composable` functions or `@ReadOnlyComposable` getters (e.g. `ClanTheme.resources.gold`) are evaluated directly inside `Canvas { ... }` or `DrawScope` lambdas. Resolve them outside the canvas in the composable scope first.
- **Operator invoke**: Ensure the UseCase implementation overrides `operator fun invoke(...)` matching the exact generic types of the base interface.
