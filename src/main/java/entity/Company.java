package entity;

import adt.ArrayList;

public class Company {
    private String id;
    private String name;
    private String location;
    private ArrayList<JobPosting> jobPostings = new ArrayList<>();
    private int COUNTER = 0;

    // Constructor
    public Company(String name, String location, ArrayList<JobPosting> jobPostings) {
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

    public ArrayList<JobPosting> getJobPostings() {
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

    public void setJobPostings(ArrayList<JobPosting> jobPostings) {
        this.jobPostings = jobPostings;
    }

    @Override
    public String toString() {
        return "{" + "id=" + id + ", name=" + name + ", location=" + location + "\njobPostings=" + getStringJobPosting() + "}";
    }

    private String getStringJobPosting() {
        String result = "";
        for (int i = 0; i < jobPostings.size(); i++) {
            result += jobPostings.get(i).toString() + "\n";
        }
        return result;
    }
}
