class InvalidUsernameException extends Exception {
    public InvalidUsernameException(String message) {
        super(message);
    }
}

class InvalidPasswordException extends Exception {
    public InvalidPasswordException(String message) {
        super(message);
    }
}

class AccountLockedException extends Exception {
    public AccountLockedException(String message) {
        super(message);
    }
}

public class LoginSystem {

    static String validUsername = "admin";
    static String validPassword = "1234";
    static int failedAttempts = 0;
    static final int MAX_ATTEMPTS = 3;
    static boolean locked = false;

    public static void login(String username, String password)
            throws InvalidUsernameException, InvalidPasswordException, AccountLockedException {

        if (locked) {
            throw new AccountLockedException("Account is locked.");
        }

        if (!username.equals(validUsername)) {
            failedAttempts++;
            throw new InvalidUsernameException("Invalid username.");
        }

        if (!password.equals(validPassword)) {
            failedAttempts++;

            if (failedAttempts >= MAX_ATTEMPTS) {
                locked = true;
                throw new AccountLockedException("Maximum failed attempts reached. Account locked.");
            }

            throw new InvalidPasswordException("Invalid password.");
        }

        failedAttempts = 0;
        System.out.println("Login successful.");
    }

    public static void main(String[] args) {

        String[] usernames = {"admin", "admin", "admin", "admin"};
        String[] passwords = {"1111", "2222", "3333", "1234"};

        for (int i = 0; i < usernames.length; i++) {

            try {
                login(usernames[i], passwords[i]);
                break;
            }
            catch (InvalidUsernameException e) {
                System.out.println(e.getMessage());
            }
            catch (InvalidPasswordException e) {
                System.out.println(e.getMessage());
            }
            catch (AccountLockedException e) {
                System.out.println(e.getMessage());
                break;
            }
            finally {
                System.out.println("Attempt completed.\n");
            }
        }
    }
}