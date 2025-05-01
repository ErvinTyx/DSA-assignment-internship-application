package control;

import adt.*;
import boundary.ApplicantProfileUI;
import entity.Student;
import entity.SkillProficiency;


public class ApplicantManager {
    private ListInterface<Student> students = new ArrayList<>();
    private SkillProficiencyManager skillProficiencyManager = new SkillProficiencyManager(new ArrayList<>());
    private ApplicantProfileUI applicantProfileUI = new ApplicantProfileUI();
    private int index=-1;

    public void runApplicantProfile() {
        int choice = 0;
        do {
            choice = applicantProfileUI.getMenuChoiceLogin();
            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    signUp();
                    break;
                case 3:
                    break;
            }
        } while (choice != 3);
    }

    public void login() {

    }

    public void signUp() {
        String name = applicantProfileUI.inputApplicantName();
        String location = applicantProfileUI.inputApplicantLocation();
        int experience = applicantProfileUI.inputApplicantWorkingExperience();
        skillProficiencyManager.runSkillProficiency();
        ListInterface<SkillProficiency> skills = skillProficiencyManager.getSkillProficiencys();
        Student student = new Student(name, skills, location, experience);
        
        applicantProfileUI.listStudentInfo(displayStudentInfo(student));
        students.add(student);
        applicantProfileUI.displayEnterToContinueMessage();
    }

    private String displayStudentInfo(Student student) {
        String result = "";
        result += student.toString();
        return result;
    }

    public static void main(String[] args) {
        ApplicantManager applicantManager = new ApplicantManager();
        applicantManager.runApplicantProfile();
    }

}
