package dao;

import adt.ArrayList;
import adt.ListInterface;
import entity.SkillProficiency;

public class SkillProficiencyInitializer {

    // Developer proficiencies
    public static ListInterface<SkillProficiency> getDeveloperProficiencies() {
        ListInterface<SkillProficiency> proficiencies = new ArrayList<>();
        proficiencies.add(new SkillProficiency("Java", 5));
        proficiencies.add(new SkillProficiency("Spring Boot", 4));
        proficiencies.add(new SkillProficiency("SQL", 3));
        proficiencies.add(new SkillProficiency("JavaScript", 4));
        proficiencies.add(new SkillProficiency("React", 3));
        return proficiencies;
    }

    // QA (Quality Assurance) proficiencies
    public static ListInterface<SkillProficiency> getQAProficiencies() {
        ListInterface<SkillProficiency> proficiencies = new ArrayList<>();
        proficiencies.add(new SkillProficiency("Selenium", 4));
        proficiencies.add(new SkillProficiency("JUnit", 3));
        proficiencies.add(new SkillProficiency("Manual Testing", 5));
        proficiencies.add(new SkillProficiency("Test Automation", 4));
        proficiencies.add(new SkillProficiency("API Testing", 4));
        return proficiencies;
    }

    // UX (User Experience) proficiencies
    public static ListInterface<SkillProficiency> getUXProficiencies() {
        ListInterface<SkillProficiency> proficiencies = new ArrayList<>();
        proficiencies.add(new SkillProficiency("Figma", 4));
        proficiencies.add(new SkillProficiency("User Research", 3));
        proficiencies.add(new SkillProficiency("Prototyping", 4));
        proficiencies.add(new SkillProficiency("Wireframing", 3));
        proficiencies.add(new SkillProficiency("Usability Testing", 3));
        return proficiencies;
    }

    // PM (Project Management) proficiencies
    public static ListInterface<SkillProficiency> getPMProficiencies() {
        ListInterface<SkillProficiency> proficiencies = new ArrayList<>();
        proficiencies.add(new SkillProficiency("Agile", 5));
        proficiencies.add(new SkillProficiency("Scrum", 4));
        proficiencies.add(new SkillProficiency("Communication", 5));
        proficiencies.add(new SkillProficiency("Risk Management", 4));
        proficiencies.add(new SkillProficiency("Stakeholder Management", 3));
        return proficiencies;
    }

    // Backend Developer proficiencies
    public static ListInterface<SkillProficiency> getBackendDeveloperProficiencies() {
        ListInterface<SkillProficiency> proficiencies = new ArrayList<>();
        proficiencies.add(new SkillProficiency("Java", 5));
        proficiencies.add(new SkillProficiency("Spring Boot", 4));
        proficiencies.add(new SkillProficiency("SQL", 4));
        proficiencies.add(new SkillProficiency("Node.js", 3));
        proficiencies.add(new SkillProficiency("REST APIs", 4));
        return proficiencies;
    }

    // QA Engineer proficiencies
    public static ListInterface<SkillProficiency> getQAEngineerProficiencies() {
        ListInterface<SkillProficiency> proficiencies = new ArrayList<>();
        proficiencies.add(new SkillProficiency("Selenium", 5));
        proficiencies.add(new SkillProficiency("JUnit", 4));
        proficiencies.add(new SkillProficiency("API Testing", 5));
        proficiencies.add(new SkillProficiency("Automation", 5));
        proficiencies.add(new SkillProficiency("Load Testing", 4));
        return proficiencies;
    }

    // Product Manager proficiencies
    public static ListInterface<SkillProficiency> getProductManagerProficiencies() {
        ListInterface<SkillProficiency> proficiencies = new ArrayList<>();
        proficiencies.add(new SkillProficiency("Agile", 5));
        proficiencies.add(new SkillProficiency("Scrum", 4));
        proficiencies.add(new SkillProficiency("Communication", 5));
        proficiencies.add(new SkillProficiency("Product Roadmap", 5));
        proficiencies.add(new SkillProficiency("Market Research", 4));
        return proficiencies;
    }

    // DevOps Engineer proficiencies
    public static ListInterface<SkillProficiency> getDevOpsEngineerProficiencies() {
        ListInterface<SkillProficiency> proficiencies = new ArrayList<>();
        proficiencies.add(new SkillProficiency("Docker", 5));
        proficiencies.add(new SkillProficiency("Kubernetes", 4));
        proficiencies.add(new SkillProficiency("CI/CD", 5));
        proficiencies.add(new SkillProficiency("Terraform", 4));
        proficiencies.add(new SkillProficiency("Linux", 4));
        return proficiencies;
    }

    // Data Scientist proficiencies
    public static ListInterface<SkillProficiency> getDataScientistProficiencies() {
        ListInterface<SkillProficiency> proficiencies = new ArrayList<>();
        proficiencies.add(new SkillProficiency("Python", 5));
        proficiencies.add(new SkillProficiency("Machine Learning", 4));
        proficiencies.add(new SkillProficiency("R", 3));
        proficiencies.add(new SkillProficiency("Data Visualization", 4));
        proficiencies.add(new SkillProficiency("SQL", 4));
        return proficiencies;
    }

    // Network Engineer proficiencies
    public static ListInterface<SkillProficiency> getNetworkEngineerProficiencies() {
        ListInterface<SkillProficiency> proficiencies = new ArrayList<>();
        proficiencies.add(new SkillProficiency("TCP/IP", 5));
        proficiencies.add(new SkillProficiency("Routing", 4));
        proficiencies.add(new SkillProficiency("Switching", 4));
        proficiencies.add(new SkillProficiency("Firewall Configuration", 5));
        proficiencies.add(new SkillProficiency("VPN", 4));
        return proficiencies;
    }

    // Business Analyst proficiencies
    public static ListInterface<SkillProficiency> getBusinessAnalystProficiencies() {
        ListInterface<SkillProficiency> proficiencies = new ArrayList<>();
        proficiencies.add(new SkillProficiency("Requirements Gathering", 5));
        proficiencies.add(new SkillProficiency("Data Analysis", 4));
        proficiencies.add(new SkillProficiency("SQL", 4));
        proficiencies.add(new SkillProficiency("Business Process Modeling", 5));
        proficiencies.add(new SkillProficiency("Excel", 5));
        return proficiencies;
    }

    // Cloud Engineer proficiencies
    public static ListInterface<SkillProficiency> getCloudEngineerProficiencies() {
        ListInterface<SkillProficiency> proficiencies = new ArrayList<>();
        proficiencies.add(new SkillProficiency("AWS", 5));
        proficiencies.add(new SkillProficiency("Azure", 4));
        proficiencies.add(new SkillProficiency("Google Cloud", 4));
        proficiencies.add(new SkillProficiency("Cloud Security", 5));
        proficiencies.add(new SkillProficiency("Terraform", 4));
        return proficiencies;
    }

    // Security Engineer proficiencies
    public static ListInterface<SkillProficiency> getSecurityEngineerProficiencies() {
        ListInterface<SkillProficiency> proficiencies = new ArrayList<>();
        proficiencies.add(new SkillProficiency("Network Security", 5));
        proficiencies.add(new SkillProficiency("Firewall Management", 5));
        proficiencies.add(new SkillProficiency("Penetration Testing", 4));
        proficiencies.add(new SkillProficiency("Cryptography", 4));
        proficiencies.add(new SkillProficiency("Incident Response", 5));
        return proficiencies;
    }

   
    // Mobile Developer proficiencies
    public static ListInterface<SkillProficiency> getMobileDeveloperProficiencies() {
        ListInterface<SkillProficiency> proficiencies = new ArrayList<>();
        proficiencies.add(new SkillProficiency("Swift", 5));
        proficiencies.add(new SkillProficiency("Kotlin", 4));
        proficiencies.add(new SkillProficiency("React Native", 3));
        return proficiencies;
    }

}
