# Simplified Banking System

A console-based banking system demonstrating key software design patterns in Java. The application allows creating different types of bank accounts, performing transactions (deposit, withdraw, transfer), and managing account states (activate, deactivate).

## Features

* **Account Types:** Savings, Checking, and Business accounts, each with its own transaction fee rate.

* **Create Account:** Open new accounts with an initial deposit.

* **Deposit & Withdraw:** Add or remove funds, with fees applied on withdrawals.

* **Transfer:** Send money between accounts, deducting transaction fees from the sender.

* **View Account:** Display balance, state (Active/Inactive), and transaction history.

* **Activate/Deactivate:** Enable or disable accounts to control operations.

## Design Patterns

| Pattern       | Purpose                                                                                |
| ------------- | -------------------------------------------------------------------------------------- |
| **Singleton** | `BankingSystem` ensures a single instance manages all accounts.                |
| **Proxy**   | `BankingSystemProxy` defines the interface for banking operations.               |
| **State**  | `AccountState` interface handles activate/deactivate behavior.           |
| **Template**  | Abstract `Account` class provides shared logic for all accounts. |

## How to Run

1. Clone the Repository

   ```bash
   git clone https://github.com/yourusername/Simplified-Banking-System.git
   cd Simplified-Banking-System
   ```

2. Compile and Run

   ```bash
   javac Main.java
   java Main
   ```

3. Input Commands
   
  * First line: number of operations n.

  * Next n lines: commands in one of the following formats:
  
      1. `Create <AccountType> <AccountName> <InitialDeposit>`
      
      2. `Deposit <AccountName> <Amount>`
      
      3. `Withdraw <AccountName> <Amount>`
      
      4. `Transfer <FromAccount> <ToAccount> <Amount>`
      
      5. `View <AccountName>`
      
      6. `Deactivate <AccountName>`
      
      7. `Activate <AccountName>`

## Example Session

  ```text
  7
  Create Savings Alice 1000.00
  Deposit Alice 200.00
  Create Checking Bob 500.00
  Transfer Alice Bob 150.00
  Withdraw Bob 50.00
  Deactivate Alice
  View Alice
  ```

Output:

  ```text
  A new Savings account created for Alice with an initial balance of $1000.000.
  Alice successfully deposited $200.000. New Balance: $1200.000.
  A new Checking account created for Bob with an initial balance of $500.000.
  Alice successfully transferred $147.000 to Bob. New Balance: $1050.000. Transaction Fee: $3.000 (0.015%) in the system.
  Bob successfully withdrew $49.000. New Balance: $696.000. Transaction Fee: $1.000 (0.02%) in the system.
  Alice's account is now deactivated.
  Alice's Account: Type: Savings, Balance: $1050.000, State: Inactive, Transactions: [Initial Deposit $1000.000, Deposit $200.000, Transfer   $150.000].
  ```

## Project Structure

  ```text
.
├── Main.java             # Entry point, parses and delegates commands
├── BankingSystemProxy.java  # Interface defining banking operations
├── BankingSystem.java    # Singleton implementation of proxy, manages accounts
├── Account.java          # Abstract base class with common logic
├── AccountState.java     # Interface for activate/deactivate behavior
├── SavingsAccount.java   # Savings account implementation
├── CheckingAccount.java  # Checking account implementation
├── BusinessAccount.java  # Business account implementation
└── DecimalFormatSymbols  # Utility for formatting monetary values
  ```
