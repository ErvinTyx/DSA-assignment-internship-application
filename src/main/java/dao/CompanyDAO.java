package dao;

import adt.ArrayList;
import adt.ListInterface;
import control.CompanyManager;
import entity.Company;
import java.io.*;

public class CompanyDAO {

    private static final String FILE_NAME = "company.txt";

    // Save all companies to file
    public static void saveCompanies(CompanyManager companyManager) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            ListInterface<Company> companies = companyManager.getCompanies();

            for (int i = 0; i < companies.size(); i++) {
                Company c = companies.get(i);
                writer.println(c.getId() + ";" + c.getName() + ";" + c.getLocation());
                // You could also write jobPosting count or IDs here
            }
        } catch (IOException e) {
            System.err.println("Error saving companies: " + e.getMessage());
        }
    }

    // Load companies from file
    public static void loadCompanies(CompanyManager companyManager) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue; // skip empty lines
            
                String[] parts = line.split(";");
                if (parts.length >= 3) {
                    String id = parts[0].trim();
                    String name = parts[1].trim();
                    String location = parts[2].trim();
            
                    Company company = new Company(name, location, new ArrayList<>());
                    company.setId(id); // explicitly set the ID if needed
                    companyManager.registerCompany(company);
                } else {
                    System.err.println("Invalid line in company.txt: " + line);
                }
            }
            
        } catch (FileNotFoundException e) {
            System.out.println("company.txt not found, skipping load.");
        } catch (IOException e) {
            System.err.println("Error loading companies: " + e.getMessage());
        }
    }
}
