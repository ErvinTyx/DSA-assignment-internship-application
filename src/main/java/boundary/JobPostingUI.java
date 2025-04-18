package boundary;

import control.JobManager;
import entity.JobPosting;
import adt.ListInterface;
import java.util.InputMismatchException;
import java.util.Scanner;

public class JobPostingUI {
    private JobManager jobManager;
    private Scanner input;

    // Constructor
    public JobPostingUI() {
        this.jobManager = new JobManager();
        this.input = new Scanner(System.in);
    }

    public JobPostingUI(ListInterface<JobPosting> jobs) {
        this.jobManager = new JobManager();
        this.input = new Scanner(System.in);
        jobManager.addJobPosting(jobs);
    }

    // Main method
    public static void main(String[] args) {
        JobPostingUI jobPostingUI = new JobPostingUI();
        jobPostingUI.run();
    }

    // Method to run the job posting management system
    public void run() {
        int choice;
        do {
            displayMenu();
            choice = getIntInput("Enter your choice: ", 1, 6);

            switch (choice) {
                case 1:
                    addJobPosting();
                    break;
                case 2:
                    removeJobPosting();
                    break;
                case 3:
                    updateJobPosting();
                    break;
                case 4:
                    listAllJobPostings();
                    break;
                case 5:
                    makeSearchJobs();
                    break;
                case 6:
                    System.out.println("\nExiting Job Posting Management System!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 6);
    }

    // Method to display the menu
    private void displayMenu() {
        System.out.println("\n=== JOB POSTING MANAGEMENT SYSTEM ===");
        System.out.println("1. Add New Job Posting");
        System.out.println("2. Remove Job Posting");
        System.out.println("3. Update Job Posting");
        System.out.println("4. List All Job Postings");
        System.out.println("5. Search Job Postings");
        System.out.println("6. Exit");
        System.out.println("=====================================");
    }

    // Method to add a new job posting
    private void addJobPosting() {
        clearInputBuffer();
        System.out.println("\n=== ADD JOB POSTING ===");
        String title = getStringInput("Enter job title: ");
        String description = getStringInput("Enter job description: ");
        String location = getStringInput("Enter job location: ");
        double[] salaryRange = getSalaryRangeInput();
        int experienceRequired = getIntInput("Enter required experience in years: ", 0, 50);
        getSkillRequirements();

        jobManager.addJobPosting(title, description, location, salaryRange, experienceRequired);
        System.out.println("\n✓ Job posting added successfully!");
        pressEnterToContinue();
    }

    // Method to remove a job posting
    private void removeJobPosting() {
        clearInputBuffer();
        System.out.println("\n=== REMOVE JOB POSTING ===");

        // Display all job postings with IDs for easier selection
        displayJobPostingsWithIds();

        String jobId = getStringInput("Enter the ID of the job posting to remove: ");
        boolean removed = jobManager.removeJobPosting(jobId);

        if (removed) {
            System.out.println("\n✓ Job posting removed successfully!");
        } else {
            System.out.println("\n✗ Job posting not found. Please check the ID and try again.");
        }
        pressEnterToContinue();
    }

    // Method to update a job posting
    private void updateJobPosting() {
        clearInputBuffer();
        System.out.println("\n=== UPDATE JOB POSTING ===");

        // Display all job postings with IDs for easier selection
        displayJobPostingsWithIds();

        String jobId = getStringInput("Enter the ID of the job posting to update: ");

        if (jobManager.containsJobPosting(jobId)) {
            updateJob(jobId);
        } else {
            System.out.println("\n✗ Job posting not found. Please check the ID and try again.");
            pressEnterToContinue();
        }
    }

    // Method to update a specific job
    private void updateJob(String jobId) {
        int choice;
        do {
            displayUpdateMenu();
            choice = getIntInput("Enter your choice: ", 1, 7);

            switch (choice) {
                case 1:
                    String title = getStringInput("Enter new job title: ");
                    jobManager.setJobPostingTitle(jobId, title);
                    System.out.println("\n✓ Job title updated successfully!");
                    break;
                case 2:
                    String description = getStringInput("Enter new job description: ");
                    jobManager.setJobPostingDescription(jobId, description);
                    System.out.println("\n✓ Job description updated successfully!");
                    break;
                case 3:
                    String location = getStringInput("Enter new job location: ");
                    jobManager.setJobPostingLocation(jobId, location);
                    System.out.println("\n✓ Job location updated successfully!");
                    break;
                case 4:
                    double[] salaryRange = getSalaryRangeInput();
                    jobManager.setJobPostingSalaryRange(jobId, salaryRange);
                    System.out.println("\n✓ Job salary range updated successfully!");
                    break;
                case 5:
                    jobManager.clearSkillRequirements();
                    getSkillRequirements();
                    jobManager.setJobPostingRequiredSkills(jobId, jobManager.getSkillRequirements());
                    System.out.println("\n✓ Job skill requirements updated successfully!");
                    break;
                case 6:
                    int experience = getIntInput("Enter new required experience in years: ", 0, 50);
                    jobManager.setJobPostingExperienceRequired(jobId, experience);
                    System.out.println("\n✓ Job experience requirement updated successfully!");
                    break;
                case 7:
                    break;
                default:
                    System.out.println("Invalid choice!");
            }

            if (choice != 7) {
                pressEnterToContinue();
            }
        } while (choice != 7);
    }

    // Method to display the update menu
    private void displayUpdateMenu() {
        System.out.println("\n=== UPDATE JOB DETAILS ===");
        System.out.println("1. Update Job Title");
        System.out.println("2. Update Job Description");
        System.out.println("3. Update Job Location");
        System.out.println("4. Update Salary Range");
        System.out.println("5. Update Skill Requirements");
        System.out.println("6. Update Experience Requirement");
        System.out.println("7. Done Updating");
        System.out.println("========================");
    }

    // Method to list all job postings
    private void listAllJobPostings() {
        System.out.println("\n=== ALL JOB POSTINGS ===");
        ListInterface<JobPosting> jobPostings = jobManager.getJobPostings();

        if (jobPostings.isEmpty()) {
            System.out.println("No job postings found.");
        } else {
            jobManager.displayFormattedJobPostings(jobPostings);
        }
        pressEnterToContinue();
    }

    // Method to search job postings
    private void searchJobPostingsMenu() {
        clearInputBuffer();
        System.out.println("\n=== SEARCH JOB POSTINGS ===");

        System.out.println("Search by:");
        System.out.println("1. Title");
        System.out.println("2. Location");
    }

    private ListInterface<JobPosting> makeSearchJobs() {
        searchJobPostingsMenu();
        int searchChoice = getIntInput("\nEnter your choice: ", 1, 2);
        getStringInput("");// clear buffer
        String query = "";
        boolean byTitle = true;

        if (searchChoice == 1) {
            query = getStringInput("Enter title to search for: ");
        } else {
            query = getStringInput("Enter location to search for: ");
            byTitle = false;
        }

        int threshold = getIntInput("Enter fuzzy search threshold (0-5, higher = more matches): ", 0, 5);

        ListInterface<JobPosting> jobs = jobManager.searchJobPostings(query, threshold, byTitle);
        pressEnterToContinue();
        return jobs;
    }

    public ListInterface<JobPosting> FindJobMatch() {
        return makeSearchJobs();
    }

    // Method to display all job postings with IDs
    private void displayJobPostingsWithIds() {
        System.out.println("\nAvailable Job Postings:");
        System.out.println("-------------------------------------------");
        jobManager.displayAllJobPosting();
    }

    // Helper methods for input handling
    private int getIntInput(String prompt, int min, int max) {
        int value = 0;
        boolean valid = false;

        while (!valid) {
            System.out.print(prompt);
            try {
                value = input.nextInt();
                if (value >= min && value <= max) {
                    valid = true;
                } else {
                    System.out.println("Please enter a value between " + min + " and " + max + ".");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                input.nextLine(); // Clear invalid input
            }
        }

        return value;
    }

    // Helper method to get user input
    private String getStringInput(String prompt) {
        System.out.print(prompt);
        return input.nextLine().trim();
    }

    // Helper method to get salary range
    private double[] getSalaryRangeInput() {
        double[] salaryRange = new double[2];
        boolean valid = false;

        while (!valid) {
            try {
                System.out.print("Enter minimum salary: $");
                salaryRange[0] = Double.parseDouble(input.nextLine());

                System.out.print("Enter maximum salary: $");
                salaryRange[1] = Double.parseDouble(input.nextLine());

                if (salaryRange[0] < 0 || salaryRange[1] < 0) {
                    System.out.println("Salaries cannot be negative. Please try again.");
                } else if (salaryRange[0] > salaryRange[1]) {
                    System.out.println("Minimum salary cannot be greater than maximum salary. Please try again.");
                } else {
                    valid = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter valid numbers.");
            }
        }

        return salaryRange;
    }

    // Method to get skill requirements
    private void getSkillRequirements() {
        int numSkills = getIntInput("Enter number of required skills: ", 0, 20);

        for (int i = 0; i < numSkills; i++) {
            System.out.println("\nSkill #" + (i + 1));
            clearInputBuffer();

            String skillName = getStringInput("Enter skill name: ");
            int importance = getIntInput("Enter importance (1-10): ", 1, 10);

            jobManager.addSkillRequirement(skillName, importance);
        }
    }

    // Method to clear input buffer
    private void clearInputBuffer() {
        if (input.hasNextLine()) {
            input.nextLine();
        }
    }

    // Method to press Enter to continue
    private void pressEnterToContinue() {
        System.out.print("\nPress Enter to continue...");
        input.nextLine();
    }

    // Getter for jobManager
    public JobManager getJobManager() {
        return jobManager;
    }
}
