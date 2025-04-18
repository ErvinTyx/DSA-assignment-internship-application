package boundary;

import java.util.Scanner;
import adt.ListInterface;
import entity.Match;
import entity.Student;
import control.CompanyManager;
import entity.JobPosting;
import control.MatchingEngine;
import control.JobManager;
import control.ApplicantManager;
import dao.CompanyInitializer;
import dao.JobPostingInitializer;
import dao.StudentInitializer;

public class MatchUI {

    private MatchingEngine matchingEngine;
    private CompanyManager companyManager;
    private ApplicantManager applicantManager;
    private JobManager jobManager;
    private Scanner input = new Scanner(System.in);

    public MatchUI(MatchingEngine matchingEngine, CompanyManager companyManager,
            ApplicantManager applicantManager) {
        this.matchingEngine = matchingEngine;
        this.companyManager = companyManager;
        this.applicantManager = applicantManager;
    }

    public static void main(String[] args) {
        // Initialize the managers and engine
        MatchingEngine matchingEngine = new MatchingEngine();
        CompanyManager companyManager = new CompanyManager();
        ApplicantManager applicantManager = new ApplicantManager();
        JobManager jobManager = new JobManager();

        // Initialize sample data
        JobPostingInitializer.initialize(jobManager);
        StudentInitializer.initialize(applicantManager);
        CompanyInitializer.initialize(companyManager);

        // Create MatchUI instance
        MatchUI matchUI = new MatchUI(matchingEngine, companyManager, applicantManager);
        matchUI.run();
    }

    // Method to display student matches
    public void displayStudentMatches() {
        Student student = enterStudentId();

        if (student != null) {
            // get all jobs from company
            matchingEngine.getAllJobsFromCompany(companyManager);
            // search for type of job related
            System.out.println("\nFinding job matches for " + student.getName() + "...");
            matchingEngine.seachRelatedJobMatch();
            // use match engine find the student score for all of the jobs
            matchingEngine.calculatematches(student);

            // display all the scores which are high than 0.4
            matchingEngine.displayScoresJobs();

        } else {
            System.out.println("Student ID not found. Please try again.");
            return;
        }

    }

    // Enter student
    private Student enterStudentId() {
        System.out.print("Enter your student ID: ");
        String studentId = input.nextLine().trim();

        // Find the student by ID
        Student student = null;
        student = applicantManager.getStudentEntity(studentId);
        return student;
    }

    // Main method to control the flow of the program
    public void run() {
        int choice;
        do {
            displayMenu();
            System.out.print("Enter your choice: ");
            try {
                choice = input.nextInt();
                input.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        displayStudentMatches(); // View student's job matches
                        break;
                    case 2:
                        filterByMinimumScore(); // Filter student's matches by minimum score
                        break;
                    case 3:
                        filterByJobLocation(); // Filter student's matches by location
                        break;
                    case 4:
                        applyForJob();
                        break;
                    case 5:
                        System.out.println("Exiting Student Job Matching System. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                input.nextLine();
                choice = 0;
            }
        } while (choice != 5);
        matchingEngine.clearJobsData();
    }

    public void applyForJob() {
        Student student = enterStudentId();
        if (student == null) {
            System.out.println("Student ID not found. Please try again.");
        } else {
            if(matchingEngine.resultIsEmpty()){
                System.out.println("Please Search your job matches first.");
            }else{
                System.out.println("\n=========== JOB DETAILS ===========\n");
                matchingEngine.displayAllMatches();
                System.out.print("Enter the number of the job you want to apply for: ");
                int jobNumber = input.nextInt();
                matchingEngine.getMatchDetails(jobNumber);
                input.nextLine();
            }
        }
    }

    // Display the menu options
    private void displayMenu() {
        System.out.println("\n===== STUDENT JOB MATCHING SYSTEM =====");
        System.out.println("1. Search your job matches");
        System.out.println("2. Filter your matches by minimum score");
        System.out.println("3. Filter your matches by job location");
        System.out.println("4. Apply for a job");
        System.out.println("5. Exit");
        System.out.println("=====================================");
    }

    // Filter student's matches by minimum score
    public void filterByMinimumScore() {
        Student student = enterStudentId();
        if (student == null) {
            System.out.println("Student ID not found. Please try again.");
        } else {
            boolean validNumber = true;
            double minScore = 0;
            do {

                System.out.print("Enter minimum match score (0.0 - 1.0): ");

                try {
                    minScore = input.nextDouble();
                    input.nextLine();

                    if (minScore < 0 || minScore > 1) {
                        System.out.println("Score must be between 0.0 and 1.0");
                    }

                } catch (Exception e) {
                    System.out.println("Invalid input. Please enter a number between 0.0 and 1.0");
                    input.nextLine();
                }
                validNumber = false;
            } while (validNumber);

            System.out.println("\n=========== FILTERED RESULTS (Score >= " + minScore + ") ===========\n");

            if (!matchingEngine.displayFilterMinScore(minScore)) {
                System.out.println("No matches found with score >= " + minScore);
            }
        }
    }

    // Filter student's matches by job location
    public void filterByJobLocation() {
        Student student = enterStudentId();

        if (student == null) {
            System.out.println("Student ID not found. Please try again.");

        } else {

            System.out.print("Enter job location to filter by: ");
            String location = input.nextLine().trim().toLowerCase();

            System.out.println("\n=========== FILTERED RESULTS (Location: " + location + ") ===========\n");

            if (!matchingEngine.displayFilterLocation(location)) {
                System.out.println("No matches found in location: " + location);
            }
        }
    }
}
