package entity;

public class SkillProficiency {
    private String skillName;
    private int proficiency;
    
    public SkillProficiency(String skillName, int proficiency) {
        this.skillName = skillName;
        this.proficiency = proficiency;
    }

    public SkillProficiency(SkillProficiency skillProficiency) {
        this.skillName = skillProficiency.getSkillName();
        this.proficiency = skillProficiency.getProficiency();
    }
    
    // Getters
    public String getSkillName() {
        return skillName;
    }
    public int getProficiency() {
        return proficiency;
    }
    
    // Setters
    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }
    public void setProficiency(int proficiency) {
        this.proficiency = proficiency;
    }

    @Override
    public String toString() {
        return "\n{" +
                "skillName='" + skillName + '\'' +
                ", proficiency=" + proficiency +
                '}';
    }
}