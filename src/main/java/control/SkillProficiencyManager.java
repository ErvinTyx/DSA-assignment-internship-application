package control;

import adt.*;
import boundary.SkillProficiencyUI;
import entity.SkillProficiency;
import utility.MessageUI;

public class SkillProficiencyManager {
        private ListInterface<SkillProficiency> skillProficiencys = new ArrayList<>();
    private SkillProficiencyUI skillProficiencyUI = new SkillProficiencyUI();

    public SkillProficiencyManager(ListInterface<SkillProficiency> skillProficiencys) {
        this.skillProficiencys = skillProficiencys;
    }

    // // remove this after testing
    // public SkillProficiencyManager() {

    // }

    public void runSkillProficiency() {
        int choice = 0;
        do {
            choice = skillProficiencyUI.getMenuChoice();
            switch (choice) {
                case 1:
                    addNewSkillProficiency();
                    skillProficiencyUI.listAllSkillProficiencys(getAllSkillProficiencys());
                    break;
                case 2:
                    displaySkillProficiency();
                    break;
                case 3:
                    updateSkillProficiency();
                    displaySkillProficiency();
                    break;
                case 4:
                    deleteSkillProficiency();
                    displaySkillProficiency();
                    break;
                case 5:
                    MessageUI.displayExitMessageSkillProficiency();
                    break;
                default:
                    MessageUI.displayInvalidChoiceMessage();
            }
        } while (choice != 5);
    }

    public void addNewSkillProficiency() {
        String name = skillProficiencyUI.inputSkillProficiencyName();
        int importance = skillProficiencyUI.inputSkillProficiencyImportance();
        SkillProficiency skillProficiency = new SkillProficiency(name, importance);
        skillProficiencys.add(skillProficiency);
    }

    public void updateSkillProficiency() {
        int index = skillProficiencyUI.inputSkillProficiencyIndex();
        if (index > 0 && index <= skillProficiencys.size()) {
            String name = skillProficiencyUI.inputSkillProficiencyName();
            int importance = skillProficiencyUI.inputSkillProficiencyImportance();
            SkillProficiency skillProficiency = new SkillProficiency(name, importance);
            skillProficiencys.set(index - 1, skillProficiency);
        } else {
            MessageUI.displayInvalidChoiceMessage();
        }
    }

    public String getAllSkillProficiencys() {
        String allSkillProficiencys = "";
        for (int i = 0; i < skillProficiencys.size(); i++) {
            allSkillProficiencys += "\t\t" + (i + 1) + ". " + skillProficiencys.get(i).toString();
        }
        return allSkillProficiencys;
    }

    public void displaySkillProficiency() {
        skillProficiencyUI.listAllSkillProficiencys(getAllSkillProficiencys());
    }

    public void deleteSkillProficiency() {
        int index = skillProficiencyUI.inputSkillProficiencyIndex();
        if (index > 0 && index <= skillProficiencys.size()) {
            skillProficiencys.remove(index - 1);
        } else {
            MessageUI.displayInvalidChoiceMessage();
        }
    }

    // public static void main(String[] args) {
    //     SkillProficiencyManager skillProficiencyManager = new SkillProficiencyManager();
    //     skillProficiencyManager.runSkillProficiency();
    // }

}
