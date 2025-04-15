package dao;

import control.JobManager;

public class SkillRequiredInitializer {

    public static void initializeDeveloperSkills(JobManager jobManager) {
        // Adding Developer skills directly to the jobManager
        jobManager.addSkillRequirement("Java", 8);
        jobManager.addSkillRequirement("Spring Boot", 7);
    }

    public static void initializeQASkills(JobManager jobManager) {
        // Adding QA skills directly to the jobManager
        jobManager.addSkillRequirement("HTML", 7);
        jobManager.addSkillRequirement("CSS", 6);
        jobManager.addSkillRequirement("JavaScript", 8);
    }

    public static void initializeUXSkills(JobManager jobManager) {
        // Adding UX skills directly to the jobManager
        jobManager.addSkillRequirement("Figma", 7);
        jobManager.addSkillRequirement("User Research", 6);
    }

    public static void initializePMSkills(JobManager jobManager) {
        // Adding PM skills directly to the jobManager
        jobManager.addSkillRequirement("Agile", 9);
        jobManager.addSkillRequirement("Scrum", 8);
        jobManager.addSkillRequirement("Communication", 7);
    }
}
