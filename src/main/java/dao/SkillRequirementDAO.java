package dao;

import entity.SkillRequirement;
import java.io.*;
import java.util.ArrayList;

public class SkillRequirementDAO {
    private final String fileName = "skills.txt";

    // Save list of skill requirements to file
    public void saveSkillsToFile(ArrayList<SkillRequirement> skills) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (SkillRequirement skill : skills) {
                writer.println(skill.getSkillName() + "," + skill.getImportance());
            }
        } catch (IOException e) {
            System.out.println("Error saving skills: " + e.getMessage());
        }
    }

    // Retrieve list of skill requirements from file
    public ArrayList<SkillRequirement> loadSkillsFromFile() {
        ArrayList<SkillRequirement> skills = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String skillName = parts[0];
                    int importance = Integer.parseInt(parts[1]);
                    skills.add(new SkillRequirement(skillName, importance));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading skills: " + e.getMessage());
        }
        return skills;
    }
}
