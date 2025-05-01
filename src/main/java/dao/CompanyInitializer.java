package dao;

import adt.*;
import entity.Company;

public class CompanyInitializer {
    public ListInterface<Company> initializeCompanyList() {
        ListInterface<Company> companies = new ArrayList<>();
        JobPostingInitializer jobPostingsinitializer = new JobPostingInitializer();
        
        companies.add(new Company("Innovix Technologies", "Kuala Lumpur", jobPostingsinitializer.initializeJobPosting()));
        companies.add(new Company("PixelWave Creations", "Penang", jobPostingsinitializer.initializeJobPosting()));
        companies.add(new Company("DataSync Analytics", "Remote", jobPostingsinitializer.initializeJobPosting()));
        companies.add(new Company("SoftPro Studio", "Johor Bahru",jobPostingsinitializer.initializeJobPosting() ));
        companies.add(new Company("TestLab Solutions", "Penang", jobPostingsinitializer.initializeJobPosting()));
        companies.add(new Company("ProdigyWorks", "Kuala Lumpur", jobPostingsinitializer.initializeJobPosting()));
        companies.add(new Company("DevStream Global", "Cyberjaya", jobPostingsinitializer.initializeJobPosting()));
        companies.add(new Company("InsightAI", "Remote", jobPostingsinitializer.initializeJobPosting())); // Sharing data-related jobs
        companies.add(new Company("SecureNet Systems", "Cyberjaya", jobPostingsinitializer.initializeJobPosting()));
        companies.add(new Company("BizFlow Consulting", "Selangor", jobPostingsinitializer.initializeJobPosting()));

        return companies;
    }

    public static void main(String[] args) {
        CompanyInitializer companyInitializer = new CompanyInitializer();
        ListInterface<Company> companies = companyInitializer.initializeCompanyList();
        CompanyDAO companyDAO = new CompanyDAO();
        companyDAO.saveToFile(companies);
    }
}
