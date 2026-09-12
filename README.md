# User Login System

A learning project built in Java based on Robert Heaton's "Programming Projects for Advanced Beginners" series.

## Project Overview

This project is a simple user authentication system that will:

- Allow users to create an account
- Allow users to log in with a username and password
- Store user credentials securely
- Validate login attempts
- Prevent unauthorized access

The primary goal of this project is to improve my Java programming skills while gaining practical experience with concepts used in real-world software development.

## Learning Objectives

Through this project I aim to practice:

- Object-Oriented Programming (OOP)
- Classes and objects
- Collections and data structures
- File handling
- Password hashing
- Exception handling
- Input validation
- Project structure and code organisation
- Git version control

## Planned Steps

### Phase 1
- [X] User login functionality
- [X] Password hashing
- [X] Persistent storage using files
- [ ] User registration functionality

### Phase 2
- [ ] Database integration
- [ ] Password validation
- [ ] Password reset functionality

## Project Structure

```
.
├── src
│   ├── User.java
│   ├── UserLogin.java (main)
│   ├── UserManagement.java
│   ├── users.csv
│   └── users.json
├── .gitignore
└── README.md
```

### Clone the Repository

```bash
git clone https://github.com/platplat/user-login.git
```

### Run the Project

```bash
javac src/*.java
java UserLogin
```

## References

- Robert Heaton: https://robertheaton.com/2019/08/12/programming-projects-for-advanced-beginners-user-logins/