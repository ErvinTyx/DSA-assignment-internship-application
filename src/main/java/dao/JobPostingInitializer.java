package dao;

import control.JobManager;

public class JobPostingInitializer {

    public static void initialize(JobManager jobManager) {

        // Initialize Developer
        SkillRequiredInitializer.initializeDeveloperSkills(jobManager);
        jobManager.addJobPosting(
                "Java Developer",
                "Develop backend systems with Java and Spring Boot.",
                "Kuala Lumpur",
                new double[]{4000, 6000},
                3
        );
        jobManager.clearSkillRequirements();

        // Initialize QA (Frontend Developer)
        SkillRequiredInitializer.initializeQASkills(jobManager);
        jobManager.addJobPosting(
                "Frontend Developer",
                "Create and maintain responsive web interfaces.",
                "Penang",
                new double[]{3000, 4500},
                2
        );
        jobManager.clearSkillRequirements();

        // Initialize UX
        SkillRequiredInitializer.initializeUXSkills(jobManager);
        jobManager.addJobPosting(
                "Data Analyst",
                "Analyze data to identify trends and insights for decision making.",
                "Remote",
                new double[]{5000, 7000},
                4
        );
        jobManager.clearSkillRequirements();

        // Initialize PM
        SkillRequiredInitializer.initializePMSkills(jobManager);
        jobManager.addJobPosting(
                "UI/UX Designer",
                "Design intuitive and engaging user interfaces for web and mobile apps.",
                "Johor Bahru",
                new double[]{3500, 5200},
                5
        );
        jobManager.clearSkillRequirements();

        // New Role: Backend Developer
        SkillRequiredInitializer.initializeDeveloperSkills(jobManager); // Use Developer skills
        jobManager.addJobPosting(
                "Backend Developer",
                "Develop and maintain server-side logic and APIs.",
                "Selangor",
                new double[]{4500, 6500},
                3
        );
        jobManager.clearSkillRequirements();

        // New Role: QA Engineer
        SkillRequiredInitializer.initializeQASkills(jobManager); // Use QA skills
        jobManager.addJobPosting(
                "QA Engineer",
                "Test and ensure the quality of web applications through manual and automated testing.",
                "Penang",
                new double[]{3500, 5000},
                4
        );
        jobManager.clearSkillRequirements();

        // New Role: Product Manager
        SkillRequiredInitializer.initializePMSkills(jobManager); // Use PM skills
        jobManager.addJobPosting(
                "Product Manager",
                "Lead the development of new products and ensure timely delivery.",
                "Kuala Lumpur",
                new double[]{7000, 10000},
                5
        );
        jobManager.clearSkillRequirements();

        // New Role: DevOps Engineer
        SkillRequiredInitializer.initializeDevOpsSkills(jobManager); // Use DevOps skills
        jobManager.addJobPosting(
                "DevOps Engineer",
                "Manage infrastructure, deployment pipelines, and automate deployment processes.",
                "Cyberjaya",
                new double[]{6000, 8000},
                4
        );
        jobManager.clearSkillRequirements();

        // New Role: Data Scientist
        SkillRequiredInitializer.initializeDataScienceSkills(jobManager); // Use Data Science skills
        jobManager.addJobPosting(
                "Data Scientist",
                "Develop machine learning models and conduct statistical analysis on large datasets.",
                "Remote",
                new double[]{8000, 12000},
                5
        );
        jobManager.clearSkillRequirements();

        // New Role: Network Engineer
        SkillRequiredInitializer.initializeNetworkEngineerSkills(jobManager); // Use Network Engineer skills
        jobManager.addJobPosting(
                "Network Engineer",
                "Design, implement, and maintain network infrastructure and security.",
                "Johor Bahru",
                new double[]{4000, 6000},
                3
        );
        jobManager.clearSkillRequirements();

        // New Role: Business Analyst
        SkillRequiredInitializer.initializeBusinessAnalystSkills(jobManager); // Use Business Analyst skills
        jobManager.addJobPosting(
                "Business Analyst",
                "Analyze and document business processes, identify improvement opportunities.",
                "Penang",
                new double[]{4000, 5500},
                2
        );
        jobManager.clearSkillRequirements();

        // New Role: Cloud Engineer
        SkillRequiredInitializer.initializeCloudEngineerSkills(jobManager); // Use Cloud Engineer skills
        jobManager.addJobPosting(
                "Cloud Engineer",
                "Design, implement, and manage cloud solutions on AWS, Azure, and Google Cloud.",
                "Kuala Lumpur",
                new double[]{6000, 9000},
                4
        );
        jobManager.clearSkillRequirements();

        // New Role: Security Engineer
        SkillRequiredInitializer.initializeSecurityEngineerSkills(jobManager); // Use Security Engineer skills
        jobManager.addJobPosting(
                "Security Engineer",
                "Ensure the security of systems, applications, and networks from potential threats.",
                "Cyberjaya",
                new double[]{7000, 10000},
                5
        );
        jobManager.clearSkillRequirements();
    }
}
