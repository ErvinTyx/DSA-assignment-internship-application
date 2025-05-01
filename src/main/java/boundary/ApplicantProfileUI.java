package boundary;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ApplicantProfileUI {

    private Scanner sc = new Scanner(System.in);

    private void applicantMenuLogin() {
        System.out.println();
        System.out.println();
        System.out.println("---------------------");
        System.out.println("Applicant Profile Menu");
        System.out.println("---------------------");
        System.out.println("1. Log In");
        System.out.println("2. Sign Up");
        System.out.println("3. Return To Main Menu");
    }

    public int getMenuChoiceLogin() {
        applicantMenuLogin();
        int choice = 0;
        boolean flag = true;
        while (flag) {
            try {
                System.out.print("Enter your choice: ");
                choice = sc.nextInt();
                if (choice < 1 || choice > 3) {
                    System.out.println("Please enter a valid number.");
                } else {
                    flag = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                sc.next(); // clear the invalid input
            }
        }
        sc.nextLine();
        return choice;
    }

    private void applicantMenu() {
        System.out.println();
        System.out.println();
        System.out.println("---------------------");
        System.out.println("Applicant Profile Menu");
        System.out.println("---------------------");
        System.out.println("1. View Profile");
        System.out.println("2. Update Profile");
        System.out.println("3. Delete Profile");
        System.out.println("4. Find Job");
        System.out.println("5. ManageInterview");
        System.out.println("6. Log out");
    }

    private void updateMenu() {
        System.out.println();
        System.out.println();
        System.out.println("---------------------");
        System.out.println("Update Menu");
        System.out.println("---------------------");
        System.out.println("1. Update Name");
        System.out.println("2. Update Working Experience");
        System.out.println("3. Update Location");
        System.out.println("4. Update Skills Proficiency");
        System.out.println("5. Return To Applicant Profile Menu");

    }

    public int getUpdateMenuChoice() {
        updateMenu();
        int choice = 0;
        boolean flag = true;
        while (flag) {
            try {
                System.out.print("Enter your choice: ");
                choice = sc.nextInt();
                if (choice < 1 || choice > 5) {
                    System.out.println("Please enter a valid number.");
                } else {
                    flag = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                sc.next(); // clear the invalid input
            }
        }
        sc.nextLine();
        return choice;
    }

    public int getMenuChoice() {
        applicantMenu();
        int choice = 0;
        boolean flag = true;
        while (flag) {
            try {
                System.out.print("Enter your choice: ");
                choice = sc.nextInt();
                if (choice < 1 || choice > 6) {
                    System.out.println("Please enter a valid number.");
                } else {
                    flag = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                sc.next(); // clear the invalid input
            }
        }
        sc.nextLine();
        return choice;
    }

    public String inputApplicantName() {
        System.out.print("Enter Applicant Name: ");
        String applicantName = sc.nextLine();
        return applicantName;
    }

    public int inputApplicantWorkingExperience() {
        int workingExperience = 0;
        boolean flag = true;
        while (flag) {
            try {
                System.out.print("Enter your Working Experience: ");
                workingExperience = sc.nextInt();
                if (workingExperience < 0) {
                    System.out.println("Please enter a valid number.");
                } else {
                    flag = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                sc.next(); // clear the invalid input
            }
        }
        sc.nextLine();
        return workingExperience;
    }

    public String inputApplicantLocation() {
        System.out.print("Enter Applicant Location: ");
        String applicantLocation = sc.nextLine();
        return applicantLocation;
    }

    public String inputApplicantId() {
        System.out.print("Enter Applicant ID: ");
        String applicantId = sc.nextLine();
        return applicantId;
    }

    public String comfimationDelete() {
        System.out.print("Are you sure you want to delete this profile? (y/n):");
        String choice = sc.nextLine();
        return choice;
    }

    public void listStudentInfo(String info) {
        String header = String.format("+---------------+---------------+---------------+---------------+\n");
        header += String.format("|%15s|%15s|%15s|%15s|\n", "ID", "Name", "Location", "Experience");
        header += String.format("+---------------+---------------+---------------+---------------+");
        System.out.println(header);
        System.out.println(info);
    }

    public void displayEnterToContinueMessage() {
        System.out.println("\nPress Enter to continue...");
        sc.nextLine();
    }

}
