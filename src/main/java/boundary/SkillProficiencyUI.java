package boundary;

import java.util.InputMismatchException;
import java.util.Scanner;

import entity.SkillProficiency;

public class SkillProficiencyUI {
    private Scanner sc = new Scanner(System.in);

    private void skillProficiencyMenu() {
        System.out.println();
        System.out.println();
        System.out.println("---------------------");
        System.out.println("Skill Proficiency Menu");
        System.out.println("---------------------");
        System.out.println("1. Add Skill Proficiency");
        System.out.println("2. View Skill Proficiencys");
        System.out.println("3. Update Skill Proficiency");
        System.out.println("4. Delete Skill Proficiency");
        System.out.println("5. Return To Student Menu");
    }

    public int getMenuChoice() {
        skillProficiencyMenu();
        int choice = 0;
        boolean flag = true;
        while (flag) {
            try {
                System.out.print("Enter your choice: ");
                choice = sc.nextInt();
                if (choice < 1 || choice > 5) {
                    System.out.println("Please enter a valid choice between 1 and 5.");
                } else {
                    flag = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                sc.next(); // clear the invalid input
            }
        }
        sc.nextLine();
        return choice;
    }

    public SkillProficiency addSkillProficiency() {
        System.out.print("Enter Skill Proficiency Name:");
        String skillProficiencyName = sc.nextLine();
        int skillProficiencyImportance = 0;
        boolean flag = true;
        while (flag) {
            try {
                System.out.print("Enter Skill Proficiency:");
                skillProficiencyImportance = sc.nextInt();
                if (skillProficiencyImportance > 0 && skillProficiencyImportance <= 10) {
                    flag = false;
                } else {
                    System.out.println("Please enter a number between 0 and 10.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                sc.next(); // clear the invalid input
            }
        }
        return new SkillProficiency(skillProficiencyName, skillProficiencyImportance);
    }

    public int inputSkillProficiencyImportance() {
        int skillProficiencyImportance = 0;
        boolean flag = true;
        while (flag) {
            try {
                System.out.print("Enter Skill Proficiency :");
                skillProficiencyImportance = sc.nextInt();
                if (skillProficiencyImportance > 0 && skillProficiencyImportance <= 10) {
                    flag = false;
                } else {
                    System.out.println("Please enter a number between 0 and 10.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                sc.next(); // clear the invalid input
            }
        }
        return skillProficiencyImportance;
    }

    public String inputSkillProficiencyName() {
        System.out.print("Enter Skill Proficiency Name:");
        String skillProficiencyName = sc.nextLine();
        return skillProficiencyName;
    }

    public int inputSkillProficiencyIndex() {
        int index = 0;
        boolean flag = true;
        while (flag) {
            try {
                System.out.print("Enter the number of skill Proficiency to update:");
                index = sc.nextInt();
                if (index > 0) {
                    flag = false;
                } else {
                    System.out.println("Please enter a valid number.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                sc.next(); // clear the invalid input
            }
        }
        sc.nextLine();
        return index;
    }

    public void listAllSkillProficiencys(String allSkillProficiencys) {
        System.out.println("\t\t---------------------");
        System.out.println("\t\tSkill Proficiency List");
        System.out.println("\t\t---------------------");
        System.out.println(allSkillProficiencys);
    }

}
