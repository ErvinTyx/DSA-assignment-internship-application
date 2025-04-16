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

    /**
     * Calculates match scores between all students and jobs, filters out low
     * matches,
     * sorts the results, and returns the sorted list.
     */
    public ListInterface<Match> calculateMatches(ListInterface<Student> students, ListInterface<JobPosting> jobs) {
        matches.clear();

        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            for (int j = 0; j < jobs.size(); j++) {
                JobPosting job = jobs.get(i);
                double score = calculateMatchScore(student, job);
                if (score > 0.4) { // Only add meaningful matches
                    matches.add(new Match(student, job, score));
                }
            }
        }

        // Convert to array and sort
        Match[] matchArray = listToArray(matches);
        SearchUtil.mergeSort(matchArray);

        // Rebuild the list in sorted order
        matches.clear();
        for (Match match : matchArray) {
            matches.add(match);
        }

        return (ListInterface<Match>) matches;
    }

    /**
     * Converts a ListInterface<Match> to a Match[] array.
     */
    private Match[] listToArray(ListInterface<Match> matchList) {
        Match[] array = new Match[matchList.size()];
        for (int i = 0; i < matchList.size(); i++) {
            array[i] = matchList.get(i);
        }
        return array;
    }

    /**
     * Computes overall match score based on skills, location, and experience.
     */
    private double calculateMatchScore(Student student, JobPosting job) {
        double skillScore = calculateSkillScore(student, job);
        double locationScore = student.getLocation().equalsIgnoreCase(job.getLocation()) ? 1.0 : 0.0;
        double experienceScore = Math.min(1.0, (double) student.getExperience() / job.getExperienceRequired());

        // Weighted average
        return (0.6 * skillScore) + (0.2 * locationScore) + (0.2 * experienceScore);
    }

    /**
     * Calculates how well a student's skills match a job's required skills.
     * Both proficiency and importance are considered.
     */
    private double calculateSkillScore(Student student, JobPosting job) {
        double scoreSum = 0;
        ListInterface<SkillRequirement> jobSkills = job.getRequiredSkills();
        ListInterface<SkillProficiency> studentSkills = student.getSkills();

        for (int i = 0; i < jobSkills.size(); i++) {
            SkillRequirement req = jobSkills.get(i);
            for (int j = 0; j < studentSkills.size(); j++) {
                SkillProficiency prof = studentSkills.get(j);
                if (req.getSkillName().equalsIgnoreCase(prof.getSkillName())) {
                    scoreSum += prof.getProficiency() * req.getImportance();
                }
            }
        }

        return scoreSum / 100.0; // Normalize score to range 0–1
    }
}
