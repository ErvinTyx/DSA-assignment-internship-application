package control;

import adt.*;
import boundary.ApplicantProfileUI;
import dao.StudentDAO;
import entity.Student;
import entity.SkillProficiency;
import utility.MessageUI;

public class ApplicantManager {
    private ListInterface<Student> students = new ArrayList<>();
    private final SkillProficiencyManager skillProficiencyManager = new SkillProficiencyManager(new ArrayList<>());
    private final ApplicantProfileUI applicantProfileUI = new ApplicantProfileUI();
    private int index = -1;
    private final StudentDAO studentDAO = new StudentDAO();
    private MatchingEngine matchingEngine;
    private InterviewSchedulerManager interviewSchedulerManager;
    

    public ApplicantManager() {
        students = studentDAO.retrieveFromFile();
    }

    public void runApplicantProfile() {
        int choice = 0;
        do {
            choice = applicantProfileUI.getMenuChoiceLogin();
            switch (choice) {
                case 1 -> login();
                case 2 -> signUp();
                case 3 -> {
                    MessageUI.displayLogOutMessage();
                }
            }
        } while (choice != 3);
        studentDAO.retrieveFromFile();
    }

    public void login() {
        index = -1;
        String id = applicantProfileUI.inputApplicantId();
        for (int i = 0; i < students.size(); i++) {
            if (id.equals(students.get(i).getId())) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            int choice = 0;
            do {
                choice = applicantProfileUI.getMenuChoice();
                switch (choice) {
                    case 1:
                        applicantProfileUI.listStudentInfo(displayStudentInfo(students.get(index)));
                        break;
                    case 2:
                        // update
                        int updateChoice = applicantProfileUI.getUpdateMenuChoice();
                        switch (updateChoice) {
                            case 1 -> {
                                String name = applicantProfileUI.inputApplicantName();
                                students.get(index).setName(name);
                        }
                            case 2 -> {
                                int experience = applicantProfileUI.inputApplicantWorkingExperience();
                                students.get(index).setExperience(experience);
                        }
                            case 3 -> {
                                String location = applicantProfileUI.inputApplicantLocation();
                                students.get(index).setLocation(location);
                        }
                            case 4 -> {
                                skillProficiencyManager
                                        .runSkillProficiency(students.get(index).getSkillProficiencies());
                                ListInterface<SkillProficiency> skills = skillProficiencyManager.getSkillProficiencys();
                                students.get(index).setSkills(skills);
                        }
                            default -> MessageUI.displayInvalidChoiceMessage();
                        }
                        applicantProfileUI.listStudentInfo(displayStudentInfo(students.get(index)));
                        break;


                    case 4:
                        matchingEngine = new MatchingEngine();
                        matchingEngine.runLookForJobs(students.get(index));
                        break;
                    case 5:
                        // schedule
                        matchingEngine = new MatchingEngine();
                        interviewSchedulerManager = new InterviewSchedulerManager(matchingEngine);
                        interviewSchedulerManager.runInterviewScheduler(students.get(index).getId());
                        break;
                    case 3:
                        // delete
                        String choiceDelete = applicantProfileUI.comfimationDelete();
                        if (!choiceDelete.equals("y")) {
                            break;
                        }
                        choice = 6;
                        students.remove(index + 1);
                        MessageUI.displayDeleteApplicantAccountMessage();
                    case 6:
                        MessageUI.displayLogOutMessage();
                        index = -1;
                        break;
                    default:
                        MessageUI.displayInvalidChoiceMessage();
                }
            } while (choice != 6);
        }
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
