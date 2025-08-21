# GitHub Users API  

A **Spring Boot** application that consumes the public GitHub API, stores users in a PostgreSQL database, and exposes REST endpoints for queries. The project follows **Clean Architecture principles**, semantic commit conventions, and includes unit test coverage for the `Service` and `Controller` layers.  

---

##  Architecture  

- **Spring Boot** (v4.0.0-M1): main framework for minimal configuration and execution.  
- **Layered structure**:  
  - `domain` → application entities (`User`).  
  - `repository` → database interface via Spring Data JPA.  
  - `service` → business logic, including user synchronization (`GithubSyncService`).  
  - `controller` → REST endpoints to list and fetch users.  
- **Database**:  
  - PostgreSQL, connected via **JPA/Hibernate**.  
  - Initial use of **H2** in-memory for quick prototyping.  
  - Migration to PostgreSQL configured in `application.properties` for real persistence.  
- **External integration**:  
  - `RestTemplate` consuming `https://api.github.com/users`.  
  - Automatic user population on startup.  

---

##  Tests  

- **Service layer**:  
  - `GithubSyncServiceTest` with **Mockito**, mocking GitHub API calls and validating conditional persistence.  
- **Controller layer**:  
  - Initial setup with `MockMvc` for isolated endpoint testing (still in progress).  
- Dependencies managed by `spring-boot-starter-test`.  

---

##  How to run  

1. **Clone repository**  
   ```bash
   git clone https://github.com/username/github-users.git
   cd github-users
   ```

2. **Configure PostgreSQL database**  
   - Create the database `github_users` using **pgAdmin** or CLI:  
     ```sql
     CREATE DATABASE github_users;
     ```
   - Update credentials in `src/main/resources/application.properties`.  

3. **Run the application**  
   ```bash
   mvn spring-boot:run
   ```

4. **Check data**  
   - GitHub API is consumed at startup, saving up to 30 users.  
   - Query with **pgAdmin**:  
     ```sql
     SELECT * FROM users;
     ```

---

##  Main Endpoints  

- **GET /users** → returns all stored users.  
- **GET /users/{id}** → returns a user by `id`.  

---

##  Design decisions and challenges  

- **H2 → PostgreSQL**: started with H2 for simplicity, but switched to PostgreSQL to enable real persistence and closer to a production scenario.  
- **GitHub API field**: the project uses the `url` field from the GitHub API response, instead of `html_url`. This was intentional to strictly follow the specification, even though it does not point directly to the profile page.  
- **Unit tests**:  
  - Faced issues with `spring-boot-test-mockito` dependency, solved by adding the proper starter.  
  - Controller layer tests failed due to context loading errors with `@WebMvcTest`, so priority was given to Service tests first.  
- **Best practices**:  
  - Commits follow **semantic convention** (`feat`, `fix`, `test`, etc.).  
  - Code organized into clear packages (Controller, Service, Repository).  

---

##  Future improvements  

- Complete Controller layer unit tests.  
- Add pagination to `/users` endpoint.  
- Create DTOs to separate internal domain from API response.  
- Add automatic documentation with **Springdoc OpenAPI / Swagger**.  
