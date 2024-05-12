import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class BankAccount {
    private String accountNumber;
    private String pin;
    private double balance;

    public BankAccount(String accountNumber, String pin, double balance) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getPin() {
        return pin;
    }

    public double getBalance() {
        return balance;
    }

    public void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient funds!");
        } else {
            balance -= amount;
            System.out.println("Withdrawal successful. Remaining balance: $" + balance);
        }
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposit successful. New balance: $" + balance);
    }

    public void transfer(BankAccount recipient, double amount) {
        if (amount > balance) {
            System.out.println("Insufficient funds for transfer!");
        } else {
            balance -= amount;
            recipient.deposit(amount);
            System.out.println("Transfer successful. Remaining balance: $" + balance);
        }
    }
}

class ATM {
    private Map<String, BankAccount> accounts;

    public ATM() {
        // Initialize accounts
        accounts = new HashMap<>();
        accounts.put("123456", new BankAccount("123456", "1234", 1000.0));
        accounts.put("789012", new BankAccount("789012", "5678", 500.0));
    }

    public void displayMenu() {
        System.out.println("Welcome to the ATM!");
        System.out.println("1. Withdraw cash");
        System.out.println("2. Check balance");
        System.out.println("3. Deposit money");
        System.out.println("4. Transfer funds");
        System.out.println("5. Exit");
    }

    public void processTransaction(String accountNumber, String pin) {
        Scanner scanner = new Scanner(System.in);
        BankAccount account = accounts.get(accountNumber);

        if (account == null || !account.getPin().equals(pin)) {
            System.out.println("Invalid account number or PIN.");
            return;
        }

        System.out.println("Login successful. Welcome, " + accountNumber + "!");

        while (true) {
            displayMenu();
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter amount to withdraw: $");
                    double withdrawAmount = scanner.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;
                case 2:
                    System.out.println("Your balance: $" + account.getBalance());
                    break;
                case 3:
                    System.out.print("Enter amount to deposit: $");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 4:
                    System.out.print("Enter recipient account number: ");
                    String recipientAccountNumber = scanner.next();
                    BankAccount recipient = accounts.get(recipientAccountNumber);
                    if (recipient == null) {
                        System.out.println("Recipient account not found.");
                        break;
                    }
                    System.out.print("Enter amount to transfer: $");
                    double transferAmount = scanner.nextDouble();
                    account.transfer(recipient, transferAmount);
                    break;
                case 5:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

public class ATMmachine {
    public static void main(String[] args) {
        ATM atm = new ATM();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter account number: ");
        String accountNumber = scanner.next();
        System.out.print("Enter PIN: ");
        String pin = scanner.next();

        atm.processTransaction(accountNumber, pin);
    }
}
