package entity;

import adt.ArrayList;
import adt.ListInterface;

public class Student {
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
        this.skills = student.getSkills();
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

    public ListInterface<SkillProficiency> getSkills() {
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
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", experience=" + experience +
                "\nSkill Proficiencies: " + getStringSkillProficiencies() +
                '}';
    }

    public String getStringSkillProficiencies() {
        String skillProficiencies = "";
        for (int i = 0; i < skills.size(); i++) {
            skillProficiencies = skillProficiencies + skills.get(i).toString();
        }
        return skillProficiencies;
    }
}