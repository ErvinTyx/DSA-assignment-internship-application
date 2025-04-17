package boundary;

import java.util.Scanner;

import adt.ListInterface;
import control.ApplicantManager;
import control.CompanyManager;
import control.InterviewSchedulerManager;
import control.JobManager;
import control.MatchingEngine;
import entity.Company;
import entity.Match;
import entity.Student;

public class InterviewUI {
    private InterviewSchedulerManager interviews;
    private MatchingEngine matchingEngines;
    private CompanyManager companyManagers;
    private JobManager jobManagers;
    private ApplicantManager applicantManagers;
    private Scanner input = new Scanner(System.in);

    // TODO: as student accept or reject interview

    // TODO: company schedule interview
    public static void main(String[] args) {
        MatchingEngine matchingEngine = new MatchingEngine();
        CompanyManager companyManager = new CompanyManager();
        ApplicantManager applicantManager = new ApplicantManager();
        JobManager jobManager = new JobManager();

        // Initialize sample data

        // Create InterviewUI instance
        InterviewUI interviewUI = new InterviewUI();
        interviewUI.run();

    }

    private void displayMenuLogin() {
        System.out.println("\n=== INTERVIEW SCHEDULING SYSTEM ===");
        System.out.println("1. Login as Student");
        System.out.println("2. Login as Company");
        System.out.println("3. Exit");
        System.out.println("=====================================");
    }

    private String getInputId() {
        System.out.print("Enter ID: ");
        return input.nextLine();
    }

    public void run() {
        int choice;
        do {
            displayMenuLogin();
            choice = getUserChoice();
            handleMainChoice(choice);

        } while (choice != 3);

    }

    private void handleMainChoice(int choice) {
        input.nextLine(); // clear buffer
        String id = getInputId();
        switch (choice) {
            case 1 -> loginAsCompany(id);
            case 2 -> loginAsStudent(id);
            case 3 -> System.out.println("INTERVIEW SCHEDULING SYSTEM. Goodbye!");
            default -> System.out.println("Invalid choice. Please try again.");
        }
    }

    private String enterId() {
        return prompt("Enter ID: ");
    }

    private void loginAsCompany(String id) {
        Company company = companyManagers.getCompanyById(id);
        if (company != null) {
            System.out.println("Login successful!");
            runMainCompany(company);
        } else {
            System.out.println("Company not found.");
        }

    }

    private void displayMenuCompany() {
        System.out.println("\n=== INTERVIEW SCHEDULING SYSTEM ===");
        System.out.println("1. Schedule Interview");
        System.out.println("2. View Scheduled Interviews");
    }

    private void runMainCompany(Company company) {
        displayMenuCompany();
        int choice = getUserChoice();
        switch (choice) {
            case 1 -> scheduleInterview(company);
            case 2 -> viewScheduledInterviews(company);
        }
    }

    public void scheduleInterview(Company company) {
        interviews.scheduleInterview(company, matchingEngines);
        System.out.println("=== Interviews Scheduled ===");
        setAllInterviewsInformation();
    }

    public void setAllInterviewsInformation() {

        do {
            interviews.setAllInterviewsInformation();

        } while (!interviews.interviewAllScheduled());
    }

    public void viewScheduledInterviews(Company company) {
        interviews.displayAllInterviews(company);
    }

    private void loginAsStudent(String id) {
        Student student = applicantManagers.getStudentEntity(id);
        if (student != null) {
            System.out.println("Login successful!");
            runMainStudent(student);
        } else {
            System.out.println("Company not found.");
        }
    }

    private void displayMenuStudent() {
        System.out.println("\n=== INTERVIEW SCHEDULING SYSTEM ===");
        System.out.println("1. Accept or Reject Interview");
        System.out.println("2. View Scheduled Interviews");
    }

    private void runMainStudent(Student student) {
        displayMenuStudent();
        int choice = getUserChoice();
        switch (choice) {
            case 1 -> acceptOrRejectInterview();
            case 2 -> viewScheduledInterviewsStudent();
        }
    }

    public void acceptOrRejectInterview() {

    }

    public void viewScheduledInterviewsStudent() {

    }

    private int getUserChoice() {
        while (!input.hasNextInt()) {
            System.out.print("Invalid input. Please enter a number: ");
            input.next();
        }
        return input.nextInt();
    }

    // Method to get user input
    private String prompt(String message) {
        System.out.print(message);
        return input.nextLine();
    }

}
