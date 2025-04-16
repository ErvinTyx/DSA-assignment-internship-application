package boundary;

import java.util.Scanner;
import adt.ArrayList;
import adt.ListInterface;
import entity.Match;
import entity.SkillProficiency;
import entity.SkillRequirement;
import entity.Student;
import entity.JobPosting;
import control.MatchingEngine;

public class MatchUI {

    private final MatchingEngine matchingEngine;
    private final Scanner input = new Scanner(System.in);

    public MatchUI(MatchingEngine matchingEngine) {
        this.matchingEngine = matchingEngine;
    }

    public static void main(String[] args) {
        // Create MatchingEngine and sample data for students and jobs
        MatchingEngine matchingEngine = new MatchingEngine();
        ListInterface<Student> students = new ArrayList<>();
        ListInterface<JobPosting> jobs = new ArrayList<>();

        ListInterface<SkillProficiency> studentSkills1 = new ArrayList<>();
        studentSkills1.add(new SkillProficiency("Java", 8)); // Skill and proficiency level
        studentSkills1.add(new SkillProficiency("Spring", 7));

        students.add(new Student("John Doe", studentSkills1, "Kuala Lumpur", 2)); // name, skills, location, experience

        ListInterface<SkillProficiency> studentSkills2 = new ArrayList<>();
        studentSkills2.add(new SkillProficiency("JavaScript", 7));
        studentSkills2.add(new SkillProficiency("React", 8));

        students.add(new Student("Jane Smith", studentSkills2, "Penang", 3)); // name, skills, location, experience

        ListInterface<SkillRequirement> jobSkills1 = new ArrayList<>();
        jobSkills1.add(new SkillRequirement("Java", 9)); // Skill and importance level
        jobSkills1.add(new SkillRequirement("Spring", 8));

        double[] salaryRange1 = { 4000, 6000 };
        jobs.add(new JobPosting("Java Developer", "Develop backend systems with Java and Spring Boot", jobSkills1,
                "Kuala Lumpur", salaryRange1, 2));

        ListInterface<SkillRequirement> jobSkills2 = new ArrayList<>();
        jobSkills2.add(new SkillRequirement("JavaScript", 8));
        jobSkills2.add(new SkillRequirement("React", 7));

        double[] salaryRange2 = { 3000, 4500 };
        jobs.add(new JobPosting("Frontend Developer", "Create responsive web interfaces", jobSkills2, "Penang",
                salaryRange2, 3));

        MatchUI matchUI = new MatchUI(matchingEngine);
        matchUI.run(students, jobs);
    }

    public void displayMatches(ListInterface<Student> students, ListInterface<JobPosting> jobs) {

        ListInterface<Match> matches = matchingEngine.calculateMatches(students, jobs);

        if (matches.size() > 0) {
            System.out.println("Match Results:\n");
            for (Match match : matches) {
                displayMatch(match);
            }
        } else {
            System.out.println("No significant matches found.");
        }
    }

    private void displayMatch(Match match) {
        Student student = match.getStudent();
        JobPosting job = match.getJob();
        double score = match.getScore();

        System.out.println("Student: " + student.getName());
        System.out.println("Job: " + job.getTitle());
        System.out.println("Location: " + job.getLocation());
        System.out.println("Experience Required: " + job.getExperienceRequired() + " years");
        System.out.println("Match Score: " + String.format("%.2f", score));
        System.out.println("-----------------------------------------------------");
    }

    public void run(ListInterface<Student> students, ListInterface<JobPosting> jobs) {
        int choice;
        do {
            displayMenu();
            System.out.print("Enter your choice: ");
            choice = input.nextInt();

            switch (choice) {
                case 1:
                    displayMatches(students, jobs);
                    break;
                case 2:
                    searchMatches(students, jobs);
                    break;
                case 3:
                    System.out.println("Exiting MatchUI.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    input.nextLine();
            }
        } while (choice != 3);
    }

    private void displayMenu() {
        System.out.println("\nMatch Menu:");
        System.out.println("1. View all matches");
        System.out.println("2. Search for matches");
        System.out.println("3. Exit");
    }

    public void searchMatches(ListInterface<Student> students, ListInterface<JobPosting> jobs) {
        displayMatches(students, jobs);
    }
}
