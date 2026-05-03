# Project Structure

## Module Organization

This project intentionally demonstrates a **hybrid modular architecture** to illustrate:
- What proper modularization looks like
- How legacy or non-modular code coexists during migrations
- The trade-offs of partial refactoring in large systems
- Gradual migration path from monolith → modular

## Directory Structure

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

## Module Dependencies

```
app (Android Application)
├── depends on → features:posts-list
├── depends on → domain
└── depends on → data

features:posts-list (Independent Feature)
├── depends on → domain
└── depends on → data

data (Implementation)
└── depends on → domain

domain (Pure Kotlin)
└── (no external dependencies)
```
