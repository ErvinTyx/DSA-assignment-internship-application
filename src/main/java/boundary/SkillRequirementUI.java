package boundary;

import java.util.InputMismatchException;
import java.util.Scanner;
import entity.SkillRequirement;

public class SkillRequirementUI {
    private Scanner sc = new Scanner(System.in);

    private void skillRequirementMenu() {
        System.out.println();
        System.out.println();
        System.out.println("---------------------");
        System.out.println("Skill Requirement Menu");
        System.out.println("---------------------");
        System.out.println("1. Add Skill Requirement");
        System.out.println("2. View Skill Requirements");
        System.out.println("3. Update Skill Requirement");
        System.out.println("4. Delete Skill Requirement");
        System.out.println("5. Return To Job Posting Menu");
    }

    public int getMenuChoice(){
        skillRequirementMenu();
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

    public SkillRequirement addSkillRequirement() {
        System.out.print("Enter Skill Requirement Name:");
        String skillRequirementName = sc.nextLine();
        int skillRequirementImportance =0;
        boolean flag = true;
        while (flag) {
            try {
                System.out.print("Enter Skill Requirement Importance:");
                skillRequirementImportance = sc.nextInt();
                if (skillRequirementImportance > 0 && skillRequirementImportance <= 10) {
                    flag = false;
                } else {
                    System.out.println("Please enter a number between 0 and 10.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                sc.next(); // clear the invalid input
            }
        }
        return new SkillRequirement(skillRequirementName, skillRequirementImportance);
    }

    public int inputSkillRequirementImportance() {
        int skillRequirementImportance =0;
        boolean flag = true;
        while (flag) {
            try {
                System.out.print("Enter Skill Requirement Importance:");
                skillRequirementImportance = sc.nextInt();
                if (skillRequirementImportance > 0 && skillRequirementImportance <= 10) {
                    flag = false;
                } else {
                    System.out.println("Please enter a number between 0 and 10.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                sc.next(); // clear the invalid input
            }
        }
        return skillRequirementImportance;
    }

    public String inputSkillRequirementName() {
        System.out.print("Enter Skill Requirement Name:");
        String skillRequirementName = sc.nextLine();
        return skillRequirementName;
    }

    public int inputSkillRequirementIndex(){
        int index = 0;
        boolean flag = true;
        while (flag) {
            try {
                System.out.print("Enter the number of skill requirement to update:");
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
    
    public void listAllSkillRequirements(String allSkillRequirements){
        System.out.println("\t\t---------------------");
        System.out.println("\t\tSkill Requirement List");
        System.out.println("\t\t---------------------");
        System.out.println(allSkillRequirements);
    }



    
}