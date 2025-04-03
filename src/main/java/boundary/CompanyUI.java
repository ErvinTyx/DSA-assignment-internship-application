package boundary;

import java.util.Scanner;
import control.CompanyManager;

public class CompanyUI {
    private CompanyManager companyManager = new CompanyManager();
    private Scanner input = new Scanner(System.in);

    public void displayMenu() {
        System.out.println("\n\n\nCompany Menu:");
        System.out.println("1. Register Company");
        System.out.println("2. Remove Company information by ID");
        System.out.println("3. Update Company information by ID");
        System.out.println("4. Manage Job Posting");
        System.out.println("5. List All Companies");
        System.out.println("6. Filter Companies");
        System.out.println("7. Exit");
    }

    public static void main(String[] args) {
        CompanyUI companyUI = new CompanyUI();
        companyUI.run();
    }

    public void run() {
        int choice;
        do {
            displayMenu();
            choice = input.nextInt();

            switch (choice) {
                case 1:
                    registerCompany();
                    break;
                case 2:
                    removeCompany();    
                    break;
                case 3:
                    updateCompany();
                    break;    
                case 4:
                    addJobPosting();
                    break;
                case 5:
                    listAllCompanies();
                    break;
                case 6:
                    filterCompanies();
                    break;
                case 7:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 6);
    }

    public void registerCompany() {
        // TODO: Implement company registration logic

    }

    public void removeCompany() {
        // TODO: Implement company removal logic
    }

    public void updateCompany() {
        // TODO: Implement company update logic
    }

    public void listAllCompanies() {
        // TODO: Implement company listing logic
    }

    public void filterCompanies() {
        // TODO: Implement company filtering logic
    }

    public void addJobPosting() {
        // TODO: Implement job posting addition logic
    }
}
