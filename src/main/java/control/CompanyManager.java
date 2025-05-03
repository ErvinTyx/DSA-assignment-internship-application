package control;

import adt.*;
import boundary.CompanyUI;
import entity.JobPosting;
import utility.MessageUI;
import entity.Company;
import control.JobManager;
import dao.CompanyDAO;

public class CompanyManager {

    private ListInterface<Company> companyList = new ArrayList<>();
    private JobManager jobManager = new JobManager(new ArrayList<>());
    private CompanyUI companyUI = new CompanyUI();
    private CompanyDAO companyDAO = new CompanyDAO();
    private InterviewSchedulerManager interviewSchedulerManager = new InterviewSchedulerManager(null);
    private int index = -1;

    public CompanyManager(){
        companyList = companyDAO.retrieveFromFile();
    }

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
        companyDAO.saveToFile(companyList);
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
                        interviewSchedulerManager.runInterviewScheduler(companyList.get(index).getJobPostings());
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

    // FIXED METHOD: Changed to public and removed the third parameter to match what MatchingEngine expects
    public ListInterface<JobPosting> searchJobs(String jobTitle, int weighting) {
        ListInterface<JobPosting> result = new ArrayList<>();
        for (int i = 0; i < companyList.size(); i++) {
           
            // Get job postings for this company
            ListInterface<JobPosting> companyJobs = companyList.get(i).getJobPostings();
            
            // Check if jobManager is properly initialized
            if (jobManager == null) {
                System.out.println("ERROR: jobManager is null");
                continue;
            }
            
            // Search for matching jobs in this company
            ListInterface<JobPosting> foundJob = jobManager.searchJobs(jobTitle, weighting, companyJobs);
            
            // Add all found jobs to the result list
            for (int j = 0; j < foundJob.size(); j++) {
                result.add(foundJob.get(j));
            }
        }
        
        System.out.println("Total matching jobs found across all companies: " + result.size());
        return result;
    }

    protected String displayCompanyInfo() {
        String result = "";
        for (int i = 0; i < companyList.size(); i++) {
            result += displayCompanyInfo(companyList.get(i));
        }
        return result;
    }
}
