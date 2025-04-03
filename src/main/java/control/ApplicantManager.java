package control;

import adt.ArrayList;
import entity.Student;
import entity.SkillProficiency;

public class ApplicantManager {
    private ArrayList<Student> applicants = new ArrayList<>();
    

    public void registerStudent(Student student) {
        applicants.add(student);
    }

    public void registerStudent(String name, ArrayList<SkillProficiency> skills, String location, int experience) {
        applicants.add(new Student(name, skills, location, experience));
    }
    

    public void removeStudentById(Student student) {
        // 
    }
}
