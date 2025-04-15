package control;

import adt.ArrayList;
import adt.ListInterface;
import entity.JobPosting;
import entity.Match;
import entity.SkillProficiency;
import entity.SkillRequirement;
import entity.Student;
import utility.SearchUtil;

public class MatchingEngine {
    private final ListInterface<Match> matches = new ArrayList<>();

    public ArrayList<Match> calculateMatches(ArrayList<Student> students, ArrayList<JobPosting> jobs) {
        matches.clear(); // Clear previous matches

        // Calculate all matches
        for (Student student : students) {
            for (JobPosting job : jobs) {
                double score = calculateMatchScore(student, job);
                if (score > 0.4) { // Threshold
                    matches.add(new Match(student, job, score));
                }
            }
        }

        // Convert to array and sort using SearchUtil.mergeSort
        Match[] matchArray = new Match[matches.size()];
        for (int i = 0; i < matches.size(); i++) {
            matchArray[i] = matches.get(i);
        }

        SearchUtil.mergeSort(matchArray);

        // Update the ArrayList with sorted results
        matches.clear();
        for (Match match : matchArray) {
            matches.add(match);
        }

        return matches;
    }

    private double calculateMatchScore(Student student, JobPosting job) {
        double skillScore = calculateSkillMatch(student, job);
        double locationScore = student.getLocation().equalsIgnoreCase(job.getCompany().getLocation()) ? 1 : 0;
        double experienceScore = Math.min(1.0, student.getExperience() / (double) job.getExperienceRequired());

        return (skillScore * 0.6) + (locationScore * 0.2) + (experienceScore * 0.2);
    }

    private double calculateSkillMatch(Student student, JobPosting job) {
        double total = 0;
        for (SkillRequirement req : job.getRequiredSkills()) {
            for (SkillProficiency prof : student.getSkills()) {
                if (req.getSkillName().equalsIgnoreCase(prof.getSkillName())) {
                    total += (prof.getProficiency() * req.getImportance());
                }
            }
        }
        return total / 100.0; // Normalize to 0-1
    }
}