package control;

import adt.ListInterface;
import adt.ArrayList;
import entity.Company;
import utility.SearchUtil;
import entity.JobPosting;

public class CompanyManager {
    private ListInterface<Company> companys = new ArrayList<>();

    public void registerCompany(Company company) {
        companys.add(company);
    }

    public void registerCompany(String name, String location, ListInterface<JobPosting> jobPostings) {
        Company company = new Company(name, location, jobPostings);
        companys.add(company);
    }

    public boolean removeCompanyById(String id) {
        for (int i = 0; i < companys.size(); i++) {
            Company company = companys.get(i);
            if (company != null && id.equals(company.getId())) { // Safe null check
                companys.remove(i + 1);
                return true;
            }
        }
        return false;
    }

    public ListInterface<Company> getCompanies() {
        ListInterface<Company> companiesCopy = new ArrayList<>();
        for (int i = 0; i < companys.size(); i++) {
            Company company = companys.get(i);
            companiesCopy.add(new Company(company));
        }
        return companiesCopy;

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

    public void filterCompaniesByName(String name) {
        ListInterface<Company> filteredCompanies = new ArrayList<>();

        // Iterate over all companies and check if the company name contains the input
        // name
        for (int i = 0; i < companys.size(); i++) {
            Company company = companys.get(i);
            if (company.getName().toLowerCase().contains(name.toLowerCase())) {
                filteredCompanies.add(company);
            }
        }
        displayFilteredCompanies(filteredCompanies, "name", name);

    }

    // Method to display filtered companies
    private void displayFilteredCompanies(ListInterface<Company> companies, String type, String keyword) {
        if (companies.isEmpty()) {
            System.out.printf("No companies found with the %s \"%s\".\n", type, keyword);
        } else {
            System.out.printf("Companies with %s \"%s\":\n", type, keyword);
            companies.forEach(System.out::println);
        }
    }

    // Method to filter companies by location
    public void filterCompaniesByLocation(String location) {
        ListInterface<Company> filteredCompanies = new ArrayList<>();

        // Iterate over all companies and check if the company location contains the
        // input location
        for (int i = 0; i < companys.size(); i++) {
            Company company = companys.get(i);
            if (company.getLocation().toLowerCase().contains(location.toLowerCase())) {
                filteredCompanies.add(company);
            }
        }

        displayFilteredCompanies(filteredCompanies, "location", location);
    }

    public void searchCompanyByName(String query, int threshold) {
        ListInterface<Company> matchedCompanies = new ArrayList<>();
        for (int i = 0; i < companys.size(); i++) {
            Company company = companys.get(i);
            for (String word : company.getName().toLowerCase().split("\\s+")) {
                if (SearchUtil.fuzzySearch(query, word, threshold)) {
                    matchedCompanies.add(company);
                    break;
                }
            }
        }

        if (matchedCompanies.isEmpty()) {
            System.out.println("No companies matched your query.");
        } else {
            System.out.println("\n-- Matched Companies --");
            matchedCompanies.forEach(System.out::println);
        }
    }
}
