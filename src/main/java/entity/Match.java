package entity;

public class Match implements Comparable<Match> {
    private Student student;
    private JobPosting job;
    private double score;

    public Match(Student student, JobPosting job, double score) {
        this.student = student;
        this.job = job;
        this.score = score;
    }

    @Override
    public int compareTo(Match other) {
        // Sort in descending order of score
        return Double.compare(other.score, this.score);
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
}
