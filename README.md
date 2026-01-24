This project is a Java-based **Dormitory Management System** that demonstrates:

- Object-Oriented Programming (OOP)
- Layered architecture (Model -> Repository -> Service)
- Database integration using JDBC
- CRUD operations on multiple related entities

The system manages students living in a dormitory, their rooms, dorm buildings, contracts, and violations.

---

## Project Topic & Entities

**Topic:** Dormitory Management System  

The project includes several related entities:

| Entity     | Description |
|------------|-------------|
| `Student`  | Represents a student living in the dormitory |
| `Dorm`     | Represents a dormitory building |
| `Room`     | Represents a room inside a dorm |
| `Contract` | Represents a student’s dormitory contract |
| `Violation`| Represents violations committed by students |

Relationships:
- A **Dorm** contains many **Rooms**
- A **Room** can contain multiple **Students**
- A **Student** can have a **Contract**
- A **Student** may have multiple **Violations**

Each entity is implemented as a Java class in `src/models`.

---

## Abstraction & Layered archiyecture

App
Service Layer
Repository Layer
Database (JDBC)


models/ - domain objects (Student, Dorm, Room, etc.)
repository/ - database operations (CRUD)
service/ - business logic and validation
utils/DatabaseConnection - database connection handling
exception/ - custom exceptions

## Database

The project uses a relational database accessed through JDBC.

Connection is handled in:

```src/utils/DatabaseConnection.java```

## API 

Although this is a console-based Java project, it follows the same logic as a REST backend:

Operation	Layer
Create	Service -> Repository -> DB
Read	Service -> Repository -> DB
Update	Service -> Repository -> DB
Delete	Service -> Repository -> DB

## How to run

1. Clone the repository:

```git clone https://github.com/alikhanov0/assignment-3.git```

2. Open in IDE (IntelliJ / VS Code)

3. Configure database credentials in:

```src/utils/DatabaseConnection.java```

4. Run:

```App.java```