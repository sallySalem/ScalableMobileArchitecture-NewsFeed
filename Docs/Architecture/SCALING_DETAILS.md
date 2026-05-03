# Scaling Details

## Current State: Hybrid Architecture

**Team Structure:**
- 1 dedicated team → Posts List feature (modularized)
- Core team → Owns domain, data, app layers
- Total: Can scale to 3-5 teams with this foundation

## Step 1: Single-Feature Modularization

 **Already Implemented**: `posts-list` feature module

- Developed independently from main app
- Has own ViewModel, UI components
- Integrates with shared domain/data layers
- Can be tested and deployed independently

## Step 2: Feature Expansion (3-5 Teams)

**When adding more features (Profile, Notifications, etc.):**

Each feature gets its own module:
```
features/
├── posts-list/
├── user-profile/
├── notifications/
└── .../
```

**Ownership Model:**
```
Team A → features:posts-list
Team B → features:user-profile
Team C → features:notifications
Core Team → domain + data + app
```

## Step 3: Avoiding Conflicts & Ensuring Quality

**1. Module Boundaries**
- Each feature module depends ONLY on domain + data
- NO cross-feature dependencies
- Shared UI components in app module or separate `ui-common` module

**2. Code Ownership**
```
OWNERS file:
/domain → Core team
/data   → Core team
/app    → Core team
/features/posts-list → Team A
/features/user-profile → Team B
```

**3. Clear API Contracts**
- Domain use cases are contracts
- Data layer abstractions are immutable
- Features implement features, not other features

**4. Consistent Patterns**
- All features follow same ViewModel + Screen pattern
- Shared dependency injection rules
- Standardized error handling

## Avoiding Monolithic Pitfalls

| Problem                                 | Solution | Implementation |
|-----------------------------------------|----------|-----------------|
| Cross-feature coupling<br/>(Tight coupling) | Clear module boundaries | No imports between features |
| Slow builds                             | Parallel compilation | Feature modules built separately |
| Shared state management                 | Domain layer contracts | UseCase interfaces |
| UI component duplication                | Shared component module | `ui-common` module |
| Testing complexity                      | Module-level testing | Unit + integration tests per feature |
| Onboarding difficulty                   | Clear folder structure | Consistent patterns across modules |
