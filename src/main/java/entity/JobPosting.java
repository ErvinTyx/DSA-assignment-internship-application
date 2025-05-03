package entity;

import adt.*;
import java.io.Serializable;

public class JobPosting implements Serializable {
    private String id;
    private String title;
    private String description;
    private ListInterface<SkillRequirement> requiredSkills = new ArrayList<>();
    private String location;
    private double[] salaryRange;
    private int experienceRequired;
    // score for match
    private int locationImprotance;
    private int experienceImportance;
    private int skillImportance;
    private static int COUNTER = 0;

    public JobPosting(JobPosting jobPosting) {
        this.id = jobPosting.getId();
        this.title = jobPosting.getTitle();
        this.description = jobPosting.getDescription();
        this.requiredSkills = jobPosting.getRequiredSkills();
        this.location = jobPosting.getLocation();
        this.salaryRange = jobPosting.getSalaryRange();
        this.experienceRequired = jobPosting.getExperienceRequired();
        this.locationImprotance = jobPosting.getLocationImprotance();
        this.experienceImportance = jobPosting.getExperienceImportance();
        this.skillImportance = jobPosting.getSkillImportance();
    }

    // constructor
    public JobPosting(String title, String description, ListInterface<SkillRequirement> requiredSkills, String location,
            double[] salaryRange, int experienceRequired, int locationImprotance, int experienceImportance,
            int skillImportance) {
        COUNTER++;
        this.id = "J" + COUNTER;
        this.title = title;
        this.description = description;
        this.requiredSkills = requiredSkills;
        this.location = location;
        this.salaryRange = salaryRange;
        this.experienceRequired = experienceRequired;
        this.locationImprotance = locationImprotance;
        this.experienceImportance = experienceImportance;
        this.skillImportance = skillImportance;
    }

    // Getters
    public int getLocationImprotance() {
        return locationImprotance;
    }

    public int getExperienceImportance() {
        return experienceImportance;
    }

    public int getSkillImportance() {
        return skillImportance;
    }

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
        String result = "";
        result += "\n\n\t--------------------------------------------\n";
        result += "\tID              : " + id + "\n";
        result += "\tTitle           : " + title + "\n";
        result += "\tDescription     : " + description + "\n";
        result += "\tLocation        : " + location + "\n";
        result += "\tExperience Req  : " + experienceRequired + " years\n";
        result += String.format("\tSalary Range    : RM %.2f ~ RM %.2f\n", salaryRange[0], salaryRange[1]);
        result += "\tExperience Imp. : " + experienceImportance + "\n";
        result += "\tLocation Imp.   : " + locationImprotance + "\n";
        result += "\tSkill Imp.      : " + skillImportance + "\n";
        result += "\t--------------------------------------------\n";
        result += getStringSkillRequirement();
        return result;
    }


    public String getStringSkillRequirement() {
        String result = "\tSkill Requirements:\n";
        result += "\t+--------------------------------+------------+\n";
        result += "\t| Skill Name                     | Importance |\n";
        result += "\t+--------------------------------+------------+\n";
        for (int i = 0; i < requiredSkills.size(); i++) {
            result += String.format("\t| %-30s | %-10d |\n",
                    requiredSkills.get(i).getSkillName(),
                    requiredSkills.get(i).getImportance());
        }
        result += "\t+--------------------------------+------------+\n";
        return result;
    }
    

}
