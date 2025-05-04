package boundary;

import java.util.InputMismatchException;
import java.util.Scanner;


public class MatchUI {
    private Scanner sc = new Scanner(System.in);

    private void matchMenu() {
        System.out.println();
        System.out.println();
        System.out.println("Find Job Menu");
        System.out.println("---------------------");
        System.out.println("1. Search For Jobs");
        System.out.println("2. View All Jobs");        
        System.out.println("3. Return To Applicant Profile Menu");
    }

    public int matchMenuInput() {
        matchMenu();
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

    public void displayMatch(String info){
        System.out.println(info);
    }

    public String inputJobTitle(){
        System.out.print("Enter Job Title: ");
        String jobTitle = sc.nextLine();
        return jobTitle;
    }   

    public int inputWeighting(){
        int weighting = 0;
        boolean flag = true;
        while (flag) {
            try {
                System.out.println("The Weighting is the large number the more unrelated to the job title.");
                System.out.print("Enter Weighting: ");
                weighting = sc.nextInt();
                sc.nextLine();
                if (weighting < 0) {
                    System.out.println("Please enter a valid number. Weighting cannot be negative.");
                } else {
                    flag = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                sc.nextLine(); // clear the invalid input
            }
        }
        return weighting;
    }

    public int inputMatchIndex(){
        int choice = 0;
        boolean flag = true;
        while (flag) {
            try {
                System.out.println("Enter -1  to exit apply job.");
                System.out.print("Enter Match No To apply Job: ");
                choice = sc.nextInt();
                sc.nextLine();
                flag = false;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                sc.nextLine(); // clear the invalid input
            }
        }
        return choice;
    }

    public void pressEnterToContinueMessage(){
        System.out.print("Press enter to continue.");
        sc.nextLine();
    }

}
