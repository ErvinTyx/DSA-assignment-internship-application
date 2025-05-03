package control;

import adt.*;
import boundary.MatchUI;
import entity.JobPosting;
import entity.Match;
import entity.SkillProficiency;
import entity.SkillRequirement;
import utility.*;
import entity.Student;
import dao.MatchDAO;

public class MatchingEngine {
    private ListInterface<Match> matches;
    private CompanyManager companyManager = new CompanyManager();
    private Student student;
    private MatchUI matchUI = new MatchUI();
    private MatchDAO matchDAO = new MatchDAO();

    public MatchingEngine() {
        matchDAO.retrieveFromFile();
    }

    public ListInterface<Match> calculateJobScoreMatches(ListInterface<JobPosting> jobPostings) {
        ListInterface<Match> matches = new ArrayList<>();
        for (int i = 0; i < jobPostings.size(); i++) {
            JobPosting jobPosting = jobPostings.get(i);
            double score = calculateMatchScore(student, jobPosting);
            matches.add(new Match(new Student(student), new JobPosting(jobPosting), score));
        }
        // menu for applying to jobs
        int choice = 0;
        do {
            // display matches
            listAllMatches(matches);
            // select match
            choice = matchUI.inputMatchIndex();
            if (choice != -1 && choice < matches.size()) {
                // upload to the match list
                this.matches.add(new Match(matches.get(choice)));
            }
        } while (choice != -1);

        return matches;
    }

    public void displayMatches(String info) {
        matchUI.displayMatch(info);
    }

    public String listAllMatches(ListInterface<Match> matches) {
        String output = "";
        for (int i = 0; i < matches.size(); i++) {
            Match match = matches.get(i);
            output += match.toString();
        }
        return output;
    }

    public void runLookForJobs(Student student) {
        ListInterface<Match> matches = new ArrayList<>();
        matches = findStudentMatches();
        this.student = student;
        int choice;
        do {
            choice = matchUI.matchMenuInput();
            switch (choice) {
                case 1:
                    // look for jobs
                    matches = addmatches(searchJobs(), matches);
                    displayMatches(listAllMatches(matches));
                    break;
                case 2:
                    // view all available jobs
                    searchAllJobs();
                    break;
                case 3:
                    // view current matches
                    displayMatches(listAllMatches(matches));
                    break;
                case 4:
                    // exit
                    MessageUI.displayExitMessageMatch();
                    break;
                default:
                    MessageUI.displayInvalidChoiceMessage();
            }
        } while (choice != 3);
        matchDAO.saveToFile(this.matches);
    }

    private ListInterface<Match> addmatches(ListInterface<Match> newMatch, ListInterface<Match> matches) {
        for (int i = 0; i < newMatch.size(); i++) {
            matches.add(new Match(newMatch.get(i)));
        }
        return matches;
    }

    private ListInterface<Match> findStudentMatches() {
        ListInterface<Match> matches = new ArrayList<>();
        for (int i = 0; i < matches.size(); i++) {
            if (matches.get(i).getStudent().equals(student)) {
                matches.add(matches.get(i));
            }
        }
        return matches;
    }

    private ListInterface<Match> searchJobs() {

        String jobTitle = matchUI.inputJobTitle();
        int weighting = matchUI.inputWeighting();
        return calculateJobScoreMatches(companyManager.searchJobs(jobTitle, weighting));

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
        double importancesum = 0;

        // Iterate over each skill requirement of the job
        for (int i = 0; i < jobSkills.size(); i++) {
            SkillRequirement req = jobSkills.get(i);

            // Check if the student has the required skill and calculate score contribution
            for (int j = 0; j < studentSkills.size(); j++) {
                SkillProficiency prof = studentSkills.get(j);
                if (req.getSkillName().equalsIgnoreCase(prof.getSkillName())) {
                    // Add the product of proficiency and importance to the score sum
                    scoreSum += prof.getProficiency() * req.getImportance();
                }
            }
            // Sum up the importance for normalization
            importancesum += req.getImportance() * 10;
        }

        // Normalize the score sum by the total importance and scale by skill weight
        return scoreSum / importancesum * skillWeights;
    }

    protected ListInterface<Match> getMatchesBySeletedJob(JobPosting job) {
        ListInterface<Match> matches = new ArrayList<>();
        for (int i = 0; i < this.matches.size(); i++) {
            if (job.getId().equals(matches.get(i).getJobPosting().getId())) {
                matches.add(new Match(matches.get(i)));
                this.matches.remove(i + 1);
            }
        }
        return matches;
    }
}
