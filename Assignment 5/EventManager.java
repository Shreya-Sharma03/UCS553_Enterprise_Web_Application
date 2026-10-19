
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.Scanner;

public class EventManager {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter event name: ");
        String eventName = sc.nextLine();
        System.out.print("Enter event date (yyyy-mm-dd): ");
        LocalDate eventDate = LocalDate.parse(sc.nextLine());
        System.out.print("Enter event start time (HH:mm): ");
        LocalTime startTime = LocalTime.parse(sc.nextLine());
        System.out.print("Enter venue name (or press Enter to skip): ");
        String venueInput = sc.nextLine();
        Optional<String> venue;

        if (venueInput.isEmpty()) {
            venue = Optional.empty();
        } else {
            venue = Optional.of(venueInput);
        }
        System.out.println("\n--- Event Details ---");
        System.out.println("Event Name: " + eventName);
        System.out.println("Event Date: " + eventDate);
        System.out.println("Start Time: " + startTime);

        System.out.println(
            "Venue: " + venue.orElse("Venue not specified")
        );
        LocalDate today = LocalDate.now();
        if (eventDate.isAfter(today)) {
            System.out.println("Event Status: Future event");
        } else if (eventDate.isEqual(today)) {
            System.out.println("Event Status: Event is today");
        } else {
            System.out.println("Event Status: Event is in the past");
        }
        long daysRemaining = ChronoUnit.DAYS.between(today, eventDate);
        System.out.println("Days remaining: " + daysRemaining);
        LocalTime endTime = startTime.plusHours(2);
        System.out.println("End Time: " + endTime);

        sc.close();
    }
}
