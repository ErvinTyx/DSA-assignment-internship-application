package boundary;

import control.JobManager;
import entity.JobPosting;
import adt.ListInterface;
import java.util.InputMismatchException;
import java.util.Scanner;

public class JobPostingUI {
    private Scanner sc = new Scanner(System.in);

    private void jobPostingMenu() {
        System.out.println();
        System.out.println();
        System.out.println("---------------------");
        System.out.println("Job Posting Menu");
        System.out.println("---------------------");
        System.out.println("1. Add Job Posting");
        System.out.println("2. View Job Postings");
        System.out.println("3. Update Job Posting");
        System.out.println("4. Delete Job Posting");
        System.out.println("5. Return To Company Menu");
    }

    public int getMenuChoice() {
        jobPostingMenu();
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

    public String inputJobPostingTitle() {
        System.out.print("Enter Job Posting Title: ");
        String jobPostingTitle = sc.nextLine();
        return jobPostingTitle;
    }

    public String inputJobPostingDescription() {
        System.out.print("Enter Job Posting Description: ");
        String jobPostingDescription = sc.nextLine();
        return jobPostingDescription;
    }

    public int inputJobPostingExperienceRequired() {
        int jobPostingExperienceRequired = 0;
        boolean flag = true;
        while (flag) {
            try {
                System.out.print("Enter Job Posting Experience Years Required: ");
                jobPostingExperienceRequired = sc.nextInt();
                if (jobPostingExperienceRequired < 0) {
                    System.out.println("Please enter a non-negative number.");
                } else {
                    flag = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                sc.next(); // clear the invalid input
            }
        }
        sc.nextLine();
        return jobPostingExperienceRequired;
    }

    public String inputJobPostingLocation() {
        System.out.print("Enter Job Posting Location: ");
        String jobPostingLocation = sc.nextLine();
        return jobPostingLocation;
    }

    public double[] inputJobPostingSalary() {
        double jobPostingSalary = 0;
        double jobPostingMaxSalary = 0;
        boolean flag = true;
        while (flag) {
            try {
                System.out.print("Enter Job Posting minimum salary: $");
                jobPostingSalary = sc.nextDouble();
                System.out.print("Enter Job Posting maximum salary: $");
                jobPostingMaxSalary = sc.nextDouble();
                if (jobPostingMaxSalary < jobPostingSalary) {
                    System.out.println("Please enter a valid number. Minimum salary must be more than maximum salary.");
                } else {
                    flag = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                sc.next(); // clear the invalid input
            }
        }
        sc.nextLine();// clear buffer
        return new double[] { jobPostingSalary, jobPostingMaxSalary };
    }

    public int inputJobPostingExperienceImportance() {
        int jobPostingExperienceRequired = 0;
        boolean flag = true;
        while (flag) {
            try {
                System.out.print("Enter Job Posting Experience Importance Score weight: ");
                jobPostingExperienceRequired = sc.nextInt();
                if (jobPostingExperienceRequired < 1 || jobPostingExperienceRequired > 10) {
                    System.out.println("Please enter a valid number between 1 and 10.");
                } else {
                    flag = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                sc.next(); // clear the invalid input
            }
        }
        sc.nextLine();
        return jobPostingExperienceRequired;
    }

    public int inputJobPostingLocationImportance() {
        int jobPostingLocationImportance = 0;
        boolean flag = true;
        while (flag) {
            try {
                System.out.print("Enter Job Posting Location Importance: ");
                jobPostingLocationImportance = sc.nextInt();
                if (jobPostingLocationImportance < 1 || jobPostingLocationImportance > 10) {
                    System.out.println("Please enter a valid number between 1 and 10.");
                } else {
                    flag = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                sc.next(); // clear the invalid input
            }
        }
        sc.nextLine();
        return jobPostingLocationImportance;
    }

    public int inputJobPostingSkillImportance() {
        int jobPostingSkillImportance = 0;
        boolean flag = true;
        while (flag) {
            try {
                System.out.print("Enter Job Posting Skill Importance: ");
                jobPostingSkillImportance = sc.nextInt();
                if (jobPostingSkillImportance < 1 || jobPostingSkillImportance > 10) {
                    System.out.println("Please enter a valid number between 1 and 10.");
                } else {
                    flag = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                sc.next(); // clear the invalid input
            }
        }
        sc.nextLine();
        return jobPostingSkillImportance;
    }

    public void listAllJobPosting(String allJobPosting) {
        System.out.println("\t---------------------");
        System.out.println("\tJob Posting List");
        System.out.println("\t---------------------");
        System.out.println(
                "\t+----+----------+----------------------+------------------+---------------+---------------------+--------+--------+--------+----------+\n"
                        +
                        "\t| ID | Title    | Description          | Location         | Exp. Required | Salary Range        | ExpImp | LocImp | SkillImp| Skills   |\n"
                        +
                        "\t+----+----------+----------------------+------------------+---------------+---------------------+--------+--------+---------+----------+\n");
        System.out.println(allJobPosting);
    }

    public int inputJobPostingIndex() {
        int jobPostingIndex = 0;
        boolean flag = true;
        while (flag) {
            try {
                System.out.print("Enter Job Posting number based on the aligned table: ");
                jobPostingIndex = sc.nextInt();
                if (jobPostingIndex < 1) {
                    System.out.println("Please enter a valid number.");
                } else {
                    flag = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                sc.next(); // clear the invalid input
            }
        }
        sc.nextLine();
        return jobPostingIndex;
    }

}
