package entity;

public class Match {
    private Student student;
    private JobPosting job;
    private double score;
    
    public Match(Student student, JobPosting job, double score) {
        this.student = student;
        this.job = job;
        this.score = score;
    }
    // Getters
}
