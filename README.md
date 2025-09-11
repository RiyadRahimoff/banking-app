# Banking-App

**Current Main Branch: `release1`**

> Note: All development code currently resides in the **release1** branch.  
> The `master` branch will be used in the future once the project reaches a stable release.

---

## Project Overview

Banking-App (in development) is a simple banking application built with Java (SPRING BOOT).  
The project simulates basic banking operations for learning and testing purposes.

## Features

Banking-App currently implements the following functionalities:

### 1. User Registration & Login
- Users can create an account with email, username, and password.
- Passwords are securely hashed using Spring Security.
- Login functionality validates credentials and provides authentication tokens (JWT).
- Input validation and error handling for secure and reliable login/register process.
- Users have a complete profile containing:
  - Full name
  - Date of birth
  - Address

### 2. Bank Account Management
- Users can create a bank account associated with their profile.
- Each account has a unique account number.
- Users can view their account details including balance and account status.
- Account deletion or modification features can be added in future updates.

### 3. Card Management
- Users can generate a bank card tied to their account.
- Card includes:
  - Card number (generating with LUHN alghoritm)
  - CVV code (automatically generated)
  - Expiry date (automatically generated)
  - Card status (ACTIVE, INACTIVE, BLOCKED)
- Card information is securely stored and partially hidden for safety when returned via API.

### 4. Money Transfers
- Users can transfer money between their own accounts or to other users.
- Validation ensures sufficient balance before transfer.
- Transaction history is maintained for reference.
- Future update: Real-time notifications for transfers.

### 5. Balance Management
- Users can view their account balance at any time.
- Balance updates immediately after deposits, withdrawals, or transfers.
- Future update: Scheduled deposits or automated balance reports.

### 6. Payments
- Users can make payments from their bank accounts.
- Payment types can be extended (utilities, bills, etc.).
- Each payment is logged in transaction history for accountability.

### 7. OTP Security (Two-Factor Authentication)
- Optional OTP verification for sensitive operations like large transfers.
- OTP is time-limited and generated per request.
- Integration with Email service for sending OTPs.

### 8. Live Chat System (Future Feature)
- Real-time chat system to communicate with bank support.
- Frontend integration with React and backend using WebSocket/SignalR.
- Aimed for customer support and quick query resolution.

### 9. Admin Features 
- Admin can approve or rejecet card or accounts.
- Admin can view all user accounts, cards, and transactions.
- Admin can block suspicious accounts or cards.(PLANNED)
- Admin dashboard to manage the platform efficiently.

### 10. Error Handling & Validation
- Input validation at API and service levels.
- Proper exception handling with informative messages.
- HTTP status codes used correctly for API responses (e.g., 404 for not found, 400 for bad requests).

---

> All these features currently reside in the `release1` branch. Future stable release with additional features will be merged into the `master` branch.

## Technologies

- **Backend:** Java 17, Spring Boot 3
- **Database:** MySQL
- **Lombok** — reduces boilerplate code
- **Spring Data JPA** — ORM framework
- **Spring Security** — user authentication
- **Liquibase** — database version control
- **TaskScheduler** — for asynchronous tasks

---
