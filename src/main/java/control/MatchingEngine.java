package control;

import adt.ArrayList;
import adt.ListInterface;
import boundary.JobPostingUI;
import entity.Company;
import entity.JobPosting;
import entity.Match;
import entity.SkillProficiency;
import entity.SkillRequirement;
import entity.Student;
import utility.SearchUtil;

public class MatchingEngine {

    private ListInterface<Match> matches = new ArrayList<>();

    private ListInterface<JobPosting> jobposting = new ArrayList<>();

    private ListInterface<JobPosting> result = new ArrayList<>();

    /**
     * Calculates match scores between all students and jobs, filters out low
     * matches,
     * sorts the results, and returns the sorted list.
     */
    public void calculateMatches(Student student) {
        matches.clear();
        for (int j = 0; j < jobposting.size(); j++) {
            JobPosting job = jobposting.get(j);
            double score = calculateMatchScore(student, job);
            if (score > 0.4) { // Only add meaningful matches
                matches.add(new Match(student, job, score));
            }
        }

        // Convert to array and sort
        Match[] matchArray = listToArray(matches);
        SearchUtil.mergeSort(matchArray);

        // Rebuild the list in sorted order
        matches.clear();
        for (Match match : matchArray) {
            matches.add(match);
        }
    }

    /**
     * Converts a ListInterface<Match> to a Match[] array.
     */
    private Match[] listToArray(ListInterface<Match> matchList) {
        Match[] array = new Match[matchList.size()];
        for (int i = 0; i < matchList.size(); i++) {
            array[i] = matchList.get(i);
        }
        return array;
    }

    /**
     * Computes overall match score based on skills, location, and experience.
     */
    private double calculateMatchScore(Student student, JobPosting job) {
        double skillScore = calculateSkillScore(student, job);
        double locationScore = student.getLocation().equalsIgnoreCase(job.getLocation()) ? 1.0 : 0.0;
        double experienceScore = Math.min(1.0, (double) student.getExperience() / job.getExperienceRequired());

        // Weighted average
        return (0.6 * skillScore) + (0.2 * locationScore) + (0.2 * experienceScore);
    }

    /**
     * Calculates how well a student's skills match a job's required skills.
     * Both proficiency and importance are considered.
     */
    private double calculateSkillScore(Student student, JobPosting job) {
        double scoreSum = 0;
        ListInterface<SkillRequirement> jobSkills = job.getRequiredSkills();
        ListInterface<SkillProficiency> studentSkills = student.getSkills();

        for (int i = 0; i < jobSkills.size(); i++) {
            SkillRequirement req = jobSkills.get(i);
            for (int j = 0; j < studentSkills.size(); j++) {
                SkillProficiency prof = studentSkills.get(j);
                if (req.getSkillName().equalsIgnoreCase(prof.getSkillName())) {
                    scoreSum += prof.getProficiency() * req.getImportance();
                }
            }
        }

        return scoreSum / 100.0; // Normalize score to range 0–1
    }

    public void getAllJobsFromCompany(CompanyManager companyManager) {
        if (!jobposting.isEmpty()) {

            ListInterface<Company> companys = companyManager.getCompanies();
            for (int i = 0; i < companys.size(); i++) {
                ListInterface<JobPosting> jobs = companys.get(i).getJobPostings();
                for (int j = 0; j < jobs.size(); j++) {
                    jobposting.add(jobs.get(i));
                }
            }
        }
    }

    public void seachRelatedJobMatch() {
        result.clear();
        JobPostingUI jobPostingUI = new JobPostingUI(jobposting);
        result = jobPostingUI.FindJobMatch();
    }

    public void clearJobsData() {
        jobposting.clear();
        result.clear();
    }

    public void displayScoresJobs() {
        if (matches.isEmpty()) {
            System.out.println("No job matches found for your profile.");
        } else {
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
        }
    }

    public void getMatchDetails(int num) {
        if (num > 0 && num <= matches.size()) {
            displayJobMatchDetails(matches.get(num - 1));
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

    }

    public boolean displayFilterMinScore(double minScore) {
        boolean found = false;
        int count = 1;
        // TODO: It is better to sort the matching score bigest to smallest
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
        return found;
    }

    public boolean displayFilterLocation(String location) {
        boolean found = false;
        int count = 1;

        for (int i = 0; i < matches.size(); i++) {
            Match match = matches.get(i);
            if (match.getJob().getLocation().toLowerCase().contains(location)) {
                System.out.printf("%-5d %-25s %-15s %-10.2f\n",
                        count++,
                        match.getJob().getTitle(),
                        match.getJob().getLocation(),
                        match.getScore()

                );
                found = true;
            }
        }
        return found;
    }

    // @dev : purpose to not make it encapsulated just to deleted matches from interview 
    public ListInterface<Match> getMatches() {
        return matches;
    }

}
