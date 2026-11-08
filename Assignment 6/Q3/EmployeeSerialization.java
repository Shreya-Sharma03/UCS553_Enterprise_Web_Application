
import java.io.*;
import java.util.Scanner;

class Employee implements Serializable {
    int employeeId;
    String name;
    String department;
    double salary;

    Employee(int employeeId, String name, String department, double salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    void display() {
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Name: " + name);
        System.out.println("Department: " + department);
        System.out.println("Salary: " + salary);
        System.out.println();
    }
}

public class EmployeeSerialization {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream("employees.dat"));

            System.out.print("Enter number of employees: ");
            int n = sc.nextInt();
            sc.nextLine();

            for (int i = 1; i <= n; i++) {
                System.out.println("\nEnter details of Employee " + i);

                System.out.print("Enter Employee ID: ");
                int id = sc.nextInt();
                sc.nextLine();

                System.out.print("Enter Name: ");
                String name = sc.nextLine();

                System.out.print("Enter Department: ");
                String department = sc.nextLine();

                System.out.print("Enter Salary: ");
                double salary = sc.nextDouble();
                sc.nextLine();

                Employee e = new Employee(id, name, department, salary);
                out.writeObject(e);
            }

            out.close();

            System.out.println("\nEmployee records serialized successfully.");

            ObjectInputStream in = new ObjectInputStream(
                new FileInputStream("employees.dat"));

            System.out.println("\nEmployee Records:");

            for (int i = 1; i <= n; i++) {
                Employee e = (Employee) in.readObject();
                e.display();
            }

            in.close();

            System.out.println("Employee records deserialized successfully.");

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Input/Output error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Employee class not found: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}
