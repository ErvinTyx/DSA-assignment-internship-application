package entity;

import adt.ArrayList;

public class Student {
    private String id;
    private String name;
    private ArrayList<SkillProficiency> skills = new ArrayList<>();
    private String location;
    private int experience;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addSkill(SkillProficiency skill) {
        skills.add(skill);
    }
    // Getters/Setters
}
