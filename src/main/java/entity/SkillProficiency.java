package entity;

public class SkillProficiency {
    private String skillName;
    private int proficiency;
    
    public SkillProficiency(String skillName, int proficiency) {
        this.skillName = skillName;
        this.proficiency = proficiency;
    }
    // Getters
    public String getSkillName() {
        return skillName;
    }
    public int getProficiency() {
        return proficiency;
    }
}