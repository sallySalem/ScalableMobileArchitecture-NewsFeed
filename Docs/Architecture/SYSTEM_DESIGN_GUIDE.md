# System Design Interview Guide

When asked to design a scalable mobile system like a News Feed, follow this approach:

## 1. Clarify Requirements

- **Scale**: millions of users, concurrent connections
- **Data Volume**: posts, users, interactions
- **Performance**: target latency, throughput
- **Platforms**: iOS, Android, web?
- **Constraints**: team size, timeline, budget

## 2. Define Architecture

```
Client Layer    → Mobile UI (Kotlin, Compose)
API Layer       → REST endpoints (mock backend)
Service Layer   → Business logic
Data Layer      → Database (Room), Caching, Sync
Infrastructure  → API gateway, auth, CDN
```

## 3. Modularization Strategy

- **Feature ownership** → Teams own features, not layers
- **Shared resources** → Domain, data, UI components
- **Integration points** → Clear contracts via use cases
- **Dependency management** → Bottom-up dependencies only

## 4. Scaling Considerations

- **Horizontal scaling** → Multiple backend instances
- **Caching strategy** → Local (Room) + network (CDN)
- **Database optimization** → Indexes, sharding for large datasets
- **Pagination** → Paging 3 for efficient loading
- **Monitoring** → Performance metrics, error tracking

## 5. Trade-offs Discussion

| Aspect | Decision | Rationale |
|--------|----------|-----------|
| **Modular vs Monolithic** | Modular | Team independence, faster iteration |
| **Native vs Cross-platform** | Native Kotlin | Performance, platform access, tooling |
| **In-memory vs Persistent** | Both | Session data in memory, posts in Room |
| **Pull vs Push** | Pull (with polling) | Simpler, no infrastructure overhead |
| **Pagination Strategy** | Cursor-based | Handles data mutations better |

## 6. This Project as Reference

This codebase demonstrates:
 - **Modular architecture** with feature modules
 - **Clean architecture** (domain, data, presentation)
 - **Dependency injection** with Hilt
 - **Pagination** with Paging 3 + Room
 - **Hybrid setup** for migration scenarios
 - **Scalable patterns** for multi-team development
