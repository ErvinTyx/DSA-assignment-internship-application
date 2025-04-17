package dao;

import adt.ArrayList;
import adt.ListInterface;
import entity.SkillProficiency;

import java.io.*;

public class SkillProficiencyDAO {

    public static void saveProficienciesToFile(String filename, ListInterface<SkillProficiency> list) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int i = 0; i < list.size(); i++) {
                SkillProficiency skill = list.get(i);
                writer.write(skill.getSkillName() + "," + skill.getProficiency());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving skill proficiencies: " + e.getMessage());
        }
    }

    public static ListInterface<SkillProficiency> loadProficienciesFromFile(String filename) {
        ListInterface<SkillProficiency> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String skillName = parts[0].trim();
                    int proficiency = Integer.parseInt(parts[1].trim());
                    list.add(new SkillProficiency(skillName, proficiency));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading skill proficiencies: " + e.getMessage());
        }
        return list;
    }
}
