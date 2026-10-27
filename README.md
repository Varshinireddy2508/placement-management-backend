# PlaceTrack Backend &mdash; Spring Boot & MySQL

RESTful API backend for the Student Placement Management System built with Java 21, Spring Boot 4 / Spring Data JPA, and MySQL.

---

## 🚀 Prerequisites
- **Java:** JDK 21+ (`java -version`)
- **Maven:** Installed or use included `mvnw.cmd`
- **MySQL Server:** 8.0+ running on port `3306`

---

## ⚙️ Configuration
The application is pre-configured to connect to MySQL on `localhost:3306` and automatically creates the `placement_db` database and its tables on boot:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/placement_db?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
spring.datasource.username=${MYSQL_USERNAME:root}
spring.datasource.password=${MYSQL_PASSWORD:root}
spring.jpa.hibernate.ddl-auto=update
```

If your MySQL root password is not `root`, either:
1. Set an environment variable before running:
   ```powershell
   $env:MYSQL_PASSWORD="your_password"
   ```
2. Or edit `src/main/resources/application.properties` directly.

---

## 🏃‍♂️ How to Run Locally

### 1. Start MySQL Server
Ensure your MySQL service is started (via Windows Services or command prompt):
```cmd
net start MySQL80
```

### 2. Run Spring Boot Application
From this `backend/` folder:
```cmd
.\mvnw.cmd spring-boot:run
```
*(Or use `mvn spring-boot:run` or hit **Run** in IntelliJ IDEA / Eclipse)*

The server will start on **`http://localhost:8080`**.

---

## 🧪 Run Tests
```cmd
.\mvnw.cmd test
```

---

## 📡 REST API Reference

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `GET` | `/api/dashboard/stats` | Summary statistics (students, drives, applications, placed count) |
| `GET` | `/api/students` | Get all students |
| `POST` | `/api/students` | Register a student (with validation & duplicate checks) |
| `GET` | `/api/students/{id}` | Get student by ID |
| `PUT` | `/api/students/{id}` | Update student record |
| `DELETE` | `/api/students/{id}` | Delete student record |
| `GET` | `/api/drives` | Get all recruitment drives |
| `POST` | `/api/drives` | Schedule new recruitment drive |
| `GET` | `/api/drives/{id}` | Get drive by ID |
| `PUT` | `/api/drives/{id}` | Update drive details |
| `DELETE` | `/api/drives/{id}` | Delete drive |
| `GET` | `/api/applications` | Get all submitted job applications |
| `POST` | `/api/applications/apply` | Apply candidate to drive (verifies CGPA cutoff) |
| `PUT` | `/api/applications/{id}/status` | Update application status (`APPLIED`, `SHORTLISTED`, `SELECTED`, `REJECTED`) |
| `DELETE` | `/api/applications/{id}` | Withdraw / delete application |

---

## 📤 Pushing to Your Backend GitHub Repository
When ready to publish this backend separately:
```bash
cd backend
git init
git add .
git commit -m "feat: complete Spring Boot placement management backend"
git branch -M main
git remote add origin https://github.com/<your-username>/placement-management-backend.git
git push -u origin main
```
