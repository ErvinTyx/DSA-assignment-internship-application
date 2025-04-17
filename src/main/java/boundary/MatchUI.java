package boundary;

import java.util.Scanner;
import adt.ListInterface;
import entity.Match;
import entity.Student;
import entity.JobPosting;
import control.MatchingEngine;
import control.JobManager;
import control.ApplicantManager;
import dao.JobPostingInitializer;
import dao.StudentInitializer;

public class MatchUI {

    private final MatchingEngine matchingEngine;
    private final JobManager jobManager;
    private final ApplicantManager applicantManager;
    private final Scanner input = new Scanner(System.in);

    public MatchUI(MatchingEngine matchingEngine, JobManager jobManager, ApplicantManager applicantManager) {
        this.matchingEngine = matchingEngine;
        this.jobManager = jobManager;
        this.applicantManager = applicantManager;
    }

    public static void main(String[] args) {
        // Initialize the managers and engine
        MatchingEngine matchingEngine = new MatchingEngine();
        JobManager jobManager = new JobManager();
        ApplicantManager applicantManager = new ApplicantManager();
        
        // Initialize sample data
        StudentInitializer.initialize(applicantManager);
        JobPostingInitializer.initialize(jobManager);
        
        // Create MatchUI instance
        MatchUI matchUI = new MatchUI(matchingEngine, jobManager, applicantManager);
        matchUI.run();
    }

    // Method to display student matches
    public void displayStudentMatches() {
        System.out.print("Enter your student ID: ");
        String studentId = input.nextLine().trim();
        
        // Find the student by ID
        Student student = null;
        ListInterface<Student> students = applicantManager.getApplicants();
        
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(studentId)) {
                student = students.get(i);
                break;
            }
        }
        
        if (student == null) {
            System.out.println("Student ID not found. Please try again.");
            return;
        }
        
        // Create a list with just this student
        ListInterface<Student> singleStudent = applicantManager.getApplicants();
        ListInterface<JobPosting> jobs = jobManager.getJobPostings();
        
        System.out.println("\nFinding job matches for " + student.getName() + "...");
        
        ListInterface<Match> matches = matchingEngine.calculateMatches(singleStudent, jobs);
        
        if (matches.isEmpty()) {
            System.out.println("No job matches found for your profile.");
            return;
        }
            
        System.out.println("\n=========== YOUR JOB MATCHES ===========\n");
        System.out.printf("%-5s %-25s %-15s %-10s\n", "No.", "Job", "Location", "Match Score");
        System.out.println("------------------------------------------------------------------");
        
        for (int i = 0; i < matches.size(); i++) {
            Match match = matches.get(i);
            System.out.printf("%-5d %-25s %-15s %-10.2f\n", 
                (i + 1),
                match.getJob().getTitle(),
                match.getJob().getLocation(),
                match.getScore());
        }
        
        System.out.println("\nEnter a job number to view details (0 to go back): ");
        int selection = input.nextInt();
        input.nextLine(); // Consume newline
        
        if (selection > 0 && selection <= matches.size()) {
            displayJobMatchDetails(matches.get(selection - 1));
        }
    }
    
    // Method to display detailed job match information
    private void displayJobMatchDetails(Match match) {
        Student student = match.getStudent();
        JobPosting job = match.getJob();
        
        System.out.println("\n========== JOB MATCH DETAILS ==========");
        
        System.out.println("\nYOUR PROFILE:");
        System.out.println("ID: " + student.getId());
        System.out.println("Name: " + student.getName());
        System.out.println("Location: " + student.getLocation());
        System.out.println("Experience: " + student.getExperience() + " years");
        
        System.out.println("\nYour Skills:");
        for (int i = 0; i < student.getSkills().size(); i++) {
            System.out.println("- " + student.getSkills().get(i).getSkillName() + 
                               " (Proficiency: " + student.getSkills().get(i).getProficiency() + ")");
        }
        
        System.out.println("\nMATCHED JOB:");
        System.out.println("ID: " + job.getId());
        System.out.println("Title: " + job.getTitle());
        System.out.println("Location: " + job.getLocation());
        System.out.println("Experience Required: " + job.getExperienceRequired() + " years");
        System.out.println("Salary Range: $" + job.getSalaryRange()[0] + " - $" + job.getSalaryRange()[1]);
        
        System.out.println("\nRequired Skills:");
        for (int i = 0; i < job.getRequiredSkills().size(); i++) {
            System.out.println("- " + job.getRequiredSkills().get(i).getSkillName() + 
                               " (Importance: " + job.getRequiredSkills().get(i).getImportance() + ")");
        }
        
        System.out.println("\nMATCH SCORE: " + String.format("%.2f", match.getScore()) + " / 1.00");
        System.out.println("\nPress Enter to continue...");
        input.nextLine();
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
                        viewStudentProfile(); // View student profile
                        break;
                    case 5:
                        System.out.println("Exiting Job Matching System. Goodbye!"); // Exit
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
    }

    // Display the menu options
    private void displayMenu() {
        System.out.println("\n===== STUDENT JOB MATCHING SYSTEM =====");
        System.out.println("1. View your job matches");
        System.out.println("2. Filter your matches by minimum score");
        System.out.println("3. Filter your matches by job location");
        System.out.println("4. View your profile details");
        System.out.println("5. Exit");
        System.out.println("=====================================");
    }

    // Filter student's matches by minimum score
    public void filterByMinimumScore() {
        System.out.print("Enter your student ID: ");
        String studentId = input.nextLine().trim();
        
        // Find the student by ID
        Student student = null;
        ListInterface<Student> students = applicantManager.getApplicants();
        
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(studentId)) {
                student = students.get(i);
                break;
            }
        }
        
        if (student == null) {
            System.out.println("Student ID not found. Please try again.");
            return;
        }
        
        System.out.print("Enter minimum match score (0.0 - 1.0): ");
        double minScore;
        
        try {
            minScore = input.nextDouble();
            input.nextLine(); 
            
            if (minScore < 0 || minScore > 1) {
                System.out.println("Score must be between 0.0 and 1.0");
                return;
            }
            
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a number between 0.0 and 1.0");
            input.nextLine(); 
            return;
        }
        
        // Create a list with just this student
        ListInterface<Student> singleStudent = applicantManager.getApplicants();
        ListInterface<JobPosting> jobs = jobManager.getJobPostings();
        ListInterface<Match> matches = matchingEngine.calculateMatches(singleStudent, jobs);
        
        System.out.println("\n=========== FILTERED RESULTS (Score >= " + minScore + ") ===========\n");
        
        boolean found = false;
        int count = 1;
        
        for (int i = 0; i < matches.size(); i++) {
            Match match = matches.get(i);
            if (match.getScore() >= minScore) {
                System.out.printf("%-5d %-25s %-15s %-10.2f\n", 
                    count++,
                    match.getJob().getTitle(),
                    match.getJob().getLocation(),
                    match.getScore());
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matches found with score >= " + minScore);
        }
    }
    
    // Filter student's matches by job location
    public void filterByJobLocation() {
        System.out.print("Enter your student ID: ");
        String studentId = input.nextLine().trim();
        
        // Find the student by ID
        Student student = null;
        ListInterface<Student> students = applicantManager.getApplicants();
        
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(studentId)) {
                student = students.get(i);
                break;
            }
        }
        
        if (student == null) {
            System.out.println("Student ID not found. Please try again.");
            return;
        }
        
        System.out.print("Enter job location to filter by: ");
        String location = input.nextLine().trim().toLowerCase();
        
        // Create a list with just this student
        ListInterface<Student> singleStudent = applicantManager.getApplicants();
        ListInterface<JobPosting> jobs = jobManager.getJobPostings();
        ListInterface<Match> matches = matchingEngine.calculateMatches(singleStudent, jobs);
        
        System.out.println("\n=========== FILTERED RESULTS (Location: " + location + ") ===========\n");
        
        boolean found = false;
        int count = 1;
        
        for (int i = 0; i < matches.size(); i++) {
            Match match = matches.get(i);
            if (match.getJob().getLocation().toLowerCase().contains(location)) {
                System.out.printf("%-5d %-25s %-15s %-10.2f\n", 
                    count++,
                    match.getJob().getTitle(),
                    match.getJob().getLocation(),
                    match.getScore());
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matches found in location: " + location);
        }
    }
    
    // View student profile details
    public void viewStudentProfile() {
        System.out.print("Enter your student ID: ");
        String studentId = input.nextLine().trim();
        
        // Find the student by ID
        Student student = null;
        ListInterface<Student> students = applicantManager.getApplicants();
        
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(studentId)) {
                student = students.get(i);
                break;
            }
        }
        
        if (student == null) {
            System.out.println("Student ID not found. Please try again.");
            return;
        }
        
        System.out.println("\n========== YOUR PROFILE DETAILS ==========");
        System.out.println("ID: " + student.getId());
        System.out.println("Name: " + student.getName());
        System.out.println("Location: " + student.getLocation());
        System.out.println("Experience: " + student.getExperience() + " years");
        
        System.out.println("\nYour Skills:");
        for (int i = 0; i < student.getSkills().size(); i++) {
            System.out.println("- " + student.getSkills().get(i).getSkillName() + 
                              " (Proficiency: " + student.getSkills().get(i).getProficiency() + ")");
        }
        
        System.out.println("\nPress Enter to continue...");
        input.nextLine();
    }
}
