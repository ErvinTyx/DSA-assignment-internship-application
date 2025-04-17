package boundary;

import control.ReportGenerator;
import entity.JobPosting;
import adt.ListInterface;
import control.ApplicantManager;
import control.CompanyManager;
import control.JobManager;
import dao.CompanyInitializer;
import dao.JobPostingInitializer;
import dao.StudentInitializer;
import entity.Company;
import entity.Student;

import java.util.Scanner;
import static utility.SearchUtil.fuzzySearch;

public class ReportUI {
    private ReportGenerator reportGenerator;
    private Scanner input;

    public ReportUI() {
        JobManager jobManager = new JobManager();
        CompanyManager companyManager = new CompanyManager();
        ApplicantManager applicantManager = new ApplicantManager();

        this.reportGenerator = new ReportGenerator(jobManager, companyManager, applicantManager);
        this.input = new Scanner(System.in);
        JobPostingInitializer.initialize(jobManager);
        CompanyInitializer.initialize(companyManager);
        StudentInitializer.initialize(applicantManager);
    }
    
    public static void main(String[] args) {
        ReportUI reportUI = new ReportUI();
        reportUI.displayMainReportMenu();
    }
    
    public void displayMainReportMenu() {
        int choice;
        do {
            System.out.println("\n=== Internship Application Report Menu ===");
            System.out.println("1. Job Posting Report");
            System.out.println("2. Company Report");
            System.out.println("3. Applicants Report");
            System.out.println("4. Matches Report");
            System.out.println("5. Interview Report");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = input.nextInt();
            input.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    displayJobPostingReportMenu();
                    break;
                case 2:
                    displayCompanyReportMenu(); // Placeholder
                    break;
                case 3:
                    displayApplicantsReportMenu(); // Placeholder
                    break;
//                case 4:
//                    displayMatchesReportMenu(); // Placeholder
//                    break;
//                case 5:
//                    displayInterviewReportMenu(); // Placeholder
//                    break;
                case 0:
                    System.out.println("Exiting report menu...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

    public void displayJobPostingReportMenu() {
        int choice;
        do {
            System.out.println("\n=== Job Posting Report Menu ===");
            System.out.println("1. View all job postings");
            System.out.println("2. Filter by job ID");
            System.out.println("3. Filter by job title");
            System.out.println("4. Filter by required skill");
            System.out.println("5. Filter by location");
            System.out.println("6. Filter by salary (minimum salary)");
            System.out.println("7. Filter by experience required (minimum years)");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    displayJobPostings(reportGenerator.generateJobPostingOverview());
                    break;
                case 2:
                    System.out.print("Enter job ID to search: ");
                    String id = input.nextLine();
                    displayJobPostings(reportGenerator.generateFilteredJobPostings(id, null, null, null, null, null));
                    break;
                case 3:
                    System.out.print("Enter job title to filter: ");
                    String title = input.nextLine();
                    displayJobPostings(reportGenerator.generateFilteredJobPostings(null, title, null, null, null, null));
                    break;
                case 4:
                    System.out.print("Enter required skill to filter: ");
                    String skill = input.nextLine();
                    ListInterface<JobPosting> filterSkill = reportGenerator.generateFilteredJobPostings(null, null, skill, null, null, null);
                    displayJobPostings(filterSkill, skill);
                    break;
                case 5:
                    System.out.print("Enter location to filter: ");
                    String location = input.nextLine();
                    displayJobPostings(reportGenerator.generateFilteredJobPostings(null, null, null, location, null, null));
                    break;
                case 6:
                    System.out.print("Enter minimum salary to filter: ");
                    double salaryInput = input.nextDouble();
                    input.nextLine(); // consume newline
                    Double minSalary = (salaryInput > 0) ? salaryInput : null;
                    displayJobPostings(reportGenerator.generateFilteredJobPostings(null, null, null, null, minSalary, null));
                    break;
                case 7:
                    System.out.print("Enter minimum experience years to filter: ");
                    int expInput = input.nextInt();
                    input.nextLine(); // consume newline
                    Integer minExp = (expInput > 0) ? expInput : null;
                    displayJobPostings(reportGenerator.generateFilteredJobPostings(null, null, null, null, null, minExp));
                    break;
                case 0:
                    System.out.println("Returning...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }
    
    private void displayJobPostings(ListInterface<JobPosting> jobPostings) {
        displayJobPostings(jobPostings, null); 
    }

    private void displayJobPostings(ListInterface<JobPosting> jobPostings, String filterSkill) {
        if (jobPostings.isEmpty()) {
            System.out.println("No job postings found.");
        } else {
            System.out.println("\n=== Job Postings Report ===");
            for (int i = 0; i < jobPostings.size(); i++) {
                JobPosting jp = jobPostings.get(i);
                System.out.println("ID: " + jp.getId());
                System.out.println("Title: " + jp.getTitle());
                System.out.println("Description: " + jp.getDescription());
                System.out.println("Location: " + jp.getLocation());
                System.out.println("Experience Required: " + jp.getExperienceRequired());
                System.out.println("Salary Range: RM" + jp.getSalaryRange()[0] + " - RM" + jp.getSalaryRange()[1]);
                System.out.println("--------------------------------------");

                // Show only matching skill
                System.out.println("Required Skills:");
                boolean foundSkill = false;
                for (int j = 0; j < jp.getRequiredSkills().size(); j++) {
                    String skillName = jp.getRequiredSkills().get(j).getSkillName();
                    if (filterSkill == null || fuzzySearch(filterSkill, skillName, 2)) {
                        System.out.println("- " + skillName);
                        foundSkill = true;
                    }
                }
                if (!foundSkill) {
                    System.out.println("- (No matching skill found)");
                }

                System.out.println("--------------------------------------");
            }
        }
    }
    
    private void displayCompanyReportMenu() {
        int choice;
        do {
            System.out.println("\n=== Company Report Menu ===");
            System.out.println("1. View all companies");
            System.out.println("2. Filter by company id");
            System.out.println("3. Filter by company name");
            System.out.println("4. Filter by location");
            System.out.println("5. Filter by number of job postings");
//            System.out.println("5. Filter by multiple criteria");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = input.nextInt();
            input.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    displayCompanies(reportGenerator.generateCompanyOverview());
                    break; 
                case 2:
                    System.out.print("Enter company ID to search: ");
                    String id = input.nextLine();
                    displayCompanies(reportGenerator.generateFilteredCompanies(id, null, null, null));
                    break;
                case 3:
                    System.out.print("Enter company name to filter: ");
                    String name = input.nextLine();
                    displayCompanies(reportGenerator.generateFilteredCompanies(null, name, null, null));
                    break;
                case 4:
                    System.out.print("Enter location to filter: ");
                    String location = input.nextLine();
                    displayCompanies(reportGenerator.generateFilteredCompanies(null, null, location, null));
                    break;
                case 5:
                    System.out.print("Enter minimum number of job postings to filter: ");
                    int minJobs = input.nextInt();
                    input.nextLine();
                    displayCompanies(reportGenerator.filterCompaniesByMinJobPostings(minJobs));
                    break;
//                case 5:
//                    handleCompanyMultiFilter();
//                    break;
                case 0:
                    System.out.println("Returning...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

    private void displayCompanies(ListInterface<Company> companies) {
        if (companies.isEmpty()) {
            System.out.println("No companies found.");
        } else {
            System.out.println("\n=== Company Report ===");
            for (int i = 0; i < companies.size(); i++) {
                Company c = companies.get(i);
                System.out.println("Company Id: " + c.getId());
                System.out.println("Company Name: " + c.getName());
                System.out.println("Location: " + c.getLocation());
                System.out.println("Job Postings: " + c.getJobPostings());
                System.out.println("--------------------------------------");
            }
            
        }
    }
        
    public void displayApplicantsReportMenu() {
        int choice;

        do {
            System.out.println("\n=== Applicant Report Menu ===");
            System.out.println("1. View all applicants");
            System.out.println("2. Filter by ID");
            System.out.println("3. Filter by name");
            System.out.println("4. Filter by skill proficiency");
            System.out.println("5. Filter by location");
            System.out.println("6. Filter by Experience");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = input.nextInt();
            input.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    displayApplicants(reportGenerator.generateApplicantsOverview());
                    break;
                case 2:
                    System.out.print("Enter applicant ID to search: ");
                    String id = input.nextLine();
                    displayApplicants(reportGenerator.generateFilteredApplicants(id, null, null, null, null));
                    break;
                case 3:
                    System.out.print("Enter applicant name to filter: ");
                    String name = input.nextLine();
                    displayApplicants(reportGenerator.generateFilteredApplicants(null, name, null, null, null));
                    break;
                case 4:
                    System.out.print("Enter skill proficiency to filter: ");
                    String skills = input.nextLine();
                    ListInterface<Student> filterSkill = reportGenerator.generateFilteredApplicants(null, null, skills, null, null);
                    displayApplicants(filterSkill, skills);
                    break;
                case 5:
                    System.out.print("Enter location to filter: ");
                    String location = input.nextLine();
                    displayApplicants(reportGenerator.generateFilteredApplicants(null, null, null, location, null));
                    break;
                case 6:
                    System.out.print("Enter experience to filter: ");
                    int experience = input.nextInt();
                    input.nextLine(); // consume newline
                    Integer minExp = (experience > 0) ? experience : null;
                    displayApplicants(reportGenerator.generateFilteredApplicants(null, null, null, null, experience));
                    break;
                case 0:
                    System.out.println("Returning...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }            
        } while (choice != 0);
    }

    private void displayApplicants(ListInterface<Student> applicants) {
        displayApplicants(applicants, null); 
    }
    
    private void displayApplicants(ListInterface<Student> applicants, String filterSkill) {
        if (applicants.isEmpty()) {
            System.out.println("No applicant found.");
        } else {
            System.out.println("\n=== Applicants Report ===");
            for (int i = 0; i < applicants.size(); i++) {
                Student s = applicants.get(i);
                System.out.println("Applicant Id: " + s.getId());
                System.out.println("Applicant Name: " + s.getName());
                System.out.println("Skill Proficiency: " + s.getSkills());
                System.out.println("Location: " + s.getLocation());
                System.out.println("Experience: " + s.getExperience());
                System.out.println("--------------------------------------");
            }
        }
    }
}
