package library.service;
import library.model.LibraryResource;
import library.model.Printable;

public class LibraryService{
    public void displayAllResources(LibraryResource[] resources){
        for (LibraryResource resource:resources){
            Printable printable=(Printable) resource;
            printable.printDetails();
        }
    }
    public double calculateTotalFine(LibraryResource[] resources, int[] overdueDays){
        double totalFine=0;
        for (int i=0; i<resources.length; i++){
            totalFine+=resources[i].calculateFine(overdueDays[i]);
        }
        return totalFine;
    }
}
