package dao;

import control.JobManager;

public class JobPostingInitializer {

    public static void initialize(JobManager jobManager) {

        // Initialize developer skills using SkillRequiredInitializer
        SkillRequiredInitializer.initializeDeveloperSkills(jobManager);
        double[] salaryRange1 = {4000, 6000};
        jobManager.addJobPosting(
                "Java Developer",
                "Develop backend systems with Java and Spring Boot.",
                "Kuala Lumpur",
                salaryRange1
        );
        jobManager.clearSkillRequirements(); // Clear skills after adding the job posting

        // Initialize frontend developer skills
        SkillRequiredInitializer.initializeQASkills(jobManager);
        double[] salaryRange2 = {3000, 4500};
        jobManager.addJobPosting(
                "Frontend Developer",
                "Create and maintain responsive web interfaces.",
                "Penang",
                salaryRange2
        );
        jobManager.clearSkillRequirements(); // Clear skills after adding the job posting

        // Initialize data analyst skills
        SkillRequiredInitializer.initializeUXSkills(jobManager);
        double[] salaryRange3 = {5000, 7000};
        jobManager.addJobPosting(
                "Data Analyst",
                "Analyze data to identify trends and insights for decision making.",
                "Remote",
                salaryRange3
        );
        jobManager.clearSkillRequirements(); // Clear skills after adding the job posting

        // Initialize UI/UX designer skills
        SkillRequiredInitializer.initializePMSkills(jobManager);
        double[] salaryRange4 = {3500, 5200};
        jobManager.addJobPosting(
                "UI/UX Designer",
                "Design intuitive and engaging user interfaces for web and mobile apps.",
                "Johor Bahru",
                salaryRange4
        );
        jobManager.clearSkillRequirements(); // Clear skills after adding the job posting
    }
}
