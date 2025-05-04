package utility;

public class MessageUI {
    public static void displayInvalidChoiceMessage() {
        System.out.println("Invalid choice. Please try again.");
    }

    public static void displayExitMessageSkillRequirement() {
        System.out.println("Exiting the Skill Requirement Management System.");
    }

    public static void displayExitMessageSkillProficiency() {
        System.out.println("Exiting the Skill Proficiency Management System.");
    }

    public static void displayEnteringMessageSkillRequirement() {
        System.out.println("Entering the Skill Requirement Management System.");
    }

    public static void displayEnteringMessageSkillProficiency() {
        System.out.println("Entering the Skill Proficiency Management System.");
    }

    public static void displayLogOutMessage() {
        System.out.println("Logging out.");
    }

    public static void displayDeleteApplicantAccountMessage() {
        System.out.println("Applicant account deleted.");
    }

    public static void displayDeleteCompanyAccountMessage() {
        System.out.println("Company account deleted.");
    }

    public static void displayCompanyInfoNotFoundMessage() {
        System.out.println("Company Id not found.");
    }

    public static void displayExitMessageMatch() {
        System.out.println("Exiting the Match Management System.");
    }

    public static void displayExitingMessageInterviewScheduler() {
        System.out.println("Exiting the Interview Scheduler.");
    }

    public static void displayJobApplicationSuccessMessage() {
        System.out.println("Job application submitted successfully!");
    }

    public static void displayErrorMessageStudentNull() {
        System.out.println("ERROR: Cannot run job search with null student");
    }
    
    public static void displayMessageRunningJobSearchStudent(String studentName) {
        System.out.println("Running job search for student: " + studentName);
    }

    public static void displayNoJobsFoundYourCriteria() {
        System.out.println("No jobs found matching your criteria.");
    }

    public static void displayStartingJobSearch(String jobTitle, int weighting) {
        System.out.println("Starting job search for job title: " + jobTitle + " with weighting: " + weighting);
    }

    public static void displayErrorMessageCompanyManagerNull() {
        System.out.println("ERROR: Cannot run job search with null companyManager");
    }
}
