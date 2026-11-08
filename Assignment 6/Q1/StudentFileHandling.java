
import java.io.*;
import java.util.Scanner;

public class StudentFileHandling {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            FileWriter fw = new FileWriter("students.txt");

            System.out.print("Enter number of students: ");
            int n = sc.nextInt();
            sc.nextLine();

            for (int i = 1; i <= n; i++) {
                System.out.println("\nEnter details of Student " + i);

                System.out.print("Enter Roll Number: ");
                String roll = sc.nextLine();

                System.out.print("Enter Name: ");
                String name = sc.nextLine();

                System.out.print("Enter Marks: ");
                String marks = sc.nextLine();

                fw.write("Roll Number: " + roll + "\n");
                fw.write("Name: " + name + "\n");
                fw.write("Marks: " + marks + "\n\n");
            }

            fw.close();

            System.out.println("\nStudent records saved successfully.");

            FileReader fr = new FileReader("students.txt");
            BufferedReader br = new BufferedReader(fr);

            System.out.println("\nStudent Records:");

            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

            br.close();
            fr.close();

            FileOutputStream fos = new FileOutputStream("copy.txt");
            FileInputStream fis = new FileInputStream("sample.dat");

            int data;
            while ((data = fis.read()) != -1) {
                fos.write(data);
            }

            fis.close();
            fos.close();

            System.out.println("\nBinary file copied successfully.");

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Input/Output error: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}
