
import java.io.*;

public class LogAnalyzer {
    public static void main(String[] args) {
        int totalEntries = 0;
        int errorCount = 0;
        int warningCount = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader("application.log"));
            BufferedWriter bw = new BufferedWriter(new FileWriter("filtered.log"));

            String line;

            while ((line = br.readLine()) != null) {
                totalEntries++;

                if (line.contains("ERROR")) {
                    errorCount++;
                    bw.write(line);
                    bw.newLine();
                } 
                else if (line.contains("WARNING")) {
                    warningCount++;
                    bw.write(line);
                    bw.newLine();
                }
            }

            br.close();
            bw.close();

            System.out.println("Log Analysis Completed Successfully.");
            System.out.println("Total Log Entries: " + totalEntries);
            System.out.println("Total ERROR Entries: " + errorCount);
            System.out.println("Total WARNING Entries: " + warningCount);
            System.out.println("Filtered logs saved in filtered.log");

        } catch (FileNotFoundException e) {
            System.out.println("Log file not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Input/Output error: " + e.getMessage());
        }
    }
}
