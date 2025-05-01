package boundary;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CompanyUI {

    private Scanner sc = new Scanner(System.in);

    private void companyMenuLogin() {
        System.out.println();
        System.out.println();
        System.out.println("---------------------");
        System.out.println("Company Menu");
        System.out.println("---------------------");
        System.out.println("1. Log In");
        System.out.println("2. Sign Up");
        System.out.println("3. Return To Main Menu");
    }

    public int getMenuChoiceLogin() {
        companyMenuLogin();
        int choice = 0;
        boolean flag = true;
        while (flag) {
            try {
                System.out.print("Enter your choice: ");
                choice = sc.nextInt();
                flag = false;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine();
            }
        }
        sc.nextLine();
        return choice;
    }

    private void companyMenu() {
        System.out.println();
        System.out.println();
        System.out.println("---------------------");
        System.out.println("Company Menu");
        System.out.println("---------------------");
        System.out.println("1. View Profile");
        System.out.println("2. Update Profile");
        System.out.println("3. Delete Profile");
        System.out.println("4. Manage Job Postings");
        System.out.println("5. Manage Interviews");
        System.out.println("6. Log out");

    }

    private void updateMenu() {
        System.out.println();
        System.out.println();
        System.out.println("---------------------");
        System.out.println("Update Menu");
        System.out.println("---------------------");
        System.out.println("1. Update Name");
        System.out.println("2. Update Location");
        System.out.println("3. Return To Company Profile Menu");
    }

    public int getMenuChoice() {
        companyMenu();
        int choice = 0;
        boolean flag = true;
        while (flag) {
            try {
                System.out.print("Enter your choice: ");
                choice = sc.nextInt();
                flag = false;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine();
            }
        }
        sc.nextLine();
        return choice;
    }

    public int getUpdateMenuChoice() {
        updateMenu();
        int choice = 0;
        boolean flag = true;
        while (flag) {
            try {
                System.out.print("Enter your choice: ");
                choice = sc.nextInt();
                flag = false;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine();
            }
        }
        sc.nextLine();
        return choice;
    }

    public String comfimationDelete() {
        System.out.print("Are you sure you want to delete this company profile? (y/n):");
        String choice = sc.nextLine();
        return choice;
    }

    public String inputCompanyName() {
        System.out.print("Enter company name: ");
        String name = sc.nextLine();
        return name;
    }

    public String inputCompanyLocation() {
        System.out.print("Enter company location: ");
        String location = sc.nextLine();
        return location;
    }

    public String inputCompanyId() {
        System.out.print("Enter company id: ");
        String id = sc.nextLine();
        return id;
    }

    public void displayEnterToContinueMessage() {
        System.out.println("Press enter to continue.");
        sc.nextLine();
    }

    public void listCompanyInfo(String info) {
        String header = "";
        header = "+----+----------+----------+-----------------------+\n" +
                "| ID | Name     | Location | Job Postings          |\n" +
                "+----+----------+----------+-----------------------+";
        System.out.println(header);
        System.out.println(info);
    }

}
