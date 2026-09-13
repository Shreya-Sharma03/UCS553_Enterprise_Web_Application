class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}

class InvalidAmountException extends Exception {
    public InvalidAmountException(String message) {
        super(message);
    }
}

class AccountNotFoundException extends Exception {
    public AccountNotFoundException(String message) {
        super(message);
    }
}

class BankAccount {
    private int accountNumber;
    private double balance;

    public BankAccount(int accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException(
                "Deposit amount must be greater than 0."
            );
        }

        balance += amount;
        System.out.println("Deposit successful.");
        System.out.println("Balance: Rs. " + balance);
    }

    public void withdraw(double amount)
            throws InvalidAmountException, InsufficientBalanceException {

        if (amount <= 0) {
            throw new InvalidAmountException(
                "Withdrawal amount must be greater than 0."
            );
        }

        if (amount > balance) {
            throw new InsufficientBalanceException(
                "Insufficient balance."
            );
        }

        balance -= amount;
        System.out.println("Withdrawal successful.");
        System.out.println("Balance: Rs. " + balance);
    }
}

public class BankingApplication {

    public static BankAccount findAccount(int accountNumber)
            throws AccountNotFoundException {

        if (accountNumber == 101) {
            return new BankAccount(101, 5000);
        }

        throw new AccountNotFoundException(
            "Account " + accountNumber + " not found."
        );
    }

    public static void main(String[] args) {

        try {
            BankAccount account = findAccount(101);

            account.deposit(2000);

            account.withdraw(1500);

            System.out.println("Final Balance: Rs. "
                    + account.getBalance());
        }

        catch (AccountNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }

        catch (InvalidAmountException e) {
            System.out.println("Error: " + e.getMessage());
        }

        catch (InsufficientBalanceException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}