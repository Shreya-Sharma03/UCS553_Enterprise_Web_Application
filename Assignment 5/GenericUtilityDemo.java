
import java.util.Optional;
import java.util.Objects;

class GenericUtility {
    public static <T> boolean isEqual(T a, T b) {
        return Objects.equals(a, b);
    }
    public static <T> T getDefault(Optional<T> value, T defaultValue) {
        return value.orElse(defaultValue);
    }
    public static <T extends Comparable<T>> T max(T a, T b) {
        if (a.compareTo(b) >= 0) {
            return a;
        } else {
            return b;
        }
    }
    public static boolean isNull(Object obj) {
        return Objects.isNull(obj);
    }
}

public class GenericUtilityDemo {
    public static void main(String[] args) {

        System.out.println("Are strings equal? " +
                GenericUtility.isEqual("Hello", "Hello"));
        System.out.println("Are integers equal? " +
                GenericUtility.isEqual(10, 20));
        Optional<String> name = Optional.of("Shreya");
        Optional<String> emptyName = Optional.empty();

        System.out.println("Name: " +
                GenericUtility.getDefault(name, "Guest"));
        System.out.println("Default name: " +
                GenericUtility.getDefault(emptyName, "Guest"));
        System.out.println("Maximum integer: " +
                GenericUtility.max(10, 25));
        System.out.println("Maximum string: " +
                GenericUtility.max("Apple", "Banana"));
        System.out.println("Maximum double: " +
                GenericUtility.max(12.5, 8.5));
        String text = null;
        String message = "Java Programming";
        System.out.println("Is text null? " +
                GenericUtility.isNull(text));
        System.out.println("Is message null? " +
                GenericUtility.isNull(message));
    }
}
