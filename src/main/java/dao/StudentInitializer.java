package dao;

import adt.*;

import entity.Student;

public class StudentInitializer {
    public ListInterface<Student> getStudents() {

        ListInterface<Student> students = new ArrayList<>();

        students.add(new Student("Alex Johnson",SkillProficiencyInitializer.getDeveloperProficiencies(), "New York", 2));
        students.add(new Student("Sarah Chen", SkillProficiencyInitializer.getDeveloperProficiencies(), "San Francisco", 8));
        students.add(new Student("Michael Lee", SkillProficiencyInitializer.getQAProficiencies(), "Chicago", 4));
        students.add(new Student("Emma Rodriguez", SkillProficiencyInitializer.getUXProficiencies(), "Boston", 5));
        students.add(new Student("James Wilson", SkillProficiencyInitializer.getPMProficiencies(), "Seattle", 7));

        students.add(new Student("David Garcia",  SkillProficiencyInitializer.getUXProficiencies(), "Austin", 6));

        students.add(new Student("Sophia Kim", SkillProficiencyInitializer.getMobileDeveloperProficiencies(), "Toronto", 4));
        students.add(new Student("John Smith", SkillProficiencyInitializer.getBackendDeveloperProficiencies(), "Los Angeles", 5));
        students.add(new Student("Olivia Martinez", SkillProficiencyInitializer.getQAEngineerProficiencies(), "Miami", 6));
        students.add(new Student("Liam Brown", SkillProficiencyInitializer.getProductManagerProficiencies(), "New York", 7));
        students.add(new Student("Ethan Davis", SkillProficiencyInitializer.getDevOpsEngineerProficiencies(), "San Francisco", 4));
        students.add(new Student("Mia Wilson", SkillProficiencyInitializer.getDataScientistProficiencies(), "Chicago", 5));
        students.add(new Student("Noah Clark", SkillProficiencyInitializer.getNetworkEngineerProficiencies(), "Seattle", 4));
        students.add(new Student("Ava Lewis", SkillProficiencyInitializer.getBusinessAnalystProficiencies(), "Toronto", 6));
        students.add(new Student("Isabella Walker", SkillProficiencyInitializer.getCloudEngineerProficiencies(), "Dallas", 5));
        return students;
    }

    public static void main(String[] args) {
        StudentInitializer studentInitializer = new StudentInitializer();
        ListInterface<Student> students = studentInitializer.getStudents();
        StudentDAO studentDAO = new StudentDAO();
        studentDAO.saveToFile(students);
    }

}