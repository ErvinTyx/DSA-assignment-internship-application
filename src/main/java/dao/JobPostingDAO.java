package dao;

import entity.JobPosting;
import entity.SkillRequirement;
import adt.ArrayList;
import adt.ListInterface;

import java.io.*;
import java.util.Scanner;

public class JobPostingDAO {
    private static final String FILE_NAME = "job_postings.txt";

    public void saveJobPostingsToFile(ListInterface<JobPosting> jobPostings) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (int i = 0; i < jobPostings.size(); i++) {
                JobPosting job = jobPostings.get(i);
                writer.println(job.getTitle() + ";" +
                        job.getDescription() + ";" +
                        job.getLocation() + ";" +
                        job.getSalaryRange()[0] + ";" +
                        job.getSalaryRange()[1] + ";" +
                        job.getExperienceRequired());

                ListInterface<SkillRequirement> skills = job.getRequiredSkills();
                for (int j = 0; j < skills.size(); j++) {
                    SkillRequirement skill = skills.get(j);
                    writer.println(skill.getSkillName() + "," + skill.getImportance());
                }
                writer.println("#"); // delimiter to separate jobs
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ListInterface<JobPosting> loadJobPostingsFromFile() {
        ListInterface<JobPosting> jobPostings = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(FILE_NAME))) {
            while (scanner.hasNextLine()) {
                String jobLine = scanner.nextLine();
                String[] jobData = jobLine.split(";");
                String title = jobData[0];
                String description = jobData[1];
                String location = jobData[2];
                double[] salaryRange = {Double.parseDouble(jobData[3]), Double.parseDouble(jobData[4])};
                int experienceRequired = Integer.parseInt(jobData[5]);

                ListInterface<SkillRequirement> skills = new ArrayList<>();
                while (scanner.hasNextLine()) {
                    String skillLine = scanner.nextLine();
                    if (skillLine.equals("#")) break;
                    String[] skillData = skillLine.split(",");
                    skills.add(new SkillRequirement(skillData[0], Integer.parseInt(skillData[1])));
                }

                JobPosting job = new JobPosting(title, description, skills, location, salaryRange, experienceRequired);
                jobPostings.add(job);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jobPostings;
    }
} 