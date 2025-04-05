package control;

import adt.ArrayList;

import entity.JobPosting;
import entity.Match;
import entity.SkillProficiency;
import entity.SkillRequirement;
import entity.Student;

import utility.SearchUtil;

public class MatchingEngine {
    private final ArrayList<Match> matches = new ArrayList<>();

    public ArrayList<Match> calculateMatches(ArrayList<Student> students, ArrayList<JobPosting> jobs) {
        for(Student student : students) {
            for(JobPosting job : jobs) {
                double score = calculateMatchScore(student, job);
                if(score > 0.4) { // Threshold
                    matches.add(new Match(student, job, score));
                }
            }
        }
        SearchUtil.mergeSort((a,b) -> Double.compare(b.getScore(), a.getScore()),0, matches.size()-1);
        return matches;
    }

    private double calculateMatchScore(Student student, JobPosting job) {
        double skillScore = calculateSkillMatch(student, job);
        double locationScore = student.getLocation().equals(job.getCompany().getLocation()) ? 1 : 0;
        double experienceScore = Math.min(1.0, student.getExperience() / (double) job.getExperienceRequired());
        
        return (skillScore * 0.6) + (locationScore * 0.2) + (experienceScore * 0.2);
    }

    private double calculateSkillMatch(Student student, JobPosting job) {
        double total = 0;
        for(SkillRequirement req : job.getRequiredSkills()) {
            for(SkillProficiency prof : student.getSkills()) {
                if(req.getSkillName().equals(prof.getSkillName())) {
                    total += (prof.getProficiency() * req.getImportance());
                }
            }
        }
        return total / 100.0; // Normalize to 0-1
    }
    
}
