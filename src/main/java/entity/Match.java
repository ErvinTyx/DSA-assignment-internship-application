package entity;
import java.io.Serializable;
public class Match implements Serializable {
    private Student student;
    private JobPosting job;
    private double score;

    public Match(Student student, JobPosting job, double score) {
        this.student = student;
        this.job = job;
        this.score = score;
    }

    public Match(Match match) {
        this.student = match.getStudent();
        this.job = match.getJob();
        this.score = match.getScore();
    }   

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public JobPosting getJob() {
        return job;
    }

    public void setJob(JobPosting job) {
        this.job = job;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public JobPosting getJobPosting() {
        return job;
    }

    @Override
    public String toString() {
        return "\n=========================\n"+"Match\n" +"=========================\n"+
                "=======Student=====\n" + student +
                "=======Job========\n"+ job +"=======Score======\n"+ score;
    }
}
