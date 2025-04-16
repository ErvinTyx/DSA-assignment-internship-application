package entity;

import adt.ArrayList;
import adt.ListInterface;

public class JobPosting {
    private String id;
    private String title;
    private String description;
    private ListInterface<SkillRequirement> requiredSkills = new ArrayList<>();
    private String location;
    private double[] salaryRange;
    private int experienceRequired;
    private static int COUNTER = 0;

    // constructor
    public JobPosting(String title, String description, ListInterface<SkillRequirement> requiredSkills, String location,
                      double[] salaryRange, int experienceRequired) {
        COUNTER++;
        this.id = "J" + COUNTER;
        this.title = title;
        this.description = description;
        this.requiredSkills = requiredSkills;
        this.location = location;
        this.salaryRange = salaryRange;
        this.experienceRequired = experienceRequired;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public ListInterface<SkillRequirement> getRequiredSkills() {
        return requiredSkills;
    }

    public String getLocation() {
        return location;
    }

    public double[] getSalaryRange() {
        return salaryRange;
    }

    public int getExperienceRequired() {
        return experienceRequired;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRequiredSkills(ListInterface<SkillRequirement> requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setSalaryRange(double[] salaryRange) {
        this.salaryRange = salaryRange;
    }

    public void setExperienceRequired(int experienceRequired) {
        this.experienceRequired = experienceRequired;
    }

    @Override
    public String toString() {
        return " [id=" + id + ", title=" + title + ", description=" + description +
                ", location=" + location + ", experienceRequired=" + experienceRequired +
                ", salaryRange=" + salaryRange[0] + "~" + salaryRange[1] + "]\n" +
                "requiredSkills=" + getStringSkillRequirement();
    }

    public String getStringSkillRequirement() {
        String result = "";
        for (int i = 0; i < requiredSkills.size(); i++) {
            result += requiredSkills.get(i).toString();
        }
        return result;
    }
}
