# Mobile Platform Architecture — Scalable News Feed

## Overview

This project demonstrates how to design and scale a mobile application architecture for a News Feed product used by millions of users.

The focus is not on building features, but on designing a system that:

- Scalable mobile architecture design 
- Multi-team development scenarios
- Engineering trad-offs and decision-making
- Maintains consistency and quality
- Demonstrates migration patterns for legacy codebases 

It includes a sample Android implementation alongside architectural documentation, trade-offs, and system design considerations.

---
## Problem Statement
**Design a mobile application for a News Feed that:**
- supports a large and growing user base
- Evolves from monolith → modular architecture
- Evolves continuously with new product requirements 
- Built and maintained by multiple teams in parallel

**Key challenges:**
- Avoiding a tightly coupled monolithic codebase
- Enabling independent team contributions
- Maintaining performance, consistency, and maintainability at scale

## Architecture Overview
The application uses a **hybrid modular architecture** following the **MVVM (Model-View-ViewModel)** pattern:

- One feature is fully modularized feature (`posts-list`)
- Other features remain inside the main app
- Architectural boundaries are introduced gradually
- Shared domain and data layers
- Core application logic in the main app module

This approach reflects how real systems evolve in production environments rather than being fully redesigned from scratch.

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
**Domain Layer** → business logic, use cases, and entities
* Interfaces for repositories (contracts for data sources)
* Domain models (pure Kotlin entities)
* Use cases (GetPostsUseCase, GetPostDetailUseCase, CreatePostUseCase, InteractWithPostUseCase)
* Pure Kotlin, no Android dependencies

**Data Layer** → data sources and repository implementations
* Retrofit API integration (Remote source)
* Local caching with Room (Local source)
* Pagination support
* Mappers for DTO transformation
* Repository implementations

-

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


### Why Not Full Modularization?

Although full modularization is often considered a best practice, it comes with significant trade-offs:

* Increased project and dependency complexity
* Higher coordination cost across teams
* Slower onboarding for new contributors
* Risk of over-engineering early-stage systems

In real production environments, a **partial modularization strategy** is often more practical and sustainable.

This project intentionally demonstrates:

* A realistic migration state
* Balancing delivery speed vs architectural purity
* Gradual evolution instead of full refactoring
---
## Key Architecture Decisions

**1. Incremental Modularization over Big-Bang Refactor**

Modularization is introduced gradually instead of a full system rewrite.

**Why:**

* Reduces risk in production systems
* Avoids blocking feature delivery
* Allows teams to adopt architecture patterns progressively 
* Clear Boundaries in a Hybrid System

**2. Clear Boundaries in a Hybrid System**

Even in a partially modularized system, boundaries are intentionally defined.

**Why:**

* Prepares the system for future modular extraction
* Prevents deep coupling between features
* Maintains architectural direction during transition


### Trade-offs Summary

This project explicitly demonstrates that:

* Perfect architecture is rarely the starting point
* Incremental improvement is more practical than full rewrites
* Trade-offs are an essential part of system design
* Architectural decisions must balance speed, scale, and maintainability

For detailed information on key architecture decisions, modularization strategy, data layer design, dependency injection, and trade-offs, see **[ARCHITECTURE_DECISIONS.md](./ARCHITECTURE_DECISIONS.md)**.

---
## Scaling the System

### Current State: Hybrid Architecture

**Team Structure:**
- 1 dedicated team → Posts List feature (modularized)
- Core team → Owns domain, data, app layers
- Total: Can scale to 3-5 teams with this foundation

### Step 1: Expand Modularization
* Gradually extract additional features into modules
* Define stricter module boundaries
### Step 2: Introduce Platform Layer
* Shared components (UI, networking, data) become platform-owned modules
* Clear ownership model per module
### Step 3: Enable Team Autonomy
* Teams own feature modules end-to-end
* Reduced cross-team dependencies
### Step 4: Improve Developer Experience
* Optimized build and CI/CD pipelines
* Standardized tooling and development practices

For detailed information on system evolution, team scaling, feature expansion, and avoiding monolithic pitfalls, see **[SCALING_DETAILS.md](./SCALING_DETAILS.md)**.

---
## Project Structure
### Module Organization

This project intentionally demonstrates a **hybrid modular architecture** to illustrate:
- What proper modularization looks like
- How legacy or non-modular code coexists during migrations
- The trade-offs of partial refactoring in large systems
- Gradual migration path from monolith → modular

For detailed information on module organization, directory structure, and module dependencies, see **[PROJECT_STRUCTURE.md](./PROJECT_STRUCTURE.md)**.

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

All versions are defined in `gradle/libs.versions.toml` for centralized dependency management.

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
