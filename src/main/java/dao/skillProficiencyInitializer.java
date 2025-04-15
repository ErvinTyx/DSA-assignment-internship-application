package dao;

import adt.ArrayList;
import adt.ListInterface;
import entity.SkillProficiency;

public class skillProficiencyInitializer {

    public static ListInterface<SkillProficiency> getDeveloperProficiencies() {
        ListInterface<SkillProficiency> proficiencies = new ArrayList<>();
        proficiencies.add(new SkillProficiency("Java", 5));
        proficiencies.add(new SkillProficiency("Spring Boot", 4));
        proficiencies.add(new SkillProficiency("SQL", 3));
        return proficiencies;
    }

    public static ListInterface<SkillProficiency> getQAProficiencies() {
        ListInterface<SkillProficiency> proficiencies = new ArrayList<>();
        proficiencies.add(new SkillProficiency("Selenium", 4));
        proficiencies.add(new SkillProficiency("JUnit", 3));
        proficiencies.add(new SkillProficiency("Manual Testing", 5));
        return proficiencies;
    }

    public static ArrayList<SkillProficiency> getUXProficiencies() {
        ArrayList<SkillProficiency> proficiencies = new ArrayList<>();
        proficiencies.add(new SkillProficiency("Figma", 4));
        proficiencies.add(new SkillProficiency("User Research", 3));
        proficiencies.add(new SkillProficiency("Prototyping", 4));
        return proficiencies;
    }

    public static ArrayList<SkillProficiency> getPMProficiencies() {
        ArrayList<SkillProficiency> proficiencies = new ArrayList<>();
        proficiencies.add(new SkillProficiency("Agile", 5));
        proficiencies.add(new SkillProficiency("Scrum", 4));
        proficiencies.add(new SkillProficiency("Communication", 5));
        return proficiencies;
    }
}
