import java.util.Optional;
public class OptionalEmailDemo{
    public static void main(String[] args){
        Optional<String> email=Optional.of("shreya@gmail.com");
        Optional<String> emptyEmail=Optional.empty();
        System.out.println("Student email:");
        email.ifPresent(e->System.out.println(e));
        String defaultEmail=emptyEmail.orElse("notprovided@gmail.com");
        try{
            String actualEmail=emptyEmail.orElseThrow(
                ()->new RuntimeException("Email is missing!")
            );
            System.out.println("Email: " +actualEmail);
        }
        catch(RuntimeException e){
            System.out.println("Error: "+ e.getMessage());
        }
    }
}