import java.util.Scanner;
class InvalidMarksException extends Exception{
    public InvalidMarksException(String message){
        super(message);
    }
}
public class StudentResult{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        try{
            System.out.println("Enter number of subjects: ");
            int n=sc.nextInt();
        int[] marks=new int[n];
        int total=0;
        for(int i=0; i<n; i++){
            System.out.println("Enter marks for subject "+(i+1)+": ");
            marks[i]=sc.nextInt();
            if(marks[i]<0 || marks[i]>100){
                throw new InvalidMarksException("Invalid marks: Marks must be between 0 and 100");
            }
                total+=marks[i];
            }
            double percentage=(double) total/n;
            char grade;
            if(percentage>=90)
                grade='A';
            else if (percentage>=75)
                grade='B';
            else if(percentage>=60)
                grade='C';
            else if(percentage>=40)
                grade='D';
            else
                grade='F';
            System.out.println("\nTotal = " + total);
            System.out.println("Percentage = " + percentage);
            System.out.println("Grade = " + grade);
        }
        catch(InvalidMarksException e){
            System.out.println("Error: " +e.getMessage());
        }
        sc.close();
    }
}
