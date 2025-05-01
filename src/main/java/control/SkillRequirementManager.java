package control;

import adt.*;
import boundary.SkillRequirementUI;
import entity.SkillRequirement;
import utility.MessageUI;

public class SkillRequirementManager {
    private ListInterface<SkillRequirement> skillRequirements = new ArrayList<>();
    private SkillRequirementUI skillRequirementUI = new SkillRequirementUI();

    public SkillRequirementManager(ListInterface<SkillRequirement> skillRequirements) {
        this.skillRequirements = skillRequirements;
    }

    public void runSkillRequirement() {
        int choice = 0;
        do {
            choice = skillRequirementUI.getMenuChoice();
            switch (choice) {
                case 1:
                    addNewSkillRequirement();
                    skillRequirementUI.listAllSkillRequirements(getAllSkillRequirements());
                    break;
                case 2:
                    displaySkillRequirement();
                    break;
                case 3:
                    updateSkillRequirement();
                    displaySkillRequirement();
                    break;
                case 4:
                    deleteSkillRequirement();
                    displaySkillRequirement();
                    break;
                case 5:
                    MessageUI.displayExitMessageSkillRequirement();
                    break;
                default:
                    MessageUI.displayInvalidChoiceMessage();
            }
        } while (choice != 5);
    }

    protected void runSkillRequirement(ListInterface<SkillRequirement> skillRequirements) {
        this.skillRequirements = skillRequirements;
        int choice = 0;
        do {
            choice = skillRequirementUI.getMenuChoice();
            switch (choice) {
                case 1:
                    addNewSkillRequirement();
                    skillRequirementUI.listAllSkillRequirements(getAllSkillRequirements());
                    break;
                case 2:
                    displaySkillRequirement();
                    break;
                case 3:
                    updateSkillRequirement();
                    displaySkillRequirement();
                    break;
                case 4:
                    deleteSkillRequirement();
                    displaySkillRequirement();
                    break;
                case 5:
                    MessageUI.displayExitMessageSkillRequirement();
                    break;
                default:
                    MessageUI.displayInvalidChoiceMessage();
            }
        } while (choice != 5);
    }

    public void addNewSkillRequirement() {
        String name = skillRequirementUI.inputSkillRequirementName();
        int importance = skillRequirementUI.inputSkillRequirementImportance();
        SkillRequirement skillRequirement = new SkillRequirement(name, importance);
        skillRequirements.add(skillRequirement);
    }

    public void updateSkillRequirement() {
        int index = skillRequirementUI.inputSkillRequirementIndex();
        if (index > 0 && index <= skillRequirements.size()) {
            String name = skillRequirementUI.inputSkillRequirementName();
            int importance = skillRequirementUI.inputSkillRequirementImportance();
            SkillRequirement skillRequirement = new SkillRequirement(name, importance);
            skillRequirements.set(index - 1, skillRequirement);
        } else {
            MessageUI.displayInvalidChoiceMessage();
        }
    }

    public String getAllSkillRequirements() {
        String allSkillRequirements = "";
        for (int i = 0; i < skillRequirements.size(); i++) {
            allSkillRequirements += "\t\t" + (i + 1) + ". " + skillRequirements.get(i).toString();
        }
        return allSkillRequirements;
    }

    public void displaySkillRequirement() {
        skillRequirementUI.listAllSkillRequirements(getAllSkillRequirements());
    }

    public void deleteSkillRequirement() {
        int index = skillRequirementUI.inputSkillRequirementIndex();
        if (index > 0 && index <= skillRequirements.size()) {
            skillRequirements.remove(index - 1);
        } else {
            MessageUI.displayInvalidChoiceMessage();
        }
    }

    protected ListInterface<SkillRequirement> getSkillRequirements() {
        ListInterface<SkillRequirement> skillRequirements = new ArrayList<>();
        for (int i = 0; i < this.skillRequirements.size(); i++) {
            skillRequirements.add(this.skillRequirements.get(i));
        }
        this.skillRequirements.clear();
        return skillRequirements;
    }
    // public static void main(String[] args) {
    // SkillRequirementManager skillRequirementManager = new
    // SkillRequirementManager();
    // skillRequirementManager.runSkillRequirement();
    // }
}
