import java.util.Scanner;
import java.util.InputMismatchException; 
class InvalidOperatorException extends Exception{
    public InvalidOperatorException(String message){
        super(message);
    }
}
public class Calculator {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        try{
            System.out.print("Enter first number: ");
            double num1=sc.nextDouble();
            System.out.println("Enter operator (+,-,*,/): ");
            char operator=sc.next().charAt(0);
            System.out.println("Enter second number: ");
            double num2=sc.nextDouble();
            double result;
            switch(operator){
                case '+':
                    result=num1+num2;
                    break;
                case '-':
                    result=num1-num2;
                    break;
                case '*':
                    result=num1*num2;
                    break;
                case '/':
                    if(num2==0){
                        throw new ArithmeticException("Cannot divide by zero");
                    }
                    result=num1/num2;
                    break;
                default:
                    throw new InvalidOperatorException("Invalid operator");
            }
            System.out.println("Result = "+result);
        }
        catch(InputMismatchException e){
            System.out.println("Error: Invalid numeric input");
        }
        catch(ArithmeticException e){
            System.out.println("Error: "+e.getMessage());
        }
        catch(InvalidOperatorException e){
            System.out.println("Error: "+ e.getMessage());
        }
        sc.close();
    }
}
