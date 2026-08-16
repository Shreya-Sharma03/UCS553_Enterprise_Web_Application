package library.main;

import library.model.Book;
import library.model.DigitalResource;
import library.model.LibraryResource;
import library.service.LibraryService;
import library.util.InputValidator;

public class Main{
    public static void main(String[] args){
        LibraryResource.setLibraryName("Smart Central Library");
        LibraryResource[] resources=new LibraryResource[5];
        resources[0]=new Book(
                101,
                "Java Programming",
                "James Gosling"
        );
        resources[1]=new Book(
                102,
                "Database Management Systems",
                "Raghu Ramakrishnan"
        );
        resources[2]=new DigitalResource(
                103,
                "Machine Learning Course",
                "Andrew Ng"
        );
        resources[3]=new Book(
                104,
                "Operating Systems",
                "Abraham Silberschatz"
        );
        resources[4]=new DigitalResource(
                105,
                "Cloud Computing",
                "Thomas Erl"
        );
        int[] overdueDays={5, 3, 10, 2, 7};
        for (int i=0; i<resources.length; i++){
            if (!InputValidator.validateResourceId(
                    resources[i].getResourceId())){
                System.out.println("Invalid Resource ID: "+resources[i].getResourceId());
            }
            if (!InputValidator.validateFineDays(overdueDays[i])){
                System.out.println("Invalid overdue days for Resource ID: "+resources[i].getResourceId());
            }
        }
        System.out.println("========== SMART LIBRARY RESOURCE MANAGEMENT SYSTEM ==========");
        System.out.println("Library Name: "+LibraryResource.getLibraryName());
        LibraryService libraryService=new LibraryService();
        libraryService.displayAllResources(resources);
        double totalFine=libraryService.calculateTotalFine(resources, overdueDays);
        System.out.println("----------------------------------------");
        System.out.println("Total Fine of All Resources: Rs. "+totalFine);
        LibraryResource.displayTotalResources();
    }
}
