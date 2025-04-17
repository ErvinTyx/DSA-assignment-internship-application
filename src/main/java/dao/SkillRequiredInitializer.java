package dao;

import control.JobManager;

public class SkillRequiredInitializer {

    public static void initializeAll(JobManager jobManager) {
        initializeDeveloperSkills(jobManager);
        initializeQASkills(jobManager);
        initializeUXSkills(jobManager);
        initializePMSkills(jobManager);
        initializeDevOpsSkills(jobManager);
        initializeDataScienceSkills(jobManager);
        initializeNetworkEngineerSkills(jobManager);
        initializeBusinessAnalystSkills(jobManager);  // Reduced skills for Business Analyst
        initializeCloudEngineerSkills(jobManager);
        initializeSecurityEngineerSkills(jobManager);
    }

    // Developer skills initialization
    public static void initializeDeveloperSkills(JobManager jobManager) {
        jobManager.addSkillRequirement("Java", 8);
        jobManager.addSkillRequirement("Spring Boot", 7);
        jobManager.addSkillRequirement("JavaScript", 6);
        jobManager.addSkillRequirement("React", 7);
        jobManager.addSkillRequirement("Node.js", 6);
        jobManager.addSkillRequirement("Git", 8);
        jobManager.addSkillRequirement("Microservices", 7);
        jobManager.addSkillRequirement("SQL", 8);
        jobManager.addSkillRequirement("Python", 7);
        jobManager.addSkillRequirement("Angular", 7);
    }

    // QA skills initialization
    public static void initializeQASkills(JobManager jobManager) {
        jobManager.addSkillRequirement("HTML", 7);
        jobManager.addSkillRequirement("CSS", 6);
        jobManager.addSkillRequirement("JavaScript", 8);
        jobManager.addSkillRequirement("Test Automation", 8);
        jobManager.addSkillRequirement("Selenium", 7);
        jobManager.addSkillRequirement("Jenkins", 7);
        jobManager.addSkillRequirement("SQL", 6);
        jobManager.addSkillRequirement("API Testing", 7);
        jobManager.addSkillRequirement("Load Testing", 6);
        jobManager.addSkillRequirement("Performance Testing", 6);
    }

    // UX skills initialization
    public static void initializeUXSkills(JobManager jobManager) {
        jobManager.addSkillRequirement("Figma", 7);
        jobManager.addSkillRequirement("User Research", 6);
        jobManager.addSkillRequirement("Prototyping", 8);
        jobManager.addSkillRequirement("Wireframing", 7);
        jobManager.addSkillRequirement("Interaction Design", 8);
        jobManager.addSkillRequirement("Usability Testing", 6);
        jobManager.addSkillRequirement("Visual Design", 7);
        jobManager.addSkillRequirement("User Interface Design", 7);
        jobManager.addSkillRequirement("Persona Development", 6);
    }

    // PM skills initialization
    public static void initializePMSkills(JobManager jobManager) {
        jobManager.addSkillRequirement("Agile", 9);
        jobManager.addSkillRequirement("Scrum", 8);
        jobManager.addSkillRequirement("Communication", 7);
        jobManager.addSkillRequirement("Project Management", 9);
        jobManager.addSkillRequirement("Risk Management", 8);
        jobManager.addSkillRequirement("Stakeholder Management", 7);
        jobManager.addSkillRequirement("Jira", 8);
        jobManager.addSkillRequirement("Budgeting", 7);
        jobManager.addSkillRequirement("Team Leadership", 8);
        jobManager.addSkillRequirement("Scheduling", 7);
    }

    // DevOps skills initialization
    public static void initializeDevOpsSkills(JobManager jobManager) {
        jobManager.addSkillRequirement("Docker", 8);
        jobManager.addSkillRequirement("Kubernetes", 7);
        jobManager.addSkillRequirement("CI/CD", 8);
        jobManager.addSkillRequirement("Linux", 7);
        jobManager.addSkillRequirement("AWS", 7);
        jobManager.addSkillRequirement("Terraform", 6);
        jobManager.addSkillRequirement("Ansible", 6);
        jobManager.addSkillRequirement("GitLab", 7);
        jobManager.addSkillRequirement("Azure", 7);
        jobManager.addSkillRequirement("Monitoring Tools", 7);
    }

    // Data Science skills initialization
    public static void initializeDataScienceSkills(JobManager jobManager) {
        jobManager.addSkillRequirement("Python", 8);
        jobManager.addSkillRequirement("R", 7);
        jobManager.addSkillRequirement("Machine Learning", 8);
        jobManager.addSkillRequirement("Deep Learning", 7);
        jobManager.addSkillRequirement("TensorFlow", 7);
        jobManager.addSkillRequirement("SQL", 8);
        jobManager.addSkillRequirement("Data Visualization", 7);
        jobManager.addSkillRequirement("Pandas", 8);
        jobManager.addSkillRequirement("Scikit-learn", 7);
        jobManager.addSkillRequirement("Big Data", 6);
    }

    // Network Engineer skills initialization
    public static void initializeNetworkEngineerSkills(JobManager jobManager) {
        jobManager.addSkillRequirement("TCP/IP", 8);
        jobManager.addSkillRequirement("Routing", 7);
        jobManager.addSkillRequirement("Switching", 7);
        jobManager.addSkillRequirement("Firewall Configuration", 8);
        jobManager.addSkillRequirement("DNS", 7);
        jobManager.addSkillRequirement("VPN", 6);
        jobManager.addSkillRequirement("Wi-Fi Configuration", 7);
        jobManager.addSkillRequirement("Network Security", 8);
        jobManager.addSkillRequirement("Load Balancing", 6);
        jobManager.addSkillRequirement("Cloud Networking", 7);
    }

    // **Reduced** Business Analyst skills initialization
    public static void initializeBusinessAnalystSkills(JobManager jobManager) {
        // Reduced to essential skills only
        jobManager.addSkillRequirement("Requirements Gathering", 8);
        jobManager.addSkillRequirement("Data Analysis", 7);
        jobManager.addSkillRequirement("Business Process Modeling", 8);
        jobManager.addSkillRequirement("SQL", 7);
        jobManager.addSkillRequirement("Excel", 8);
    }

    // Cloud Engineer skills initialization
    public static void initializeCloudEngineerSkills(JobManager jobManager) {
        jobManager.addSkillRequirement("AWS", 8);
        jobManager.addSkillRequirement("Azure", 7);
        jobManager.addSkillRequirement("Google Cloud", 7);
        jobManager.addSkillRequirement("Cloud Security", 8);
        jobManager.addSkillRequirement("Terraform", 7);
        jobManager.addSkillRequirement("Cloud Architecture", 8);
        jobManager.addSkillRequirement("CI/CD", 7);
        jobManager.addSkillRequirement("Containers", 7);
        jobManager.addSkillRequirement("Kubernetes", 7);
        jobManager.addSkillRequirement("Monitoring Tools", 6);
    }

    // Security Engineer skills initialization
    public static void initializeSecurityEngineerSkills(JobManager jobManager) {
        jobManager.addSkillRequirement("Network Security", 8);
        jobManager.addSkillRequirement("Firewall Management", 8);
        jobManager.addSkillRequirement("Penetration Testing", 7);
        jobManager.addSkillRequirement("Risk Assessment", 8);
        jobManager.addSkillRequirement("Security Protocols", 8);
        jobManager.addSkillRequirement("Cryptography", 7);
        jobManager.addSkillRequirement("Vulnerability Management", 7);
        jobManager.addSkillRequirement("Incident Response", 7);
        jobManager.addSkillRequirement("Cloud Security", 8);
        jobManager.addSkillRequirement("Compliance", 7);
    }
}
