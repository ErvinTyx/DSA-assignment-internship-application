package dao;

import control.ApplicantManager;
import entity.SkillProficiency;
import adt.ListInterface;

public class StudentInitializer {

    public static void initialize(ApplicantManager applicantManager) {
        // Clear before initializing
        applicantManager.clearSkillProficiencies();

        // Student 1: Junior Developer
        applicantManager.registerStudent("Alex Johnson", "New York", 2);
        applicantManager.updateStudentSkills("S1", SkillProficiencyInitializer.getDeveloperProficiencies());

        // Student 2: Senior Developer
        applicantManager.registerStudent("Sarah Chen", "San Francisco", 8);
        applicantManager.updateStudentSkills("S2", SkillProficiencyInitializer.getDeveloperProficiencies());

        // Student 3: QA Engineer
        applicantManager.registerStudent("Michael Lee", "Chicago", 4);
        applicantManager.updateStudentSkills("S3", SkillProficiencyInitializer.getQAProficiencies());

        // Student 4: UX Designer
        applicantManager.registerStudent("Emma Rodriguez", "Boston", 5);
        applicantManager.updateStudentSkills("S4", SkillProficiencyInitializer.getUXProficiencies());

        // Student 5: Project Manager
        applicantManager.registerStudent("James Wilson", "Seattle", 7);
        applicantManager.updateStudentSkills("S5", SkillProficiencyInitializer.getPMProficiencies());

        // Student 6: Full Stack Developer (Dev + QA)
        ListInterface<SkillProficiency> fullStackSkills = SkillProficiencyInitializer.getDeveloperProficiencies();
        ListInterface<SkillProficiency> qaSkills = SkillProficiencyInitializer.getQAProficiencies();
        for (int i = 0; i < qaSkills.size(); i++) {
            fullStackSkills.add(qaSkills.get(i));
        }
        applicantManager.registerStudent("David Garcia", "Austin", 6);
        applicantManager.updateStudentSkills("S6", fullStackSkills);

        // Student 7: Mobile Developer
        applicantManager.registerStudent("Sophia Kim", "Toronto", 4);
        applicantManager.updateStudentSkills("S7", SkillProficiencyInitializer.getMobileDeveloperProficiencies());


        // Student 8: Backend Developer
        applicantManager.registerStudent("John Smith", "Los Angeles", 5);
        applicantManager.updateStudentSkills("S8", SkillProficiencyInitializer.getBackendDeveloperProficiencies());

        // Student 9: QA Engineer (Advanced)
        applicantManager.registerStudent("Olivia Martinez", "Miami", 6);
        applicantManager.updateStudentSkills("S9", SkillProficiencyInitializer.getQAEngineerProficiencies());

        // Student 10: Product Manager
        applicantManager.registerStudent("Liam Brown", "New York", 7);
        applicantManager.updateStudentSkills("S10", SkillProficiencyInitializer.getProductManagerProficiencies());

        // Student 11: DevOps Engineer
        applicantManager.registerStudent("Ethan Davis", "San Francisco", 4);
        applicantManager.updateStudentSkills("S11", SkillProficiencyInitializer.getDevOpsEngineerProficiencies());

        // Student 12: Data Scientist
        applicantManager.registerStudent("Mia Wilson", "Chicago", 5);
        applicantManager.updateStudentSkills("S12", SkillProficiencyInitializer.getDataScientistProficiencies());

        // Student 13: Network Engineer
        applicantManager.registerStudent("Noah Clark", "Seattle", 4);
        applicantManager.updateStudentSkills("S13", SkillProficiencyInitializer.getNetworkEngineerProficiencies());

        // Student 14: Business Analyst
        applicantManager.registerStudent("Ava Lewis", "Toronto", 6);
        applicantManager.updateStudentSkills("S14", SkillProficiencyInitializer.getBusinessAnalystProficiencies());

        // Student 15: Cloud Engineer
        applicantManager.registerStudent("Isabella Walker", "Dallas", 5);
        applicantManager.updateStudentSkills("S15", SkillProficiencyInitializer.getCloudEngineerProficiencies());

        applicantManager.clearSkillProficiencies();
    }
}
