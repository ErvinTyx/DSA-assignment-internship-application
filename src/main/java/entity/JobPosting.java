package entity;

import adt.ArrayList;
public class JobPosting {
    private String id;
    private String title;
    private String description;
    private ArrayList<SkillRequirement> requiredSkills = new ArrayList<>();
    private String location;
    private double[] salaryRange;
    
    public JobPosting(String id, String title) {
        this.id = id;
        this.title = title;
    }
}
