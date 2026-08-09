# Boat Rental Management System

A multi-tier Client-Server desktop application designed for managing boat rentals, built in Java using Object-Oriented Programming (OOP) principles and a MySQL database.

The application is engineered as a simulation environment based on a **Client-Server architecture**:
* **PROJECT_SERVER:** Manages client connections, handles core business logic, and processes database operations.
* **PROJECT_CLIENT:** Provides a graphical user interface (GUI) for users to interact with the system, perform boat reservations, and manage rental records.
* **PROJECT_COMMON:** Contains shared domain models, transfer objects, and system architecture components used by both Client and Server modules.
* **Database Layer:** Uses a **MySQL** database for persistence. The database schema script (`database_schema.sql`) is included in the root directory.

## Tech Stack & Tools

* **Language:** Java
* **Architecture:** Client-Server, Multi-Tier (MVC / 3-Tier)
* **Networking & Persistence:** Java Sockets, JDBC
* **Database:** MySQL
* **Tools & IDEs:** NetBeans IDE, SQLyog, Git
