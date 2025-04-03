package control;

import adt.ArrayList;
import entity.Company;
public class CompanyManager {
    private ArrayList<Company> companys = new ArrayList<>();
    
    public void registerCompany(Company company) {
        companys.add(company);
    }

    public boolean removeCompanyById(Company company) {
        for (int i = 0; i < companys.size(); i++) {
            if (companys.get(i).getId().equals(company.getId())) {
                companys.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean updateCompanyById(Company company) {
        for (int i = 0; i < companys.size(); i++) {
            if (companys.get(i).getId().equals(company.getId())) {
                companys.set(i, company);
                return true;
            }
        }
        return false;
    }

    
}
