package boundary;

import java.util.Scanner;
import adt.ArrayList;
import adt.ListInterface;
import control.CompanyManager;
import control.JobManager;
import dao.CompanyInitializer;
import entity.Company;
import entity.JobPosting;
import utility.SearchUtil;

public class CompanyUI {
    private final Scanner input = new Scanner(System.in);
    private final CompanyManager companyManager = new CompanyManager();
    private final JobManager jobManager = new JobManager();

    public static void main(String[] args) {
        new CompanyUI().run();
    }

    public void run() {
        CompanyInitializer.initialize(companyManager, jobManager);
        int choice;

        do {
            displayMainMenu();
            choice = getUserChoice();
            handleMainChoice(choice);
        } while (choice != 7);
    }

    private void displayMainMenu() {
        System.out.println("\n====== Company Management Menu ======");
        System.out.println("1. Register Company");
        System.out.println("2. Remove Company by ID");
        System.out.println("3. Update Company by ID");
        System.out.println("4. List All Companies");
        System.out.println("5. Filter Companies");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }

    private int getUserChoice() {
        while (!input.hasNextInt()) {
            System.out.print("Invalid input. Please enter a number: ");
            input.next();
        }
        return input.nextInt();
    }

    private void handleMainChoice(int choice) {
        input.nextLine(); // clear buffer
        switch (choice) {
            case 1 -> registerCompany();
            case 2 -> removeCompany();
            case 3 -> updateCompany();
            case 4 -> listAllCompanies();
            case 5 -> displayFilterMenu();
            case 6 -> System.out.println("Exiting Company Management. Goodbye!");
            default -> System.out.println("Invalid choice. Please try again.");
        }
    }

    private void registerCompany() {
        System.out.println("\n-- Register New Company --");
        String name = prompt("Enter company name: ");
        String location = prompt("Enter company location: ");
        ListInterface<JobPosting> jobPostings = getInputJobPostings();

        companyManager.registerCompany(name, location, jobPostings);
        System.out.println("Company registered successfully!");
    }

    private void removeCompany() {
        System.out.println("\n-- Remove Company --");
        String id = prompt("Enter company ID to remove: ");
        boolean removed = companyManager.removeCompanyById(id);

        System.out.println(removed ? "Company removed successfully!" : "Company not found.");
    }

    private void updateCompany() {
        System.out.println("\n-- Update Company --");
        String id = prompt("Enter company ID to update: ");

        if (companyManager.getCompanyById(id) != null) {
            runUpdateMenu(id);
        } else {
            System.out.println("Company not found.");
        }
    }

    private void runUpdateMenu(String id) {
        int choice;
        do {
            displayUpdateMenu();
            choice = getUserChoice();
            input.nextLine(); // clear buffer

            Company company = companyManager.getCompanyById(id);
            switch (choice) {
                case 1 -> company.setName(prompt("Enter new company name: "));
                case 2 -> company.setLocation(prompt("Enter new company location: "));
                case 3 -> company.setJobPostings(getInputJobPostings());
                case 4 -> System.out.println("Exiting update menu.");
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);
    }

    private void displayUpdateMenu() {
        System.out.println("\n-- Update Company Info --");
        System.out.println("1. Update Name");
        System.out.println("2. Update Location");
        System.out.println("3. Update Job Postings");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    private void listAllCompanies() {
        System.out.println("\n-- List of All Companies --");
        displayFormattedCompanies(companyManager.getCompanies());
    }

    private void displayFormattedCompanies(ListInterface<Company> companies) {
        if (companies.isEmpty()) {
            System.out.println("No companies found.");
            return;
        }
    
        for (int i = 0; i < companies.size(); i++) {
            Company company = companies.get(i);
            System.out.println("\n===========================================");
            System.out.println("Company ID     : " + company.getId());
            System.out.println("Name           : " + company.getName());
            System.out.println("Location       : " + company.getLocation());
    
            ListInterface<JobPosting> jobPostings = company.getJobPostings();
            System.out.println("Job Postings   :");
    
            if (jobPostings == null || jobPostings.isEmpty()) {
                System.out.println("  No job postings available.");
            } else {
                for (int j = 0; j < jobPostings.size(); j++) {
                    JobPosting job = jobPostings.get(j);
                    System.out.println("  • " + job.getTitle() + " (" + job.getLocation() + ")");
                }
            }
        }
        System.out.println("===========================================");
    }
    
    

    private void displayFilterMenu() {
        System.out.println("\n-- Filter Companies --");
        System.out.println("1. By Name");
        System.out.println("2. By Location");
        System.out.println("3. Fuzzy Search Name");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");

        int choice = getUserChoice();
        input.nextLine(); // clear buffer

        switch (choice) {
            case 1 -> filterByName();
            case 2 -> filterByLocation();
            case 3 -> searchCompanyByName();
            case 4 -> System.out.println("Exiting filter menu.");
            default -> System.out.println("Invalid choice. Please try again.");
        }
    }

    private void filterByName() {
        String name = prompt("Enter company name to filter: ");
        ListInterface<Company> companies = companyManager.filterCompaniesByName(name);
        displayFilteredCompanies(companies, "name", name);
    }

    private void filterByLocation() {
        String location = prompt("Enter company location to filter: ");
        ListInterface<Company> companies = companyManager.filterCompaniesByLocation(location);
        displayFilteredCompanies(companies, "location", location);
    }

    private void searchCompanyByName() {
        String query = prompt("Enter company name to search (fuzzy): ").toLowerCase();
        int threshold = Integer.parseInt(prompt("Enter fuzzy threshold (e.g., 2): "));

        ListInterface<Company> matchedCompanies = new ArrayList<>();
        for (int i = 0; i < companyManager.getCompanies().size(); i++) {
            Company company = companyManager.getCompanies().get(i);
            for (String word : company.getName().toLowerCase().split("\\s+")) {
                if (SearchUtil.fuzzySearch(query, word, threshold)) {
                    matchedCompanies.add(company);
                    break;
                }
            }
        }

        if (matchedCompanies.isEmpty()) {
            System.out.println("No companies matched your query.");
        } else {
            System.out.println("\n-- Matched Companies --");
            matchedCompanies.forEach(System.out::println);
        }
    }

    private ListInterface<JobPosting> getInputJobPostings() {
        System.out.println("\n-- Enter Job Postings for the Company --");
        JobPostingUI jobPostingUI = new JobPostingUI();
        jobPostingUI.run();
        return jobPostingUI.getJobManager().getJobPostings();
    }

    private String prompt(String message) {
        System.out.print(message);
        return input.nextLine();
    }

    private void displayFilteredCompanies(ListInterface<Company> companies, String type, String keyword) {
        if (companies.isEmpty()) {
            System.out.printf("No companies found with the %s \"%s\".\n", type, keyword);
        } else {
            System.out.printf("Companies with %s \"%s\":\n", type, keyword);
            companies.forEach(System.out::println);
        }
    }
}
