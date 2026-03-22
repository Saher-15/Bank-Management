# Bank Management

A desktop banking management application built with JavaFX and MySQL, featuring a full GUI for managing bank accounts, loans, credits, and transactions.

## Features

- **User Authentication** — login and sign up
- **Account Management** — view and manage account information
- **Transfers** — transfer funds between accounts
- **Loans** — apply and manage loan requests
- **Credits** — credit management and requests
- **Mortgage** — mortgage application and tracking
- **Activities** — view transaction history
- **Personal Information** — update profile and settings
- **Change Password** — secure password management
- **Supervisor Panel** — admin controls for loans, credits, and mortgages

## Tech Stack

- Java + JavaFX (GUI)
- MySQL (database)
- MySQL Connector/J 8.0.13 (JDBC driver)
- CSS (JavaFX styling)

## Getting Started

### Prerequisites
- Java 11+
- MySQL Server
- IDE (IntelliJ IDEA or NetBeans recommended)

### 1. Clone the repo
```bash
git clone https://github.com/Saher-15/Bank-Management.git
cd Bank-Management
```

### 2. Setup the database
- Create a MySQL database
- Update the DB connection settings in the server configuration file

### 3. Add the JDBC driver
- The `mysql-connector-java-8.0.13.jar` is included in the repo
- Add it to your project's classpath

### 4. Run the application
- Open the project in your IDE
- Run `Main.java`

## Project Structure

```
src/
├── main/          # Entry point (Main.java)
├── controllers/   # JavaFX page controllers
├── Entity/        # Data models (User, Loan, Credit, Mortgage...)
├── server/        # Database/server connection
├── supervisor/    # Admin panel logic
├── view/          # FXML layout files
└── css/           # Stylesheets
```

