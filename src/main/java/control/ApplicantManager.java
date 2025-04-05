package control;

import adt.ArrayList;
import entity.Student;
import entity.SkillProficiency;

public class ApplicantManager {
    private ArrayList<Student> applicants = new ArrayList<>();

    private ArrayList<SkillProficiency> skills = new ArrayList<>();
    

    public void registerStudent(Student student) {
        applicants.add(student);
    }

    public void clearSkillProficiencies() {
        skills.clear();
    }

    public void addSkillProficiency(String skillname, int experience) {
        skills.add(new SkillProficiency(skillname, experience));
    }

    public ArrayList<SkillProficiency> getSkillProficiencies() {
        ArrayList<SkillProficiency> skillProficiencies = new ArrayList<>();
        for (int i = 0; i < skills.size(); i++) {
            skillProficiencies.add(skills.get(i));
        }
        clearSkillProficiencies();
        return skillProficiencies;
    }

    

    public boolean getStudentById(String id) {
        for (int i = 0; i < applicants.size(); i++) {
            if (applicants.get(i).getId().equals(id)) {
                return true;
            }
        }
        return false;
    }
    

    public void registerStudent(String name, String location, int experience) {
        applicants.add(new Student(name, getSkillProficiencies(), location, experience));
    }
    

    public boolean removeStudentById(String id) {
        for (int i = 0; i < applicants.size(); i++) {
            if (applicants.get(i).getId().equals(id)) {
                applicants.remove(i+1);
                return true;
            }
        }
        return false;
    }

    public void updateStudentName(String id, String name) {
        for (int i = 0; i < applicants.size(); i++) {
            if (applicants.get(i).getId().equals(id)) {
                applicants.get(i).setName(name);
            }
        }
    }

    public void updateStudentLocation(String id, String location) {
        for (int i = 0; i < applicants.size(); i++) {
            if (applicants.get(i).getId().equals(id)) {
                applicants.get(i).setLocation(location);
            }
        }
    }

    public void updateStudentExperience(String id, int experience) {
        for (int i = 0; i < applicants.size(); i++) {
            if (applicants.get(i).getId().equals(id)) {
                applicants.get(i).setExperience(experience);
            }
        }
    }

    public void updateStudentSkills(String id, ArrayList<SkillProficiency> skills) {
        for (int i = 0; i < applicants.size(); i++) {
            if (applicants.get(i).getId().equals(id)) {
                applicants.get(i).setSkills(skills);
            }
        }
    }

    public String listSpecificApplicants(String id) {
        // view student with specific id
        for (int i = 0; i < applicants.size(); i++) {
            if (applicants.get(i).getId().equals(id)) {
                return applicants.get(i).toString();
            }
        }
        return null;
    }
}
