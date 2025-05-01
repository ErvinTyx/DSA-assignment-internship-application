package control;

import adt.*;
import boundary.CompanyUI;
import entity.JobPosting;
import utility.MessageUI;
import entity.Company;
import control.JobManager;

public class CompanyManager {

    private ListInterface<Company> companyList = new ArrayList<>();
    private JobManager jobManager = new JobManager(new ArrayList<>());
    private CompanyUI companyUI = new CompanyUI();
    private int index = -1;

    public void runCompanyProfile() {
        boolean flag = true;
        while (flag) {
            index = companyUI.getMenuChoiceLogin();
            switch (index) {
                case 1:
                    login();
                    break;
                case 2:
                    signUp();
                    break;
                case 3:
                    flag = false;
                    break;
            }
        }
    }

    public void login() {
        index = -1;
        String id = companyUI.inputCompanyId();
        for (int i = 0; i < companyList.size(); i++) {
            if (id.equals(companyList.get(i).getId())) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            int choice = 0;
            do {
                choice = companyUI.getMenuChoice();
                switch (choice) {
                    case 1:
                        companyUI.listCompanyInfo(displayCompanyInfo(companyList.get(index)));
                        break;
                    case 2:
                        // update
                        int updateChoice = companyUI.getUpdateMenuChoice();
                        switch (updateChoice) {
                            case 1:
                                String name = companyUI.inputCompanyName();
                                companyList.get(index).setName(name);
                                break;
                            case 2:
                                String location = companyUI.inputCompanyLocation();
                                companyList.get(index).setLocation(location);
                                break;
                            case 3:
                                break;
                            default:
                                MessageUI.displayInvalidChoiceMessage();
                        }
                        companyUI.listCompanyInfo(displayCompanyInfo(companyList.get(index)));
                        break;
                    case 4:
                        jobManager.runJobPosting(companyList.get(index).getJobPostings());
                        ListInterface<JobPosting> jobPostings = jobManager.getJobPostings();
                        companyList.get(index).setJobPostings(jobPostings);
                        companyUI.listCompanyInfo(displayCompanyInfo(companyList.get(index)));
                        break;
                    case 5:
                        // TODO : manage interviews
                        break;
                    case 3:
                        // delete company
                        String choiceDelete = companyUI.comfimationDelete();
                        if (choiceDelete.equals("y")) {
                            companyList.remove(index + 1);
                            MessageUI.displayDeleteCompanyAccountMessage();
                        } else {
                            break;
                        }
                        choice = 6;
                    case 6:
                        index = -1;
                        MessageUI.displayLogOutMessage();
                        // logout
                        break;
                    default:
                        MessageUI.displayInvalidChoiceMessage();
                }
            } while (choice != 6);
        }else{
            MessageUI.displayCompanyInfoNotFoundMessage();
        }
    }

    public void signUp() {
        String name = companyUI.inputCompanyName();
        String location = companyUI.inputCompanyLocation();
        Company company = new Company(name, location);
        companyList.add(company);
        companyUI.listCompanyInfo(displayCompanyInfo(company));
        companyUI.displayEnterToContinueMessage();
    }

    public String displayCompanyInfo(Company company) {
        String result = company.toString();
        return result;
    }

    public static void main(String[] args) {
        CompanyManager companyManager = new CompanyManager();
        companyManager.runCompanyProfile();
    }
}
