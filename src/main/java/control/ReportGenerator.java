package control;

import adt.ListInterface;
import entity.JobPosting;
import adt.ArrayList;
import entity.Company;
import entity.Student;
import static utility.SearchUtil.fuzzySearch;

public class ReportGenerator {
    private JobManager jobManager;
    private CompanyManager companyManager;
    private ApplicantManager applicantManager;

    public ReportGenerator(JobManager jobManager, CompanyManager companyManager, ApplicantManager applicantManager) {
        this.jobManager = jobManager;
        this.companyManager = companyManager;
        this.applicantManager = applicantManager;
    }

    // Filtered report
    public ListInterface<JobPosting> generateFilteredJobPostings(
            String id,
            String title,
            String skill,
            String location,
            Double minSalary,
            Integer minExperience
    ) {
        ListInterface<JobPosting> filteredJob = new ArrayList<>();
        ListInterface<JobPosting> allJobs = jobManager.getJobPostings();

        for (int i = 0; i < allJobs.size(); i++) {
            JobPosting job = allJobs.get(i);
            boolean matches = true;
            
            // ID filter
            if (id != null && !id.isEmpty() && !job.getId().equalsIgnoreCase(id)) {
                matches = false;
            }

            // Title filter
            if (title != null && !title.isEmpty() && !job.getTitle().toLowerCase().contains(title.toLowerCase())) {
                matches = false;
            }

            if (location != null && !location.isEmpty()) {
                String jobLoc = job.getLocation().toLowerCase();
                String inputLoc = location.toLowerCase();

                if (!jobLoc.contains(inputLoc) && !fuzzySearch(inputLoc, jobLoc, 2)) {
                    matches = false;
                }
            }

            // Experience filter
            if (minExperience != null && job.getExperienceRequired() < minExperience) {
                matches = false;
            }

            // Salary filter
            if (minSalary != null && job.getSalaryRange()[0] < minSalary) {
                matches = false;
            }

            // Skill filter
            if (skill != null && !skill.isEmpty()) {
                boolean hasSkill = false;
                for (int j = 0; j < job.getRequiredSkills().size(); j++) {
                    if (fuzzySearch(skill, job.getRequiredSkills().get(j).getSkillName(), 2)) {
                        hasSkill = true;
                        break;
                    }
                }
                if (!hasSkill){
                    matches = false;
                }
            }
            if (matches) {
                filteredJob.add(job);
            }
        }

        return filteredJob;
    }

    public ListInterface<JobPosting> generateJobPostingOverview() {
        return jobManager.getJobPostings();
    }
    
    public ListInterface<Company> generateCompanyOverview() {
        return companyManager.getCompanies();
    }
    
    // In ReportGenerator.java
    public ListInterface<Company> generateFilteredCompanies(
        String id, String name, String location, Integer minJobCount
    ) {
        ListInterface<Company> all = companyManager.getCompanies();
        ListInterface<Company> filteredCompany = new adt.ArrayList<>();

        for (int i = 0; i < all.size(); i++) {
            Company c = all.get(i);
            boolean matches = true;
            
            if (id != null && !id.isEmpty() && !c.getId().equalsIgnoreCase(id)) {
                matches = false;
            }

            if (name != null && !name.isEmpty()) {
                String companyName = c.getName().toLowerCase();
                String inputName = name.toLowerCase();
                if (!companyName.contains(inputName) && !fuzzySearch(inputName, companyName, 2)) {
                    matches = false;
                }
            }

            if (location != null && !location.isEmpty()) {
                String compLoc = c.getLocation().toLowerCase();
                String inputLoc = location.toLowerCase();
                if (!compLoc.contains(inputLoc) && !fuzzySearch(inputLoc, compLoc, 2)) {
                    matches = false;
                }
            }

            if (minJobCount != null && c.getJobPostings().size() < minJobCount) {
                matches = false;
            }

            if (matches) {
                filteredCompany.add(c);
            }
        }
        return filteredCompany;
    }

    
    public ListInterface<Company> filterCompaniesByMinJobPostings(int min) {
        ListInterface<Company> all = companyManager.getCompanies();
        ListInterface<Company> filtered = new ArrayList<>();
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getJobPostings().size() >= min) {
                filtered.add(all.get(i));
            }
        }
        return filtered;
    }
    
    public ListInterface<Student> generateApplicantsOverview() {
        return applicantManager.getApplicants();
    }
    
    public ListInterface<Student> generateFilteredApplicants(
        String id, String name, String skills, String location, Integer experience
    ) {
        ListInterface<Student> all = applicantManager.getApplicants();
        ListInterface<Student> filteredApplicant = new adt.ArrayList<>();

        for (int i = 0; i < all.size(); i++) {
            Student s = all.get(i);
            boolean matches = true;
            
            if (id != null && !id.isEmpty() && !s.getId().equalsIgnoreCase(id)) {
                matches = false;
            }

            if (name != null && !name.isEmpty()) {
                String applicantName = s.getName().toLowerCase();
                String inputName = name.toLowerCase();
                if (!applicantName.contains(inputName) && !fuzzySearch(inputName, applicantName, 2)) {
                    matches = false;
                }
            }

            if (skills != null && !skills.isEmpty()) {
                boolean hasSkill = false;
                for (int j = 0; j < s.getSkills().size(); j++) {
                    if (fuzzySearch(skills, s.getSkills().get(j).getSkillName(), 2)) {
                        hasSkill = true;
                        break;
                    }
                }
                if (!hasSkill){
                    matches = false;
                }
            }

            if (location != null && !location.isEmpty()) {
                String appLoc = s.getLocation().toLowerCase();
                String inputLoc = location.toLowerCase();
                if (!appLoc.contains(inputLoc) && !fuzzySearch(inputLoc, appLoc, 2)) {
                    matches = false;
                }
            }

            if (experience != null && s.getExperience() < experience) {
                matches = false;
            }

            if (matches) {
                filteredApplicant.add(s);
            }
        }
        return filteredApplicant;
    }

}
