# AccessForge

AccessForge is an access management and identity workflow platform built on Spring Boot. It provides a foundation for authentication, fine-grained authorization, and permission orchestration across modular CMS-style applications.

---

## Features

- Stateless JWT authentication with HTTP-only cookies
- Annotation-driven, module-scoped authorization (`@RequiresPermission`)
- Authority levels: `CREATE`, `READ`, `UPDATE`, `DELETE`, `ALL`, `ALL_WITH_REVIEW`
- CMS module registry (e.g. `BLOG`, `PAGES`) with per-module permission grants
- Custom hierarchical modules with parent/child assignments
- Centralized exception handling with structured error responses
- DTO-to-entity mapping via MapStruct
- Database schema versioning via Flyway

---

## Tech Stack

| Layer        | Technology                          |
| ------------ | ----------------------------------- |
| Language     | Java 25                             |
| Framework    | Spring Boot 4.0.5                   |
| Security     | Spring Security + JJWT 0.12.6       |
| Persistence  | Spring Data JPA / Hibernate         |
| Database     | PostgreSQL                          |
| Migrations   | Flyway                              |
| Mapping      | MapStruct 1.6.3                     |
| Validation   | Jakarta Validation (Hibernate Validator) |
| Build        | Maven                               |
| Monitoring   | Spring Boot Actuator                |

---

## Architecture Overview

```
src/main/java/org/samtar/cms/
│
├── CmsApplication.java              entry point
│
├── common/
│   ├── exception/                   AuthException, UserException, GlobalException, etc.
│   ├── response/                    GenericResponse wrapper
│   └── util/                        JwtUtils, AuthCookieUtils, DateUtil
│
├── config/
│   ├── mapper/                      MapStruct config
│   ├── security/                    SecurityConfig, JwtFilterChain
│   └── web/                         CorsConfig
│
├── modules/
│   ├── accesscontrols/
│   │   ├── authority/               Authority lookup (CRUD + READ enums)
│   │   ├── cmsModules/              CMS surface registry (BLOG, PAGES, ...)
│   │   ├── customModules/           User-defined modules + children + assignments
│   │   ├── permission/              user/module/authority join + grant logic
│   │   └── user/                    user, profile, auth controller
│   │
│   └── shared/                      enums (Authorities, Roles, Status, ...) + shared entities
│
└── security/
    ├── annotation/                  @RequiresPermission
    ├── aspect/                      PermissionAspect (AOP enforcement)
    ├── exception/                   AccessDeniedException
    └── resolver/                    (optional) facade for combined permission sources
```

### Authorization flow

1. Client logs in via `/api/v1/auth/...`.
2. `JwtFilterChain` extracts the JWT from the cookie, validates it, and loads the `UserPrincipleImps` into the `SecurityContext`.
3. Controller method is annotated with `@RequiresPermission(module = "BLOG", authority = Authorities.CREATE)`.
4. `PermissionAspect` intercepts the call, fetches the current user, and delegates to `PermissionService.hasPermission(user, module, authority)`.
5. `PermissionService` checks super-admin bypass, expands `ALL` / `ALL_WITH_REVIEW`, and returns the decision.
6. On denial, `AccessDeniedException` is thrown and rendered by `GlobalException` as a structured 403 response.

---

## Prerequisites

- JDK 25
- Maven 3.9+
- PostgreSQL 14+ (running locally or reachable via URL)

---

## Installation

Clone the repository:

```
git clone https://github.com/startthecode/AccessForge.git
cd AccessForge
```

Configure the database in `src/main/resources/application.properties` (or via environment variables, see below).

Build and run:

```
./mvnw clean install
./mvnw spring-boot:run
```

On Windows:

```
mvnw.cmd clean install
mvnw.cmd spring-boot:run
```

The application starts on `http://localhost:8080` by default.

---

## Configuration

Key settings (override via `application.properties`, `application-{profile}.properties`, or environment variables):

```
# Server
server.port=8080

# Datasource
spring.datasource.url=jdbc:postgresql://localhost:5432/accessforge
spring.datasource.username=postgres
spring.datasource.password=postgres

# JPA
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=false

# Flyway
spring.flyway.enabled=true
spring.flyway.locations=classpath:db/migration

# JWT
app.jwt.secret=change-me-base64-encoded-256-bit-key
app.jwt.access-ttl-minutes=15
app.jwt.refresh-ttl-days=7

# Cookie
app.auth.cookie.secure=true
app.auth.cookie.same-site=Strict
```

Never commit real secrets. Use environment variables in production:

```
export APP_JWT_SECRET=...
export SPRING_DATASOURCE_URL=...
export SPRING_DATASOURCE_PASSWORD=...
```

---

## API Endpoints (selected)

| Method | Endpoint                          | Auth     | Description                              |
| ------ | --------------------------------- | -------- | ---------------------------------------- |
| POST   | /api/v1/auth/login                | Public   | Authenticate, issue JWT cookie           |
| POST   | /api/v1/auth/register             | Public   | Register a new user                      |
| POST   | /api/v1/auth/logout               | Required | Clear auth cookie                        |
| GET    | /api/v1/users/me                  | Required | Current user profile                     |
| POST   | /api/v1/custom-modules            | Required | Create a custom module                   |
| POST   | /api/v1/custom-modules/{id}/child | Required | Add a child node to a module             |
| POST   | /api/v1/custom-modules/assign     | Required | Assign a module to a user/profile        |
| POST   | /api/v1/permissions               | Required | Grant an authority on a module to a user |
| GET    | /api/testing/unprotected          | Public   | Health check / smoke test                |

Refer to controller classes under `modules/accesscontrols/**/controller/` for the full list.

---

## Authorizing a Controller Method

```java
@PostMapping
@RequiresPermission(module = "BLOG", authority = Authorities.CREATE)
public ResponseEntity<BlogResponse> createBlog(@Valid @RequestBody BlogRequest req) {
    return ResponseEntity.ok(blogService.create(req));
}
```

The aspect intercepts the call before the method body runs. If the current principal lacks the authority on the named module, a 403 is returned.

---

## Database Migrations

All schema changes live under `src/main/resources/db/migration/` and follow the Flyway naming convention:

```
V1__init_schema.sql
V2__add_permission_table.sql
V3__seed_cms_modules.sql
```

Migrations run automatically on application startup when `spring.flyway.enabled=true`.

---

## Running Tests

```
./mvnw test
```

Run a single test class:

```
./mvnw -Dtest=PermissionServiceTest test
```

---

## Development Workflow

```
git checkout -b feature/<short-description>
# make changes
./mvnw test
git commit -m "feat(<scope>): <message>"
git push origin feature/<short-description>
```

Open a Pull Request after pushing. Commit prefixes used in this repo: `feat`, `fix`, `chore`, `refactor`, `docs`, `test`.

---

## Roadmap

- OAuth2 / OIDC provider integration
- Admin dashboard (React frontend)
- Multi-tenant scoping on the permission graph
- `ALL_WITH_REVIEW` workflow (request -> reviewer -> commit)
- Audit log for permission grants and privileged actions
- CLI for seeding modules, authorities, and admin users

---

## Contributing

1. Fork the repository
2. Create a feature branch
3. Add tests for new behavior
4. Open a pull request describing the change and motivation

---

## License

MIT License.

---

## Maintainer

**startthecode**
