package control;

import adt.ArrayList;
import entity.Student;

public class ApplicantManager {
    private ArrayList<Student> applicants = new ArrayList<>();
    

    public void registerStudent(Student student) {
        applicants.add(student);
    }
}
