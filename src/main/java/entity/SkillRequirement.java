package entity;

public class SkillRequirement {
    private String skillName;
    private int importance;

    public SkillRequirement(String skillName, int importance) {
        this.skillName = skillName;
        this.importance = importance;
    }

    public SkillRequirement(SkillRequirement skillRequirement) {
        this.skillName = skillRequirement.getSkillName();
        this.importance = skillRequirement.getImportance();
    }

    public String getSkillName() {
        return skillName;
    }

    public int getImportance() {
        return importance;
    }

    @Override
    public String toString() {
        return String.format("| %-15s | %-10d |%n", skillName, importance);
    }
}
