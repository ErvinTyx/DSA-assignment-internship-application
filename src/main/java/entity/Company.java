package entity;

import adt.ArrayList;
import adt.ListInterface;

public class Company {
    private String id;
    private String name;
    private String location;
    private ListInterface<JobPosting> jobPostings = new ArrayList<>();
    private static int COUNTER = 0;

    // Constructor
    public Company(String name, String location, ListInterface<JobPosting> jobPostings) {
        COUNTER++;
        this.id = "C" + COUNTER;
        this.name = name;
        this.location = location;
        this.jobPostings = jobPostings;
    }

    public Company(Company company) {
        this.id = company.getId();
        this.name = company.getName();
        this.location = company.getLocation();
        this.jobPostings = company.getJobPostings();
    }

    public Company(String name, String location) {
        COUNTER++;
        this.id = "C" + COUNTER;
        this.name = name;
        this.location = location;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public ListInterface<JobPosting> getJobPostings() {
        return jobPostings;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setJobPostings(ListInterface<JobPosting> jobPostings) {
        this.jobPostings = jobPostings;
    }

    @Override
    public String toString() {
        String result = String.format(
                "|%4s|%10s| %9s| %13s       |\n" +
                        "+----+----------+----------+-----------------------+\n",
                id, name, location, getStringJobPosting());
        return result;
    }

    private String getStringJobPosting() {
        if (jobPostings.isEmpty()) {
            return "No job posting.";
        }
        String result = "";
        result += "\n\t---------------------" +"\n\tJob Posting List" + "\n\t---------------------"+"\n\t+----+----------+----------------------+------------------+---------------+---------------------+--------+--------+--------+----------+\n"
                        +
                        "\t| ID | Title    | Description          | Location         | Exp. Required | Salary Range        | ExpImp | LocImp | SkillImp| Skills   |\n"
                        +
                        "\t+----+----------+----------------------+------------------+---------------+---------------------+--------+--------+---------+----------+\n";
        for (int i = 0; i < jobPostings.size(); i++) {
            result += "\t"+jobPostings.get(i).toString();
        }
        return result;
    }

}
