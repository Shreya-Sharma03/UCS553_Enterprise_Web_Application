import java.util.Optional;
import java.util.Objects;
class Result<T extends Number>{
    private T[] marks;
    public Result(T[] marks){
        this.marks=Objects.requireNonNull(marks,"Marks cannot be null");
    }
    public double calculateAverage(){
        double total=0;
        for(T mark: marks){
            total=total+mark.doubleValue();
        }
        return total/marks.length;
    }
    public boolean hasPassed(double threshold){
        Objects.requireNonNull(marks,"Marks cannot be null");
        return calculateAverage()>=threshold;
    }
    public Optional<T[]> getMarks() {
        return Optional.ofNullable(marks);
    }
}
public class ResultDemo {
    public static void main(String[] args) {
        Integer[] studentMarks = {80, 70, 90};
        Result<Integer> result = new Result<>(studentMarks);
        result.getMarks().ifPresent(marks -> {
            System.out.println("Student marks:");
            for (Integer mark : marks) {
                System.out.println(mark);
            }
            System.out.println(
                "Average: " + result.calculateAverage()
            );
            if (result.hasPassed(40)) {
                System.out.println("Result: Pass");
            } else {
                System.out.println("Result: Fail");
            }
        });
    }
}