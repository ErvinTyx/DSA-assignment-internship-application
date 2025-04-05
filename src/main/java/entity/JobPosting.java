package entity;

import adt.ArrayList;

public class JobPosting {
    private String id;
    private String title;
    private String description;
    private ArrayList<SkillRequirement> requiredSkills = new ArrayList<>();
    private String location;
    private double[] salaryRange;
    private static int COUNTER = 0;

    // constructor
    public JobPosting(String title, String description, ArrayList<SkillRequirement> requiredSkills, String location,
            double[] salaryRange) {
        COUNTER++;
        this.id = "J" + COUNTER;
        this.title = title;
        this.description = description;
        this.requiredSkills = requiredSkills;
        this.location = location;
        this.salaryRange = salaryRange;
    }

    // getters
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<SkillRequirement> getRequiredSkills() {
        return requiredSkills;
    }

    public String getLocation() {
        return location;
    }

    public double[] getSalaryRange() {
        return salaryRange;
    }

    // setters
    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRequiredSkills(ArrayList<SkillRequirement> requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setSalaryRange(double[] salaryRange) {
        this.salaryRange = salaryRange;
    }

    public String toString() {
        return " [id=" + id + ", title=" + title + ", description=" + description + ", location=" + location
                + ", salaryRange=" + salaryRange[0] + "~"+ salaryRange[1] + "]\n" + "requiredSkills="
                + getStringSkillRequirement();
    }

    public String getStringSkillRequirement() {
        String result = "";
        for (int i = 0; i < requiredSkills.size(); i++) {
            result += requiredSkills.get(i).toString();
        }
        return result;
    }
}
