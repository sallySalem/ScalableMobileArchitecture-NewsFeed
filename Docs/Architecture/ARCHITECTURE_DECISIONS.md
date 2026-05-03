# Architecture Decisions

## Modularization
- **Reduces coupling** between features → independence
- **Allows teams to work independently** → faster iteration
- **Improves build performance** → only affected modules rebuild
- **Enables feature flags** → easier rollouts and experimentation
- **Reduces cognitive load** → developers focus on their domain

## Data Layer
The data layer implements **repository pattern** with multiple sources:
- **Remote Source** (Retrofit) → API communication
  - Network interceptors for logging and auth
  - Error handling and retry logic
- **Local Source** (Room) → offline support & caching
  - SQLite-backed persistence
  - Pagination support via Room + Paging
- **Repository** → abstraction layer
  - Delegates to appropriate data source
  - Handles data transformation via mappers
  - Ensures clean separation from domain logic

## Domain Layer (Business Logic)
- **Pure Kotlin** (no Android dependencies)
- **Use Cases** → encapsulate business logic
  - GetPostsUseCase
  - GetPostDetailUseCase
  - CreatePostUseCase
  - InteractWithPostUseCase
- **Domain Models** → core entities
- **Repository Interfaces** → contracts for data sources

## Dependency Injection (Hilt)
- Compile-time safe DI with Hilt
- Reduces boilerplate through annotations
- Modules defined in layers for clear ownership

## Pagination Strategy
- **Paging 3 Library** → handles pagination complexity
- **Room Integration** → offline-first pagination
- **Network + Local** → seamless user experience with caching

## Trade-offs

| Decision             | Pros                           | Cons                    |
|----------------------|--------------------------------|-------------------------|
| Modular architecture | Scalability, team independence | Increased complexity    |
| Native Android       | Performance, flexibility       | Higher development cost |
| Clean layering       | Maintainability                | More abstraction        |

