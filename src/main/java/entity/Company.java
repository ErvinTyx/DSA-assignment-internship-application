package entity;

import adt.ArrayList;

public class Company {
    private String id;
    private String name;
    private String location;
    private ArrayList<JobPosting> jobPostings = new ArrayList<>();

    public Company(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addJobPosting(JobPosting job) {
        jobPostings.add(job);
    }
}
