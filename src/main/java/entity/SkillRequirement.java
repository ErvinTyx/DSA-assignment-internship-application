package entity;

public class SkillRequirement {
    private String skillName;
    private int importance;

    public SkillRequirement(String skillName, int importance) {
        this.skillName = skillName;
        this.importance = importance;
    }

    @Override
    public String toString() {
        return "\n{" +
                "skillName='" + skillName + '\'' +
                ", importance=" + importance +
                '}';
    }
}
