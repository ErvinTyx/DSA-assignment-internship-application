package control;

import adt.*;
import boundary.JobPostingUI;
import entity.JobPosting;
import utility.MessageUI;
import entity.SkillRequirement;

public class JobManager {
    private ListInterface<JobPosting> jobPostings = new ArrayList<>();
    private JobPostingUI jobPostingUI = new JobPostingUI();
    private SkillRequirementManager skillRequirementManager = new SkillRequirementManager(new ArrayList<>());

    public JobManager(ListInterface<JobPosting> jobPostings) {
        this.jobPostings = jobPostings;
    }

    public void runJobPosting() {
        int choice = 0;
        do {
            choice = jobPostingUI.getMenuChoice();
            switch (choice) {
                case 1:
                    addJobPosting();
                    break;
                case 2:
                    displayAllJobPosting();
                    break;
                case 3:
                    updateJobPosting();
                    break;
                case 4:
                    deleteJobPosting();
                    break;
                case 5:
                    return;
                default:
                    MessageUI.displayInvalidChoiceMessage();
            }
        } while (choice != 5);
    }

    public void addJobPosting() {
        String jobPostingTitle = jobPostingUI.inputJobPostingTitle();// got
        String jobPostingDescription = jobPostingUI.inputJobPostingDescription();// got
        MessageUI.displayEnteringMessageSkillRequirement();
        skillRequirementManager.runSkillRequirement();
        ListInterface<SkillRequirement> skillRequirements = skillRequirementManager.getSkillRequirements();// got
        String jobPostingLocation = jobPostingUI.inputJobPostingLocation();// got
        double[] jobPostingSalary = jobPostingUI.inputJobPostingSalary();// got
        int jobPostingExperienceRequired = jobPostingUI.inputJobPostingExperienceRequired();
        int jobPostingExperienceImportance = jobPostingUI.inputJobPostingExperienceImportance();
        int jobPostingLocationImportance = jobPostingUI.inputJobPostingLocationImportance();
        int jobPostingSkillImportance = jobPostingUI.inputJobPostingSkillImportance();
        JobPosting jobPosting = new JobPosting(
                jobPostingTitle,
                jobPostingDescription,
                skillRequirements, jobPostingLocation, jobPostingSalary, jobPostingExperienceRequired,
                jobPostingExperienceImportance, jobPostingLocationImportance, jobPostingSkillImportance);
        jobPostings.add(jobPosting);

        displayAllJobPosting();
    }

    public void displayAllJobPosting() {
        jobPostingUI.listAllJobPosting(getAllJobPosting());
    }

    private String getAllJobPosting() {
        String allJobPosting = "";
        for (JobPosting jobPosting : jobPostings) {
            allJobPosting += "\t" + jobPosting.toString();
        }
        return allJobPosting;
    }

    public void deleteJobPosting() {
        int index = jobPostingUI.inputJobPostingIndex();
        if (index > 0 && index <= jobPostings.size()) {
            jobPostings.remove(index);
        } else {
            MessageUI.displayInvalidChoiceMessage();
        }
        displayAllJobPosting();
    }

    public void updateJobPosting() {
        int index = jobPostingUI.inputJobPostingIndex();
        if (index > 0 && index <= jobPostings.size()) {
            String jobPostingTitle = jobPostingUI.inputJobPostingTitle();// got
            String jobPostingDescription = jobPostingUI.inputJobPostingDescription();// got
            MessageUI.displayEnteringMessageSkillRequirement();
            skillRequirementManager.runSkillRequirement(jobPostings.get(index - 1).getRequiredSkills());
            ListInterface<SkillRequirement> skillRequirements = skillRequirementManager.getSkillRequirements();// got
            String jobPostingLocation = jobPostingUI.inputJobPostingLocation();// got
            double[] jobPostingSalary = jobPostingUI.inputJobPostingSalary();// got
            int jobPostingExperienceRequired = jobPostingUI.inputJobPostingExperienceRequired();
            int jobPostingExperienceImportance = jobPostingUI.inputJobPostingExperienceImportance();
            int jobPostingLocationImportance = jobPostingUI.inputJobPostingLocationImportance();
            int jobPostingSkillImportance = jobPostingUI.inputJobPostingSkillImportance();
            JobPosting jobPosting = new JobPosting(
                    jobPostingTitle,
                    jobPostingDescription,
                    skillRequirements, jobPostingLocation, jobPostingSalary, jobPostingExperienceRequired,
                    jobPostingExperienceImportance, jobPostingLocationImportance, jobPostingSkillImportance);
            jobPostings.set(index - 1, jobPosting);
        } else {
            MessageUI.displayInvalidChoiceMessage();
        }
        displayAllJobPosting();
    }

    public static void main(String[] args) {
        JobManager jobManager = new JobManager(new ArrayList<>());
        jobManager.runJobPosting();
    }
}
