package control;

import entity.JobPosting;
import entity.SkillRequirement;
import utility.SearchUtil;
import adt.ArrayList;
import adt.ListInterface;

public class JobManager {
    private ListInterface<JobPosting> jobPostings = new ArrayList<>();
    private ListInterface<SkillRequirement> skillRequirements = new ArrayList<>();

    public void addJobPosting(JobPosting jobPosting) {
        jobPostings.add(jobPosting);
    }

    public void addJobPosting(ListInterface<JobPosting> jobs) {
        for (int i = 0; i < jobPostings.size(); i++) {
            jobPostings.add(jobs.get(i));
        }
    }

    public ListInterface<SkillRequirement> getSkillRequirements() {
        ListInterface<SkillRequirement> skillRequirement = new ArrayList<>();
        for (int i = 0; i < skillRequirements.size(); i++) {
            skillRequirement.add(skillRequirements.get(i));
        }
        clearSkillRequirements();
        return skillRequirement;
    }

    public void addJobPosting(String title, String description, String location, double[] salaryRange,
            int experienceRequired) {
        ListInterface<SkillRequirement> requiredSkills = getSkillRequirements();
        JobPosting jobPosting = new JobPosting(title, description, requiredSkills, location, salaryRange,
                experienceRequired);
        jobPostings.add(jobPosting);
    }

    public boolean removeJobPosting(String jobId) {
        for (int i = 0; i < jobPostings.size(); i++) {
            if (jobId != null && jobId.equals(jobPostings.get(i).getId())) {
                jobPostings.remove(i + 1); // NOTE: i+1 might be a bug; ArrayList should remove(i)
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

    public boolean containsJobPosting(String jobId) {
        for (int i = 0; i < jobPostings.size(); i++) {
            if (jobId != null && jobId.equals(jobPostings.get(i).getId())) {
                return true;
            }
        }
        return false;
    }

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

    public boolean setJobPostingExperienceRequired(String jobId, int experienceRequired) {
        for (int i = 0; i < jobPostings.size(); i++) {
            if (jobId != null && jobId.equals(jobPostings.get(i).getId())) {
                jobPostings.get(i).setExperienceRequired(experienceRequired);
                return true;
            }
        }
        return false;
    }

    public void searchJobPostings(String query, int threshold, boolean byTitle) {
        ListInterface<JobPosting> results = new adt.ArrayList<>();

        query = query.toLowerCase();

        for (int i = 0; i < jobPostings.size(); i++) {
            JobPosting job = jobPostings.get(i);
            String fieldToSearch = byTitle ? job.getTitle().toLowerCase() : job.getLocation().toLowerCase();
            String[] words = fieldToSearch.split("\\s+");

            for (String word : words) {
                if (SearchUtil.fuzzySearch(word, query, threshold) ||
                        SearchUtil.fuzzySearch(query, word, threshold) ||
                        fieldToSearch.contains(query)) {
                    results.add(job);
                    break;
                }
            }
        }

        System.out.println("\n=== SEARCH RESULTS ===");
        if (results.isEmpty()) {
            System.out.println("No job postings matched your search criteria.");
        } else {
            System.out.println("Found " + results.size() + " matching job posting(s):");
            displayFormattedJobPostings(results);
        }

    }

    public void displayFormattedJobPostings(ListInterface<JobPosting> jobPostings) {
        for (int i = 0; i < jobPostings.size(); i++) {
            JobPosting job = jobPostings.get(i);
            System.out.println("\n-------------------------------------------");
            System.out.println("ID: " + job.getId());
            System.out.println("Title: " + job.getTitle());
            System.out.println("Location: " + job.getLocation());
            System.out.println("Experience Required: " + job.getExperienceRequired() + " years");
            System.out.println("Salary Range: $" + job.getSalaryRange()[0] + " - $" + job.getSalaryRange()[1]);

            System.out.println("Required Skills:");
            ListInterface<SkillRequirement> skills = job.getRequiredSkills();
            if (skills.isEmpty()) {
                System.out.println("  No specific skills required");
            } else {
                for (int j = 0; j < skills.size(); j++) {
                    SkillRequirement skill = skills.get(j);
                    System.out.println("  • " + skill.getSkillName() + " (Importance: " + skill.getImportance() + ")");
                }
            }

            System.out.println("Description: " + job.getDescription());
        }
        System.out.println("-------------------------------------------");
    }

    public void displayAllJobPosting() {
        if (jobPostings.isEmpty()) {
            System.out.println("No job postings found.");
        } else {
            System.out.printf("%-10s %-30s %-20s\n", "ID", "Title", "Location");
            System.out.println("-------------------------------------------");

            for (int i = 0; i < jobPostings.size(); i++) {
                JobPosting job = jobPostings.get(i);
                System.out.printf("%-10s %-30s %-20s\n",
                        job.getId(),
                        truncateString(job.getTitle(), 27),
                        truncateString(job.getLocation(), 17));
            }
        }
        System.out.println("-------------------------------------------");
    }

    private String truncateString(String str, int maxLength) {
        if (str.length() <= maxLength) {
            return str;
        }
        return str.substring(0, maxLength - 3) + "...";
    }
}
