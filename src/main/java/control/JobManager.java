package control;

import entity.JobPosting;
import entity.SkillRequirement;
import adt.ArrayList;

public class JobManager {
    private ArrayList<JobPosting> jobPostings = new ArrayList<>();
    private ArrayList<SkillRequirement> skillRequirements = new ArrayList<>();

    public void addJobPosting(JobPosting jobPosting) {
        jobPostings.add(jobPosting);
    }

    public ArrayList<SkillRequirement> getSkillRequirements() {
        ArrayList<SkillRequirement> skillRequirements = new ArrayList<>();
        for (SkillRequirement skill : this.skillRequirements) {
            skillRequirements.add(skill);
        }
        clearSkillRequirements();
        return skillRequirements;
    }

    public void addJobPosting(String title, String description, String location, double[] salaryRange) {
        
        ArrayList<SkillRequirement> requiredSkills = new ArrayList<>();
        requiredSkills = getSkillRequirements();
        JobPosting jobPosting = new JobPosting(title, description, requiredSkills, location, salaryRange);
        jobPostings.add(jobPosting);
        
    }

    public boolean removeJobPosting(String jobId) {
        for (int i = 0; i < jobPostings.size(); i++) {
            if (jobId != null && jobId.equals(jobPostings.get(i).getId())) {

                jobPostings.remove(i+1);
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

    public ArrayList<JobPosting> getJobPostings() {
        ArrayList<JobPosting> copy = new ArrayList<>();
        for (JobPosting job : jobPostings) {
            copy.add(job);
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

    public boolean setJobPostingRequiredSkills(String jobId, ArrayList<SkillRequirement> requiredSkills) {
        for (int i = 0; i < jobPostings.size(); i++) {
            if (jobId != null && jobId.equals(jobPostings.get(i).getId())) {

                jobPostings.get(i).setRequiredSkills(requiredSkills);
                return true;
            }
        }
        return false;
    }
}
