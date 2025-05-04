package boundary;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InterviewUI {

    private Scanner scanner = new Scanner(System.in);
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private void displayInterviewMenu() {
        System.out.println("Interview Menu");
        System.out.println("---------------");
        System.out.println("1. Shedule Interview for a Job Posting");
        System.out.println("2. View Interviewers");
        System.out.println("3. Return To Company Menu");
    }

    public int displayInterviewMenuOptions() {
        displayInterviewMenu();
        int choice = 0;
        boolean flag = true;
        while (flag) {
            try {
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                if (choice < 1 || choice > 3) {
                    System.out.println("Please enter a valid choice between 1 and 5.");
                } else {
                    flag = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // clear the invalid input
            }
        }
        return choice;
    }

    private void displayInterviewMenuStudent(){
        System.out.println();
        System.out.println();
        System.out.println("Interview Menu");
        System.out.println("---------------");
        System.out.println("1. View Interviews");
        System.out.println("2. Set Interview Status");
        System.out.println("3. Return To Student Menu");
    }

    public int displayInterviewMenuStudentOptions() {
        displayInterviewMenuStudent();
        int choice = 0;
        boolean flag = true;
        while (flag) {
            try {
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                if (choice < 1 || choice > 3) {
                    System.out.println("Please enter a valid choice between 1 and 3.");
                } else {
                    flag = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // clear the invalid input
            }
        }
        return choice;
    }


    public void displayInterview(String info) {
        System.out.println(info);
    }

    public int displayJobs(String info) {
        // see wan add header anot
        System.out.println(info);
        int choice = 0;
        boolean flag = true;
        while (flag) {
            try {
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                flag = false;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // clear the invalid input
            }
        }
        scanner.nextLine();
        return choice;
    }

    public LocalDateTime getInterviewDateAndTime() {
        // get date and time
        LocalDateTime dateTime = null;
        while (dateTime == null) {
            System.out.print("Enter interview date and time (yyyy-MM-dd HH:mm): ");
            String dateTimeStr = scanner.nextLine().trim();
            try {
                dateTime = LocalDateTime.parse(dateTimeStr, dateFormatter);
                if (dateTime.isBefore(LocalDateTime.now())) {
                    System.out.println("Cannot schedule interviews in the past. Please enter a future date and time.");
                    dateTime = null;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date/time format. Please use yyyy-MM-dd HH:mm format.");
            }
        }
        return dateTime;
    }

    public int acceptApplicant() {
        int choice = 0;
        boolean flag = true;
        while (flag) {
            try {
                System.out.println("1. Reject Applicant");
                System.out.println("2. Accept");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                if (choice < 1 || choice > 2) {
                    System.out.println("Please enter a valid choice between 1 and 2.");
                } else {
                    flag = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // clear the invalid input
            }
        }
        return choice;
    }

    public int inputSelectedInterview(){
        int choice = 0;
        boolean flag = true;
        while (flag) {
            try {
                System.out.println("Enter 0 to exit.");
                System.out.print("Enter the number of interview: ");
                choice = scanner.nextInt();
                flag = false;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // clear the invalid input
            }
        }
        return choice;
    }
    
    public int inputInterviewComing(){
        int choice = 0;
        boolean flag = true;
        while (flag) {
            try {
                System.out.println("Are you coming to the interview?");
                System.out.println("1. Yes");
                System.out.println("2. No");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                if (choice < 1 || choice > 2) {
                    System.out.println("Please enter a valid choice between 1 and 2.");
                } else {
                    flag = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // clear the invalid input
            }
        }
        return choice;
    }
}
