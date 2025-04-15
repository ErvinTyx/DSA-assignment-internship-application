package control;

import adt.ArrayList;
import entity.Company;
import entity.JobPosting;
public class CompanyManager {
    private ArrayList<Company> companys = new ArrayList<>();
    
    public void registerCompany(Company company) {
        companys.add(company);
    }

    public void registerCompany(String name, String location, ArrayList<JobPosting>jobPostings) {
        Company company = new Company(name, location, jobPostings);
        companys.add(company);
    }

    public boolean removeCompanyById(String id) {
        for (int i = 0; i < companys.size(); i++) {
            Company company = companys.get(i);
            if (company != null && id.equals(company.getId())) {  // Safe null check
                companys.remove(i+1);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Company> getCompanies() {
        return companys;
    }
    

    public Company getCompanyById(String id) {
        for (int i = 0; i < companys.size(); i++) {
            if (companys.get(i).getId().equals(id)) {
                return companys.get(i);
            }
        }
        return null;
    }

    public void listAllCompanies() {
        System.out.println("\n\nCompanies:");
        for (int i = 0; i < companys.size(); i++) {
            System.out.println(companys.get(i).toString());
        }
    }

    public ArrayList<Company> filterCompaniesByName(String name) {
        ArrayList<Company> filteredCompanies = new ArrayList<>();
        
        // Iterate over all companies and check if the company name contains the input name
        for (Company company : companys) {
            if (company.getName().toLowerCase().contains(name.toLowerCase())) {
                filteredCompanies.add(company);
            }
        }
        
        return filteredCompanies;
    }

    // Method to filter companies by location
    public ArrayList<Company> filterCompaniesByLocation(String location) {
        ArrayList<Company> filteredCompanies = new ArrayList<>();
        
        // Iterate over all companies and check if the company location contains the input location
        for (Company company : companys) {
            if (company.getLocation().toLowerCase().contains(location.toLowerCase())) {
                filteredCompanies.add(company);
            }
        }
        
        return filteredCompanies;
    }

}
