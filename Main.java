import java.util.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Main {
    /**
     * Creates a new bank account based on the specified type with the given initial deposit amount.
     *
     * @param scanner The scanner object used for input.
     * @throws NumberFormatException If the initial deposit amount is not a valid float.
     */
    public static void create(Scanner scanner) {
        // Extract account type, account name, and initial deposit amount from input
        String accountType = scanner.next();
        String accountName = scanner.next();
        float initialDeposit = Float.parseFloat(scanner.nextLine().trim());

        BankingSystem system = BankingSystem.getInstance();

        // Create the account based on the specified type
        system.createAccount(accountType, accountName, initialDeposit);
    }

    /**
     * Deposits a specified amount into the account with the given name.
     *
     * @param scanner The scanner object used for input.
     * @throws NumberFormatException If the input amount is not a valid float.
     */
    public static void deposit(Scanner scanner) {
        String accountName = scanner.next();
        float depositAmount = Float.parseFloat(scanner.nextLine().trim());

        BankingSystem system = BankingSystem.getInstance();
        system.deposit(accountName, depositAmount);
    }

    /**
     * Withdraws a specified amount from the given bank account.
     *
     * @param scanner The scanner object used for input.
     * @throws NumberFormatException If the withdrawal amount is not a valid float.
     */
    public static void withdraw(Scanner scanner) {
        // Extract account name and withdrawal amount from input
        String accountName = scanner.next();
        float withdrawalAmount = Float.parseFloat(scanner.nextLine().trim());

        BankingSystem system = BankingSystem.getInstance();
        system.withdraw(accountName, withdrawalAmount);
    }

    public static void transfer(Scanner scanner) {
        String fromAccountName = scanner.next();
        String toAccountName = scanner.next();
        float transferAmount = Float.parseFloat(scanner.nextLine().trim());

        BankingSystem system = BankingSystem.getInstance();

        // Transfer the specified amount between specified accounts
        system.transfer(transferAmount, fromAccountName, toAccountName);
    }

    /**
     * Displays the details of a specified bank account.
     *
     * @param scanner The scanner object used for input.
     */
    public static void view(Scanner scanner) {
        String accountName = scanner.next();

        BankingSystem system = BankingSystem.getInstance();
        system.viewAccount(accountName);
    }

    /**
     * Deactivates the specified bank account for withdraw operation.
     *
     * @param scanner The scanner object used for input.
     */
    public static void deactivate(Scanner scanner) {
        String accountName = scanner.next();

        BankingSystem system = BankingSystem.getInstance();
        system.deactivateAccount(accountName);
    }

    /**
     * Activates the specified bank account.
     *
     * @param scanner The scanner object used for input.
     */
    public static void activate(Scanner scanner) {
        String accountName = scanner.next();

        BankingSystem system = BankingSystem.getInstance();
        system.activateAccount(accountName);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < n; i++) {
            String typeOfOperation = scanner.next();

            if (typeOfOperation.equals("Create")) {
                String typeOfObject = scanner.next();
                create(scanner);
            } else if (typeOfOperation.equals("Deposit")) {
                deposit(scanner);
            } else if (typeOfOperation.equals("Withdraw")) {
                withdraw(scanner);
            } else if (typeOfOperation.equals("Transfer")) {
                transfer(scanner);
            } else if (typeOfOperation.equals("View")) {
                view(scanner);
            } else if (typeOfOperation.equals("Deactivate")) {
                deactivate(scanner);
            } else if (typeOfOperation.equals("Activate")) {
                activate(scanner);
            }
        }
    }
}

/**
 * The BankingSystemProxy interface represents a proxy for accessing the banking system's functionality.
 * It defines methods for creating accounts, depositing and withdrawing funds, transferring funds between accounts,
 * viewing account details, deactivating and activating accounts.
 */
interface BankingSystemProxy {
    /**
     * Creates a new bank account with the specified type, name, and initial deposit amount.
     *
     * @param accountType     The type of the account (e.g., "Savings", "Checking", "Business").
     * @param accountName     The name of the account.
     * @param initialDeposit The initial deposit amount for the account.
     */
    void createAccount(String accountType, String accountName, float initialDeposit);

    /**
     * Deposits a specified amount into the specified account.
     *
     * @param accountName The name of the account to deposit funds into.
     * @param amount      The amount to deposit.
     */
    void deposit(String accountName, float amount);

    /**
     * Withdraws a specified amount from the specified account.
     *
     * @param accountName The name of the account to withdraw funds from.
     * @param amount      The amount to withdraw.
     */
    void withdraw(String accountName, float amount);

    /**
     * Transfers funds from one account to another.
     *
     * @param amount          The amount to transfer.
     * @param fromAccountName The name of the account to transfer funds from.
     * @param toAccountName   The name of the account to transfer funds to.
     */
    void transfer(float amount, String fromAccountName, String toAccountName);

    /**
     * Displays the details of the specified account.
     *
     * @param accountName The name of the account to view.
     */
    void viewAccount(String accountName);

    /**
     * Deactivates the specified account.
     *
     * @param accountName The name of the account to deactivate.
     */
    void deactivateAccount(String accountName);

    /**
     * Activates the specified account.
     *
     * @param accountName The name of the account to activate.
     */
    void activateAccount(String accountName);
}

/**
 * The BankingSystem class represents a banking system that manages bank accounts and facilitates transactions.
 */
class BankingSystem implements BankingSystemProxy {
    /**
     * List of accounts managed by the banking system.
     */
    public List<Account> accounts;

    /**
     * Singleton instance of the BankingSystem class.
     */
    private static BankingSystem instance;

    /**
     * Private constructor to prevent external instantiation.
     * Initializes the accounts list.
     */
    private BankingSystem() {
        accounts = new ArrayList<>();
    }

    /**
     * Retrieves the singleton instance of the BankingSystem class.
     *
     * @return The singleton instance of the BankingSystem class.
     */
    public static BankingSystem getInstance() {
        if (instance == null) {
            instance = new BankingSystem();
        }
        return instance;
    }

    /**
     * Creates a new bank account with the specified type, name, and initial deposit amount.
     *
     * @param accountType     The type of the account (e.g., "Savings", "Checking", "Business").
     * @param accountName     The name of the account.
     * @param initialDeposit  The initial deposit amount for the account.
     */
    @Override
    public void createAccount(String accountType, String accountName, float initialDeposit) {
        switch (accountType) {
            case "Savings" -> {
                SavingsAccount account = new SavingsAccount(accountName, initialDeposit);
                accounts.add(account);
            }
            case "Checking" -> {
                CheckingAccount account = new CheckingAccount(accountName, initialDeposit);
                accounts.add(account);
            }
            case "Business" -> {
                BusinessAccount account = new BusinessAccount(accountName, initialDeposit);
                accounts.add(account);
            }
        }
    }

    /**
     * Deposits a specified amount into the specified account.
     *
     * @param accountName  The name of the account to deposit funds into.
     * @param amount       The amount to deposit.
     */
    @Override
    public void deposit(String accountName, float amount) {
        if (!findAccount(accountName)) {
            System.out.println("Error: Account " + accountName + " does not exist.");
            return;
        }
        Account account = getAccount(accountName);
        account.deposit(amount);
    }

    /**
     * Withdraws a specified amount from the specified account.
     *
     * @param accountName  The name of the account to withdraw funds from.
     * @param amount       The amount to withdraw.
     */
    @Override
    public void withdraw(String accountName, float amount) {
        if (!findAccount(accountName)) {
            System.out.println("Error: Account " + accountName + " does not exist.");
            return;
        }
        Account account = getAccount(accountName);
        account.withdraw(amount);
    }

    /**
     * Transfers funds from one account to another.
     *
     * @param amount          The amount to transfer.
     * @param fromAccountName The name of the account to transfer funds from.
     * @param toAccountName   The name of the account to transfer funds to.
     */
    @Override
    public void transfer(float amount, String fromAccountName, String toAccountName) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        symbols.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("0.000", symbols);
        if (!findAccount(fromAccountName)) {
            System.out.println("Error: Account " + fromAccountName + " does not exist.");
            return;
        } else if (!findAccount(toAccountName)) {
            System.out.println("Error: Account " + toAccountName + " does not exist.");
            return;
        }

        Account from = getAccount(fromAccountName);
        Account to = getAccount(toAccountName);

        if (from.getState().equals("Inactive")) {
            System.out.println("Error: Account " + fromAccountName + " is inactive.");
            return;
        }

        if (from.getBalance() < amount) {
            System.out.println("Error: Insufficient funds for " + fromAccountName + ".");
            return;
        }

        float feeAmount = amount * from.transactionFeeRate;
        float amountAfterFee = amount - feeAmount;

        from.setBalance(from.getBalance() - amount);
        to.setBalance(to.getBalance() + amountAfterFee);

        System.out.println(fromAccountName + " successfully transferred $" + df.format(amountAfterFee) + " to " + toAccountName +
                ". New Balance: $" + df.format(from.getBalance()) + ". Transaction Fee: $" + df.format(feeAmount) +
                " (" + from.transactionFeeRate*100 + "%) in the system.");
        from.addOperation("Transfer $" + df.format(amount));
    }

    @Override
    public void viewAccount(String accountName) {
        if (!findAccount(accountName)) {
            System.out.println("Error: Account " + accountName + " does not exist.");
            return;
        }
        Account account = getAccount(accountName);
        account.view();
    }

    @Override
    public void deactivateAccount(String accountName) {
        if (!findAccount(accountName)) {
            System.out.println("Error: Account " + accountName + " does not exist.");
            return;
        }
        Account account = getAccount(accountName);
        account.deactivate();
    }

    @Override
    public void activateAccount(String accountName) {
        if (!findAccount(accountName)) {
            System.out.println("Error: Account " + accountName + " does not exist.");
            return;
        }
        Account account = getAccount(accountName);
        account.activate();
    }

    /**
     * Checks if an account with the specified name exists in the system.
     *
     * @param accountName The name of the account to check.
     * @return True if the account exists, false otherwise.
     */
    public boolean findAccount(String accountName) {
        for (Account acc : accounts) {
            if (acc.getAccountName().equals(accountName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retrieves the account with the specified name from the system.
     *
     * @param accountName The name of the account to retrieve.
     * @return The account object if found, null otherwise.
     */
    public Account getAccount(String accountName) {
        for (Account acc : accounts) {
            if (acc.getAccountName().equals(accountName)) {
                return acc;
            }
        }
        return null;
    }

    /**
     * Adds a new account to the banking system.
     *
     * @param acc The account to add.
     */
    public void addAccount(Account acc) {
        accounts.add(acc);
    }
}

/**
 * The AccountState interface defines methods for managing the state of an account.
 */
interface AccountState {
    void activate();
    void deactivate();
}

/**
 * The Account class represents a bank account.
 */
abstract class Account {
    /**
     * List of operations performed on the account.
     */
    protected List<String> operations;

    /**
     * The balance of the account.
     */
    private float balance;

    /**
     * The transaction fee rate for the account.
     */
    protected float transactionFeeRate;

    /**
     * The name of the account.
     */
    private String accountName;

    /**
     * The state of the account.
     */
    protected String state;

    /**
     * Constructs an Account object with the specified account name and initial deposit.
     *
     * @param accountName    The name of the account.
     * @param initialDeposit The initial deposit amount.
     */
    protected Account(String accountName, float initialDeposit) {
        this.accountName = accountName;
        this.balance = initialDeposit;
        this.state = "Active";
        this.operations = new ArrayList<>();
    }

    /**
     * Deposits funds into the account.
     *
     * @param amount The amount to deposit.
     */
    public void deposit(float amount) {
        balance += amount;
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        symbols.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("0.000", symbols);
        System.out.println(accountName + " successfully deposited $" + df.format(amount) +
                ". New Balance: $" + df.format(balance) + ".");
        operations.add("Deposit $" + df.format(amount));
    }

    /**
     * Withdraws funds from the account.
     *
     * @param amount The amount to withdraw.
     */
    public void withdraw(float amount) {
        if (getState().equals("Inactive")) {
            System.out.println("Error: Account " + accountName + " is inactive.");
            return;
        }
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        symbols.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("0.000", symbols);
        float amountOfFee = amount * transactionFeeRate;
        float amountAfterFee = amount - amountOfFee;

        if (amount > balance) {
            System.out.println("Error: Insufficient funds for " + accountName + ".");
            return;
        }
        balance -= amount;
        System.out.println(accountName + " successfully withdrew $" + df.format(amountAfterFee) +
                ". New Balance: $" + df.format(balance) + ". Transaction Fee: $" + df.format(amountOfFee) +
                " (" + transactionFeeRate*100 + "%) in the system.");
        operations.add("Withdrawal $" + df.format(amount));
    }

    /**
     * Adds an operation to the list of operations performed on the account.
     *
     * @param operation The operation to add.
     */
    public void addOperation(String operation) {
        operations.add(operation);
    }

    public String getAccountName() {
        return accountName;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float newBalance) {
        this.balance = newBalance;
    }

    public String getState() {
        return state;
    }

    public void view() {}
    public void activate() {}
    public void deactivate() {}
}

/**
 * The SavingsAccount class represents a savings account.
 */
class SavingsAccount extends Account implements AccountState {
    /**
     * Constructs a SavingsAccount object with the specified account name and initial balance.
     *
     * @param accountName    The name of the account.
     * @param initialBalance The initial balance of the account.
     */
    public SavingsAccount(String accountName, float initialBalance) {
        super(accountName, initialBalance);
        this.transactionFeeRate = 0.015F;
        creationPrint();
    }

    /**
     * Prints a message indicating the creation of the savings account.
     */
    private void creationPrint() {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        symbols.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("0.000", symbols);
        System.out.println("A new Savings account created for " + getAccountName() +
                " with an initial balance of $" + df.format(getBalance()) + ".");
        operations.add("Initial Deposit $" + df.format(getBalance()));
    }

    /**
     * Displays information about the savings account.
     */
    @Override
    public void view() {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        symbols.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("0.000", symbols);
        System.out.print(getAccountName() + "'s Account: Type: Savings, Balance: $" +
                df.format(getBalance()) + ", State: " + getState() + ", Transactions: [");
        for (int i = 0; i < operations.size(); i++) {
            if (i == operations.size() - 1) {
                System.out.print(operations.get(i) + "].");
            } else {
                System.out.print(operations.get(i) + ", ");
            }
        }
        System.out.println();
    }

    @Override
    public void activate() {
        if (state.equals("Active")) {
            System.out.println("Error: Account " + getAccountName() + " is already activated.");
            return;
        }
        state = "Active";
        System.out.println(getAccountName() + "'s account is now activated.");
    }

    @Override
    public void deactivate() {
        if (state.equals("Inactive")) {
            System.out.println("Error: Account " + getAccountName() + " is already deactivated.");
            return;
        }
        state = "Inactive";
        System.out.println(getAccountName() + "'s account is now deactivated.");
    }
}

/**
 * The CheckingAccount class represents a checking account.
 */
class CheckingAccount extends Account implements AccountState {
    /**
     * Constructs a CheckingAccount object with the specified account name and initial balance.
     *
     * @param accountName    The name of the account.
     * @param initialBalance The initial balance of the account.
     */
    public CheckingAccount(String accountName, float initialBalance) {
        super(accountName, initialBalance);
        this.transactionFeeRate = 0.02F;
        creationPrint();
    }

    /**
     * Prints a message indicating the creation of the checking account.
     */
    private void creationPrint() {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        symbols.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("0.000", symbols);
        System.out.println("A new Checking account created for " + getAccountName() +
                " with an initial balance of $" + df.format(getBalance()) + ".");
        operations.add("Initial Deposit $" + df.format(getBalance()));
    }

    @Override
    public void view() {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        symbols.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("0.000", symbols);
        System.out.print(getAccountName() + "'s Account: Type: Checking, Balance: $" +
                df.format(getBalance()) + ", State: " + getState() + ", Transactions: [");
        for (int i = 0; i < operations.size(); i++) {
            if (i == operations.size() - 1) {
                System.out.print(operations.get(i) + "].");
            } else {
                System.out.print(operations.get(i) + ", ");
            }
        }
        System.out.println();
    }

    @Override
    public void activate() {
        if (state.equals("Active")) {
            System.out.println("Error: Account " + getAccountName() + " is already activated.");
            return;
        }
        state = "Active";
        System.out.println(getAccountName() + "'s account is now activated.");
    }

    @Override
    public void deactivate() {
        if (state.equals("Inactive")) {
            System.out.println("Error: Account " + getAccountName() + " is already deactivated.");
            return;
        }
        state = "Inactive";
        System.out.println(getAccountName() + "'s account is now deactivated.");
    }
}

/**
 * The BusinessAccount class represents a business account.
 */
class BusinessAccount extends Account implements AccountState {
    /**
     * Constructs a BusinessAccount object with the specified account name and initial balance.
     *
     * @param accountName    The name of the account.
     * @param initialBalance The initial balance of the account.
     */
    public BusinessAccount(String accountName, float initialBalance) {
        super(accountName, initialBalance);
        this.transactionFeeRate = 0.025F;
        creationPrint();
    }

    /**
     * Prints a message indicating the creation of the business account.
     */
    private void creationPrint() {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        symbols.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("0.000", symbols);
        System.out.println("A new Business account created for " + getAccountName() +
                " with an initial balance of $" + df.format(getBalance()) + ".");
        operations.add("Initial Deposit $" + df.format(getBalance()));
    }

    @Override
    public void view() {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        symbols.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("0.000", symbols);
        System.out.print(getAccountName() + "'s Account: Type: Business, Balance: $" +
                df.format(getBalance()) + ", State: " + getState() + ", Transactions: [");
        for (int i = 0; i < operations.size(); i++) {
            if (i == operations.size() - 1) {
                System.out.print(operations.get(i) + "].");
            } else {
                System.out.print(operations.get(i) + ", ");
            }
        }
        System.out.println();
    }

    @Override
    public void activate() {
        if (state.equals("Active")) {
            System.out.println("Error: Account " + getAccountName() + " is already activated.");
            return;
        }
        state = "Active";
        System.out.println(getAccountName() + "'s account is now activated.");
    }

    @Override
    public void deactivate() {
        if (state.equals("Inactive")) {
            System.out.println("Error: Account " + getAccountName() + " is already deactivated.");
            return;
        }
        state = "Inactive";
        System.out.println(getAccountName() + "'s account is now deactivated.");
    }
}
