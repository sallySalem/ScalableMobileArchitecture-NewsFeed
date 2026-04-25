# Mobile Platform Architecture — Scalable News Feed

## Overview

This project demonstrates how to design and scale a mobile application architecture for a News Feed product used by millions of users. It showcases a **hybrid modular architecture** combining:

- A fully modularized feature (`posts-list`)
- Shared domain and data layers
- Core application logic in the main app module

The focus is not on building features, but on designing a system that:

- Scales across multiple teams
- Maintains consistency and quality
- Enables fast and reliable delivery
- Demonstrates migration patterns for legacy codebases

## Problem Statement
Design a mobile application for a News Feed that:
- Supports large-scale usage
- Works with multiple teams in parallel
- Evolves from monolith → modular architecture 
- Keeps performance and maintainability at scale

## Architecture Overview
The application uses a **three-layer modular architecture** following the **MVVM (Model-View-ViewModel)** pattern:

### Layer 1: Application Module (View Layer)
- **Main App** (`app/`) → UI orchestration, navigation, DI setup
- Jetpack Compose-based UI → renders UI declaratively
- Hilt dependency injection
- Navigation graphs
- Observes state changes from ViewModels

### Layer 2: Feature Modules (ViewModel Layer)
- **Posts List** (`features/posts-list`) → fully modularized, independently developed
- **PostListViewModel** → holds UI state and communicates with use cases
  - Uses coroutines for async operations
  - Survives configuration changes (lifecycle-aware)
- Demonstrates best practices for feature isolation
- Can be developed and tested independently by dedicated teams

### Layer 3: Domain & Data Layers (Model Layer - Shared Foundation)
- **Domain Layer** → business logic, use cases, and entities
  - Interfaces for repositories (contracts for data sources)
  - Domain models (pure Kotlin entities)
  - Use cases (GetPostsUseCase, GetPostDetailUseCase, CreatePostUseCase, InteractWithPostUseCase)
  - Pure Kotlin, no Android dependencies
- **Data Layer** → data sources and repository implementations
  - Retrofit API integration (Remote source)
  - Local caching with Room (Local source)
  - Pagination support
  - Mappers for DTO transformation
  - Repository implementations



                        ┌──────────────────────────────┐
                        │        Main App Layer        │
                        │     Compose UI + Nav + DI    │
                        └───┬──────────────┬─────────┬─┘
                            │              │         │
              ┌─────────────▼────┐   ┌─────▼──────┐  │
              │  Feature Modules │   │Domain Layer│  │
              └────┬────┬────────┘   └───┬────────┘  │
                   │    │                │           │
                   │    └────────┬───────┤           │
                   │             │       │           │
                   └─────────┬───▼───────▼───────────▼────┐
                             │         Data Layer         │
                             │       Retrofit+Room        │
                             │        Repositories        │
                             └────────────────────────────┘
## Key Architecture Decisions

### Modularization
- **Reduces coupling** between features → independence
- **Allows teams to work independently** → faster iteration
- **Improves build performance** → only affected modules rebuild
- **Enables feature flags** → easier rollouts and experimentation
- **Reduces cognitive load** → developers focus on their domain

### Data Layer
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

### Domain Layer (Business Logic)
- **Pure Kotlin** (no Android dependencies)
- **Use Cases** → encapsulate business logic
  - GetPostsUseCase
  - GetPostDetailUseCase
  - CreatePostUseCase
  - InteractWithPostUseCase
- **Domain Models** → core entities
- **Repository Interfaces** → contracts for data sources

### Dependency Injection (Hilt)
- Compile-time safe DI with Hilt
- Reduces boilerplate through annotations
- Modules defined in layers for clear ownership

### Pagination Strategy
- **Paging 3 Library** → handles pagination complexity
- **Room Integration** → offline-first pagination
- **Network + Local** → seamless user experience with caching

## Trade-offs

| Decision             | Pros                           | Cons                    |
|----------------------|--------------------------------|-------------------------|
| Modular architecture | Scalability, team independence | Increased complexity    |
| Native Android       | Performance, flexibility       | Higher development cost |
| Clean layering       | Maintainability                | More abstraction        |


## Scaling the System

### Current State: Hybrid Architecture

**Team Structure:**
- 1 dedicated team → Posts List feature (modularized)
- Core team → Owns domain, data, app layers
- Total: Can scale to 3-5 teams with this foundation

### Step 1: Single-Feature Modularization

 **Already Implemented**: `posts-list` feature module

- Developed independently from main app
- Has own ViewModel, UI components
- Integrates with shared domain/data layers
- Can be tested and deployed independently

### Step 2: Feature Expansion (3-5 Teams)

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

### Step 3: Avoiding Conflicts & Ensuring Quality

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

### Avoiding Monolithic Pitfalls

| Problem                                 | Solution | Implementation |
|-----------------------------------------|----------|-----------------|
| Cross-feature coupling<br/>(Tight coupling) | Clear module boundaries | No imports between features |
| Slow builds                             | Parallel compilation | Feature modules built separately |
| Shared state management                 | Domain layer contracts | UseCase interfaces |
| UI component duplication                | Shared component module | `ui-common` module |
| Testing complexity                      | Module-level testing | Unit + integration tests per feature |
| Onboarding difficulty                   | Clear folder structure | Consistent patterns across modules |


## Project Structure

### Module Organization

This project intentionally demonstrates a **hybrid modular architecture** to illustrate:
- What proper modularization looks like 
- How legacy or non-modular code coexists during migrations
- The trade-offs of partial refactoring in large systems
- Gradual migration path from monolith → modular

### Directory Structure

```
NewsFeedApp/
├── app/                           → Main Application Module
│   ├── build.gradle.kts          → App module configuration
│   ├── src/main/
│   │   ├── java/com/msd/newsfeedapp/
│   │   │   ├── NewsFeedApplication.kt
│   │   │   ├── di/               → Application-level DI
│   │   │   └── ui/               → UI screens, navigation, theme
│   │   ├── res/                  → Resources (layouts, strings, colors)
│   │   └── AndroidManifest.xml
│   └── proguard-rules.pro
│
├── domain/                        → Domain Layer (Pure Kotlin)
│   ├── build.gradle.kts
│   └── src/main/java/com/msd/domain/
│       ├── model/                → Domain entities & models
│       ├── repository/           → Repository interfaces
│       ├── usecase/              → Business logic use cases
│       │   ├── GetPostsUseCase
│       │   ├── GetPostDetailUseCase
│       │   ├── CreatePostUseCase
│       │   └── InteractWithPostUseCase
│       └── event/                → Domain events
│
├── data/                          → Data Layer (Implementation)
│   ├── build.gradle.kts
│   └── src/main/java/com/msd/data/
│       ├── remote/               → Network layer
│       │   ├── api/              → Retrofit interfaces
│       │   ├── dto/              → Data Transfer Objects
│       │   ├── interceptor/      → HTTP interceptors
│       │   ├── NetworkUtils.kt
│       │   └── ApiResult.kt      → Sealed class for API responses
│       ├── local/                → Local caching (Room)
│       │   ├── database/
│       │   ├── dao/
│       │   └── entity/
│       ├── mapper/               → DTO ↔ Domain model mapping
│       ├── repository/           → Repository implementations
│       │   └── PostRepositoryImpl.kt
│       ├── paging/               → Pagination logic
│       └── di/                   → Data layer DI modules
│
├── features/                      → Feature Modules
│   └── posts-list/               → Modularized Posts List Feature
│       ├── build.gradle.kts
│       └── src/main/
│           ├── java/com/msd/posts_list/
│           │   ├── PostListScreen.kt   → Feature UI
│           │   ├── PostListViewModel.kt → Feature business logic
│           │   ├── PostItem.kt         → UI components
│           │   └── di/                 → Feature DI (if needed)
│           └── res/                    → Feature resources
│
├── gradle/
│   ├── libs.versions.toml        → Centralized dependency versioning
│   └── wrapper/                  → Gradle wrapper
│
├── build.gradle.kts              → Root build configuration
├── settings.gradle.kts           → Module includes
└── local.properties              → Local build configuration
```

### Module Dependencies

```
app (Android Application)
├── depends on → features:posts-list
├── depends on → domain
└── depends on → data

features:posts-list (Independent Feature)
└── depends on → domain

data (Implementation)
└── depends on → domain

domain (Pure Kotlin)
└── (no external dependencies)
```

## Backend (Mock Server)

This repository includes a Node.js Express mock backend that simulates API endpoints for the News Feed application.

### Backend Structure

```
mock-backend/
├── server.js          → Express application setup
├── routes/            → API route handlers
├── middleware/        → Auth, logging, error handling
├── models/            → Mock data models
└── package.json       → Dependencies
```

### Supported Endpoints

- `GET /api/posts` → Fetch posts with pagination
- `GET /api/posts/:id` → Get post details
- `POST /api/posts` → Create new post
- `PUT /api/posts/:id` → Update post
- `DELETE /api/posts/:id` → Delete post
- `POST /api/posts/:id/interact` → Like, comment, share

### Run the Backend Locally

```bash
# Navigate to mock-backend directory
cd mock-backend

# Install dependencies
npm install

# Start development server
npm run dev
```

**Note**: The Android app should point to this local backend URL in its configuration.

### Backend Features
- Mock data generation
- Authentication
- Error handling
- Pagination support

---

## Technology Stack
Key technologies:
- **Kotlin** with Jetpack Compose for modern declarative UI
- **Hilt** for dependency injection
- **Retrofit + Room** for network and local data management
- **Paging 3** for efficient pagination
- **Coroutines** for async operations
- **ExoPlayer** for media playback

**All versions are defined in** `gradle/libs.versions.toml` for centralized dependency management.

---

## System Design Interview Guide

For a detailed guide on how to approach system design interviews and apply these architectural patterns, see **[SYSTEM_DESIGN_GUIDE.md](./SYSTEM_DESIGN_GUIDE.md)**.

This includes:
- Requirements clarification framework
- Architecture definition approach
- Modularization strategy
- Scaling considerations
- Trade-offs discussion
- How this project demonstrates these patterns 
---
