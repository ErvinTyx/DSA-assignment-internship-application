package entity;

import adt.ArrayList;
import adt.ListInterface;
import java.io.Serializable;

public class Student implements Serializable {
    private String id;
    private String name;
    private ListInterface<SkillProficiency> skills = new ArrayList<>();
    private String location;
    private int experience;
    private static int COUNTER;

    // constructor
    public Student(String name, ListInterface<SkillProficiency> skills, String location, int experience) {
        COUNTER++;
        this.id = "S" + COUNTER;
        this.name = name;
        this.skills = skills;
        this.location = location;
        this.experience = experience;
    }

    public Student(Student student) {
        this.id = student.getId();
        this.name = student.getName();
        this.skills = student.getSkillProficiencies();
        this.location = student.getLocation();
        this.experience = student.getExperience();
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSkills(ListInterface<SkillProficiency> skills) {
        this.skills = skills;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ListInterface<SkillProficiency> getSkillProficiencies() {
        return skills;
    }

    public String getLocation() {
        return location;
    }

    public int getExperience() {
        return experience;
    }

    @Override
    public String toString() {
        String studentInfo = "";

        // Basic profile details
        studentInfo += "ID        : " + id + "\n";
        studentInfo += "Name      : " + name + "\n";
        studentInfo += "Location  : " + location + "\n";
        studentInfo += "Experience: " + experience + " years\n";

        // Skill Section
        studentInfo += "\nSkills:\n";

        if (skills == null || skills.isEmpty()) {
            studentInfo += "  No Skill Proficiency\n";
        } else {
            studentInfo += "+-----+-------------------+-------------+\n";
            studentInfo += "| No. | Skill             | Proficiency |\n";
            studentInfo += "+-----+-------------------+-------------+\n";
            for (int i = 0; i < skills.size(); i++) {
                SkillProficiency sp = skills.get(i);
                studentInfo += String.format("| %-3d | %-17s | %-11d |\n",
                        i + 1, sp.getSkillName(), sp.getProficiency());
            }
            studentInfo += "+-----+-------------------+-------------+\n";
        }

        return studentInfo;
    }

}
