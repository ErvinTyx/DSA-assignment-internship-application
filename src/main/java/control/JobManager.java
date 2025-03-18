package control;

import adt.ArrayList;
import entity.JobPosting;
public class JobManager {
    private ArrayList<JobPosting> jobs = new ArrayList<>();
    //private CustomMap<String, JobPosting> jobMap = new CustomMap<>();

    public void createJob(JobPosting job) {
        jobs.add(job);
    }

    //Remove job
    public boolean removeJob(){

        return false;
    }

    // Update Jobs

    
    // filtering jobs
    // location, company, job type, salary range

}
