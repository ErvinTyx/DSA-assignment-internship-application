package control;

import adt.*;
import boundary.MatchUI;
import entity.JobPosting;
import entity.Match;
import entity.SkillProficiency;
import entity.SkillRequirement;
import entity.Student;
import dao.MatchDAO;
import utility.MessageUI;

public class MatchingEngine {
    private ListInterface<Match> matches;
    private CompanyManager companyManager;
    private Student student;
    private final MatchUI matchUI = new MatchUI();
    private final MatchDAO matchDAO = new MatchDAO();

    public MatchingEngine() {
        this.matches = new ArrayList<>();
        this.companyManager = new CompanyManager();
        matches = matchDAO.retrieveFromFile();
    }

    public MatchingEngine(CompanyManager companyManager) {
        this.matches = new ArrayList<>();
        this.companyManager = companyManager;
        matches = matchDAO.retrieveFromFile();
    }

    public ListInterface<Match> calculateJobScoreMatches(ListInterface<JobPosting> jobPostings) {
        ListInterface<Match> newMatches = new ArrayList<>();

        for (int i = 0; i < jobPostings.size(); i++) {
            JobPosting jobPosting = jobPostings.get(i);

            if (student == null) {
                MessageUI.displayErrorMessageStudentNull();
                return newMatches;
            }

            double score = calculateMatchScore(student, jobPosting);
            newMatches.add(new Match(new Student(student), new JobPosting(jobPosting), score));
        }

        if (newMatches.size() == 0) {
            MessageUI.displayNoJobsFoundYourCriteria();
            return newMatches;
        }

        // menu for applying to jobs
        int choice = 0;
        do {
            // display matches
            String matchesInfo = listAllMatches(newMatches);
            displayMatches(matchesInfo);

            // select match
            choice = matchUI.inputMatchIndex();
            if (choice != -1 && choice < newMatches.size()) {
                // upload to the match list
                this.matches.add(new Match(newMatches.get(choice)));
                MessageUI.displayJobApplicationSuccessMessage();
                matchUI.pressEnterToContinueMessage();
            }
        } while (choice != -1);

        return newMatches;
    }

    public void displayMatches(String info) {
        if (info.isEmpty()) {
            matchUI.displayMatch("No matching jobs found.");
        } else {
            matchUI.displayMatch(info);
        }
    }

    public String listAllMatches(ListInterface<Match> matches) {
        String output = "";
        if (matches.isEmpty()) {
            return "No matching jobs found.";
        }

        for (int i = 0; i < matches.size(); i++) {
            Match match = matches.get(i);
            output += "\n" + match.toString(i) + "\n";
        }
        return output;
    }

    public void runLookForJobs(Student student) {
        if (student == null) {
            MessageUI.displayErrorMessageStudentNull();

            return;
        }

        this.student = student;
        MessageUI.displayMessageRunningJobSearchStudent(student.getName());

        ListInterface<Match> CurrentMatches = new ArrayList<>();
        // Get the student's existing matches from the system
        CurrentMatches = findStudentMatches();

        int choice;
        do {
            choice = matchUI.matchMenuInput();
            switch (choice) {
                case 1 -> {
                    // look for jobs
                    ListInterface<Match> newMatches = searchJobs();
                    if (newMatches.size() > 0) {
                        CurrentMatches = addMatches(newMatches, CurrentMatches);
                        displayMatches(listAllMatches(CurrentMatches));
                    } else {
                        MessageUI.displayNoJobsFoundYourCriteria();
                    }
                }
                case 2 -> // view all available jobs
                    searchAllJobs();
                case 3 ->
                    MessageUI.displayExitMessageMatch();
                default -> MessageUI.displayInvalidChoiceMessage();
            }
        } while (choice != 3); // Changed from 3 to 4 to match the menu

        // Save matches before exiting
        matchDAO.saveToFile(this.matches);
    }

    private ListInterface<Match> addMatches(ListInterface<Match> newMatches, ListInterface<Match> matches) {
        for (int i = 0; i < newMatches.size(); i++) {
            matches.add(new Match(newMatches.get(i)));
        }
        return matches;
    }

    private ListInterface<Match> findStudentMatches() {
        ListInterface<Match> studentMatches = new ArrayList<>();

        for (int i = 0; i < this.matches.size(); i++) {
            if (this.matches.get(i).getStudent().equals(student)) {
                studentMatches.add(this.matches.get(i));
            }
        }
        return studentMatches;
    }

    private ListInterface<Match> searchJobs() {
        String jobTitle = matchUI.inputJobTitle();
        int weighting = matchUI.inputWeighting();

        // Debug info before search
        MessageUI.displayStartingJobSearch(jobTitle, weighting);

        // Ensure companyManager is initialized
        if (companyManager == null) {
            MessageUI.displayErrorMessageCompanyManagerNull();
            companyManager = new CompanyManager();
        }

        // Get job postings that match the search criteria
        ListInterface<JobPosting> matchingJobs = companyManager.searchJobs(jobTitle, weighting);

        // Calculate match scores for each job
        return calculateJobScoreMatches(matchingJobs);
    }

    public void searchAllJobs() {
        String result = companyManager.displayCompanyInfo();
        displayMatches(result);
    }

    public ListInterface<Match> getMatchesFromCompany(JobPosting jobPostings) {
        ListInterface<Match> matches = new ArrayList<>();

        String jobPosting = jobPostings.getId();
        for (int j = 0; j < this.matches.size(); j++) {
            if (matches.get(j).getJobPosting().getId().equals(jobPosting)) {
                matches.add(matches.get(j));
            }
        }
        return matches;
    }

    /**
     * Calculate the match score of a student and a job posting. The score is
     * a weighted sum of the skill score, location score, and experience score.
     * 
     * The skill score is the sum of the products of the proficiency of the
     * student in a skill and the importance of the skill in the job posting,
     * normalized to a value between 0 and 1.
     * 
     * The location score is 1 if the student's location matches the job posting's
     * location, and 0 otherwise.
     * 
     * The experience score is the minimum of the student's experience and the job
     * posting's experience requirement, normalized to a value between 0 and 1.
     * 
     * The weights of the skill, location, and experience scores are given by
     * the job posting's skill, location, and experience importance.
     * 
     * @param student the student to calculate the score for
     * @param job     the job posting to calculate the score for
     * @return the match score
     */
    private double calculateMatchScore(Student student, JobPosting job) {
        // weightings
        double skillWeight = job.getSkillImportance() / 10.0;
        double locationWeight = job.getLocationImprotance() / 10.0;
        double experienceWeight = job.getExperienceImportance() / 10.0;

        // Calculate the skill score.
        double skillScore = calculateSkillScore(student, job);

        // Calculate the location score.
        double locationScore;
        if (student.getLocation().equalsIgnoreCase(job.getLocation())) {
            locationScore = 1.0;
        } else {
            locationScore = 0.0;
        }
        locationScore *= locationWeight;

        // Calculate the experience score.
        double experienceRatio;
        if (job.getExperienceRequired() == 0) {
            experienceRatio = 1.0;
        } else {
            experienceRatio = (double) student.getExperience() / job.getExperienceRequired();
        }
        double experienceScore = Math.min(1.0, experienceRatio) * experienceWeight;

        // Calculate the match score as a weighted average of the skill, location,
        // and experience scores.
        double matchScore = (skillScore + locationScore + experienceScore)
                / (skillWeight + locationWeight + experienceWeight);

        return matchScore;
    }

    /**
     * Calculate the skill score between a student and a job posting. The score
     * is a sum of the products of the proficiency of the student in a certain
     * skill and the importance of the skill in the job posting, normalized to
     * a value between 0 and 1.
     * 
     * @param student the student to calculate the score for
     * @param job     the job posting to calculate the score for
     * @return the skill score
     */
    private double calculateSkillScore(Student student, JobPosting job) {
        double scoreSum = 0;
        ListInterface<SkillRequirement> jobSkills = job.getRequiredSkills();
        ListInterface<SkillProficiency> studentSkills = student.getSkillProficiencies();
        double skillWeights = (double) job.getSkillImportance();
        double importanceSum = 0;

        // Iterate over each skill requirement of the job
        for (int i = 0; i < jobSkills.size(); i++) {
            SkillRequirement req = jobSkills.get(i);

            // Check if the student has the required skill and calculate score contribution
            for (int j = 0; j < studentSkills.size(); j++) {
                SkillProficiency prof = studentSkills.get(j);
                if (req.getSkillName().equalsIgnoreCase(prof.getSkillName())) {
                    // Add the product of proficiency and importance to the score sum
                    double contribution = prof.getProficiency() * req.getImportance();
                    scoreSum += contribution;
                }
            }
            // Sum up the importance for normalization
            importanceSum += req.getImportance() * 10;
        }

        // Avoid division by zero
        if (importanceSum == 0) {
            System.out.println("No skill importance factors found, returning skill score of 0");
            return 0;
        }

        // Normalize the score sum by the total importance and scale by skill weight
        double finalScore = scoreSum / importanceSum * skillWeights;
        return finalScore;
    }

    protected ListInterface<Match> getMatchesBySeletedJob(JobPosting job) {
        ListInterface<Match> SeletedJobMatches = new ArrayList<>();
        
        for (int i = 0; i < this.matches.size(); i++) {
            if (job.getId().equals(this.matches.get(i).getJobPosting().getId())) {
                SeletedJobMatches.add(new Match(this.matches.get(i)));
                this.matches.remove(i+1);
            }
        }
        matchDAO.saveToFile(this.matches);
        return SeletedJobMatches;
    }

}
