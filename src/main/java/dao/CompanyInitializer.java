package dao;

import adt.ArrayList;
import adt.ListInterface;
import control.CompanyManager;
import control.JobManager;
import entity.JobPosting;

public class CompanyInitializer {

    public static void initialize(CompanyManager companyManager) {
        // Initialize job postings first
        JobManager jobManager = new JobManager();
        JobPostingInitializer.initialize(jobManager);
        ListInterface<JobPosting> allPostings = jobManager.getJobPostings();

        // Create job posting lists for each company
        ListInterface<JobPosting> techCompanyPostings = new ArrayList<>();
        ListInterface<JobPosting> creativeCompanyPostings = new ArrayList<>();
        ListInterface<JobPosting> financeCompanyPostings = new ArrayList<>();
        ListInterface<JobPosting> supportCompanyPostings = new ArrayList<>();
        ListInterface<JobPosting> marketingCompanyPostings = new ArrayList<>();
        ListInterface<JobPosting> cloudCompanyPostings = new ArrayList<>();
        ListInterface<JobPosting> securityCompanyPostings = new ArrayList<>();
        ListInterface<JobPosting> digitalCompanyPostings = new ArrayList<>();
        ListInterface<JobPosting> dataScienceCompanyPostings = new ArrayList<>();
        ListInterface<JobPosting> remoteCompanyPostings = new ArrayList<>();

        // Simple logic to assign jobs to different companies
        for (int i = 0; i < allPostings.size(); i++) {
            JobPosting posting = allPostings.get(i);
            String title = posting.getTitle().toLowerCase();

            if (title.contains("developer") || title.contains("backend") || title.contains("full stack")) {
                techCompanyPostings.add(posting);
            } else if (title.contains("designer") || title.contains("ux") || title.contains("product manager")) {
                creativeCompanyPostings.add(posting);
            } else if (title.contains("analyst") || title.contains("scientist")) {
                financeCompanyPostings.add(posting);
            } else if (title.contains("support") || title.contains("it")) {
                supportCompanyPostings.add(posting);
            } else if (title.contains("marketing")) {
                marketingCompanyPostings.add(posting);
            } else if (title.contains("cloud")) {
                cloudCompanyPostings.add(posting);
            } else if (title.contains("security")) {
                securityCompanyPostings.add(posting);
            } else if (title.contains("digital")) {
                digitalCompanyPostings.add(posting);
            } else if (title.contains("data") || title.contains("machine learning")) {
                dataScienceCompanyPostings.add(posting);
            } else if (title.contains("remote")) {
                remoteCompanyPostings.add(posting);
            }
        }

        // Register companies with job postings
        companyManager.registerCompany("Tech Innovators Sdn Bhd", "Kuala Lumpur", techCompanyPostings);
        companyManager.registerCompany("Creative Minds Studio", "Penang", creativeCompanyPostings);
        companyManager.registerCompany("Finlytics Global", "Remote", financeCompanyPostings);
        companyManager.registerCompany("Support Experts Sdn Bhd", "Kuala Lumpur", supportCompanyPostings);
        companyManager.registerCompany("Creative Digital Solutions", "Penang", marketingCompanyPostings);
        companyManager.registerCompany("CloudX Technologies", "Cyberjaya", cloudCompanyPostings);
        companyManager.registerCompany("SecureNet Solutions", "Kuala Lumpur", securityCompanyPostings);
        companyManager.registerCompany("Digital Surge Co.", "Johor Bahru", digitalCompanyPostings);
        companyManager.registerCompany("DataX Labs", "Remote", dataScienceCompanyPostings);
        companyManager.registerCompany("Future Tech Ventures", "Selangor", remoteCompanyPostings);
    }
}
