package control;

import entity.JobPosting;
import entity.SkillRequirement;
import adt.ArrayList;
import adt.ListInterface;

public class JobManager {
    private ListInterface<JobPosting> jobPostings = new ArrayList<>();
    private ListInterface<SkillRequirement> skillRequirements = new ArrayList<>();

    public void addJobPosting(JobPosting jobPosting) {
        jobPostings.add(jobPosting);
    }

    public ListInterface<SkillRequirement> getSkillRequirements() {
        ListInterface<SkillRequirement> skillRequirement = new ArrayList<>();
        for (int i = 0; i < skillRequirements.size(); i++) {
            skillRequirement.add(skillRequirements.get(i));
        }
        clearSkillRequirements();
        return skillRequirement;
    }

    public void addJobPosting(String title, String description, String location, double[] salaryRange) {

        ListInterface<SkillRequirement> requiredSkills = new ArrayList<>();
        requiredSkills = getSkillRequirements();
        JobPosting jobPosting = new JobPosting(title, description, requiredSkills, location, salaryRange);
        jobPostings.add(jobPosting);

    }

    public boolean removeJobPosting(String jobId) {
        for (int i = 0; i < jobPostings.size(); i++) {
            if (jobId != null && jobId.equals(jobPostings.get(i).getId())) {

                jobPostings.remove(i + 1);
                return true;
            }
        }
        return false;
    }

    public void clearSkillRequirements() {
        skillRequirements.clear();
    }

    public void listAllJobPostings() {
        System.out.println("Job Postings:");
        if (jobPostings.isEmpty()) {
            System.out.println("No job postings found.");
        } else {

            for (int i = 0; i < jobPostings.size(); i++) {

                System.out.println(jobPostings.get(i).toString());
            }
        }
    }

    public ListInterface<JobPosting> getJobPostings() {
        ListInterface<JobPosting> copy = new ArrayList<>();
        for (int i = 0; i < jobPostings.size(); i++) {
            copy.add(jobPostings.get(i));
        }
        return copy;
    }

    public void addSkillRequirement(String skillname, int importance) {
        skillRequirements.add(new SkillRequirement(skillname, importance));
    }

    // contains job postings
    public boolean containsJobPosting(String jobId) {
        for (int i = 0; i < jobPostings.size(); i++) {
            if (jobId != null && jobId.equals(jobPostings.get(i).getId())) {

                return true;
            }
        }
        return false;
    }

    // Update data
    public boolean setJobPostingTitle(String jobId, String title) {
        for (int i = 0; i < jobPostings.size(); i++) {
            if (jobId != null && jobId.equals(jobPostings.get(i).getId())) {

                jobPostings.get(i).setTitle(title);
                return true;
            }
        }
        return false;
    }

    public boolean setJobPostingDescription(String jobId, String description) {
        for (int i = 0; i < jobPostings.size(); i++) {
            if (jobId != null && jobId.equals(jobPostings.get(i).getId())) {

                jobPostings.get(i).setDescription(description);
                return true;
            }
        }
        return false;
    }

    public boolean setJobPostingLocation(String jobId, String location) {
        for (int i = 0; i < jobPostings.size(); i++) {
            if (jobId != null && jobId.equals(jobPostings.get(i).getId())) {

                jobPostings.get(i).setLocation(location);
                return true;
            }
        }
        return false;
    }

    public boolean setJobPostingSalaryRange(String jobId, double[] salaryRange) {
        for (int i = 0; i < jobPostings.size(); i++) {
            if (jobId != null && jobId.equals(jobPostings.get(i).getId())) {

                jobPostings.get(i).setSalaryRange(salaryRange);
                return true;
            }
        }
        return false;
    }

    public boolean setJobPostingRequiredSkills(String jobId, ListInterface<SkillRequirement> requiredSkills) {
        for (int i = 0; i < jobPostings.size(); i++) {
            if (jobId != null && jobId.equals(jobPostings.get(i).getId())) {

                jobPostings.get(i).setRequiredSkills(requiredSkills);
                return true;
            }
        }
        return false;
    }
}
