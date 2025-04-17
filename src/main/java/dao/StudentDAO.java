package dao;

import adt.ArrayList;
import adt.ListInterface;
import entity.SkillProficiency;
import entity.Student;

import java.io.*;

public class StudentDAO {

    public static void saveStudentsToFile(String filename, ListInterface<Student> students) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int i = 0; i < students.size(); i++) {
                Student student = students.get(i);
                writer.write(student.getId() + "|" + student.getName() + "|" + student.getLocation() + "|" + student.getExperience());
                writer.newLine();
                for (int j = 0; j < student.getSkills().size(); j++) {
                    SkillProficiency skill = student.getSkills().get(j);
                    writer.write(skill.getSkillName() + "," + skill.getProficiency());
                    writer.newLine();
                }
                writer.write("#"); // Delimiter between students
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving students: " + e.getMessage());
        }
    }

    public static ListInterface<Student> loadStudentsFromFile(String filename) {
        ListInterface<Student> students = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            String id = "", name = "", location = "";
            int experience = 0;
            ListInterface<SkillProficiency> skills = new ArrayList<>();
    
            while ((line = reader.readLine()) != null) {
                if (line.equals("#")) {
                    Student student = new Student(name, skills, location, experience);
                    student.setId(id); // Restore original ID from file
                    students.add(student);
                    skills = new ArrayList<>(); // Reset for next student
                } else if (line.contains("|")) {
                    String[] parts = line.split("\\|");
                    id = parts[0];
                    name = parts[1];
                    location = parts[2];
                    experience = Integer.parseInt(parts[3]);
                } else if (line.contains(",")) {
                    String[] skillParts = line.split(",");
                    skills.add(new SkillProficiency(skillParts[0], Integer.parseInt(skillParts[1])));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading students: " + e.getMessage());
        }
        return students;
    }
    
}
