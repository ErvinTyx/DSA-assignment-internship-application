package control;

import adt.ListInterface;

import java.util.List;
import java.util.Scanner;
import adt.ArrayList;
import entity.Company;
import entity.Interview;
import entity.JobPosting;
import entity.Match;
import entity.Student;

public class InterviewSchedulerManager {

    private ListInterface<Interview> interviews = new ArrayList<>();

    private ListInterface<Interview> interviewsResult = new ArrayList<>();

    // @dev : this just loop through the matches and add them to the interviews
    public void scheduleInterview(Company company, MatchingEngine matchingEngines) {
        ListInterface<JobPosting> jobPostings = company.getJobPostings();
        ListInterface<Match> matches = matchingEngines.getMatches();
        for (int i = 0; i < jobPostings.size(); i++) {
            ListInterface<Match> matchesForCompany = new ArrayList<>();
            for (int j = 0; j < matches.size(); j++) {
                // look for match that the company is his one
                if (jobPostings.get(i).getId() == matches.get(j).getJob().getId()) {
                    matchesForCompany.add(matches.get(j));
                }
                // remove from the match manager
                matches.remove(j);

                // add to the interview manager
                interviews.add(new Interview(matchesForCompany));
            }
        }
    }

    // @dev : this loop through all interview that belong to the company and assign
    // to result interview
    public void displayAllInterviews(Company company) {
        if (interviewsResult.isEmpty()) {
            ListInterface<JobPosting> jobPostings = company.getJobPostings();
            for (int i = 0; i < company.getJobPostings().size(); i++) {
                JobPosting job = jobPostings.get(i);
                if (interviews.get(i).getMatches().get(0).getJob().getId() == job.getId()) {
                    interviewsResult.add(interviews.get(i));
                }
            }

            for (int i = 0; i < interviewsResult.size(); i++) {
                System.out.println("Interview " + (i + 1) + ": " + interviewsResult.get(i).getId());
                System.out.println("Date: " + interviewsResult.get(i).getIdate() + "/"
                        + interviewsResult.get(i).getImonth() + "/" + interviewsResult.get(i).getIyear());
                System.out.println(
                        "Time: " + interviewsResult.get(i).getIhour() + ":" + interviewsResult.get(i).getImin());
                System.out.println("State: " + interviewsResult.get(i).getState());
            }
        }

    }

    public boolean interviewAllScheduled() {
        for (int i = 0; i < interviewsResult.size(); i++) {
            if (interviewsResult.get(i).getState()[0] != 0) {
                return true;
            }
        }
        return false;
    }

    public void setAllInterviewsInformation() {
        Scanner input = new Scanner(System.in);
        for (int i = 0; i < interviewsResult.size(); i++) {
            if (interviewsResult.get(i).getState()[0] == 0) {
                System.out.println("Interview " + (i + 1) + ": " + interviewsResult.get(i).getId());
                // print out for which job
                System.out.println(interviewsResult.get(i).getMatches().get(0).getJob().getTitle());
                // input date
                System.out.print("Enter Date: ");
                int date = input.nextInt();
                System.out.print("Enter Month: ");
                int month = input.nextInt();
                System.out.print("Enter Year: ");
                int year = input.nextInt();
                // input time
                System.out.println("Enter Time in 24 hours format");
                System.out.print("Enter Hour: ");
                int hour = input.nextInt();
                System.out.print("Enter Minute: ");
                int min = input.nextInt();

                // set date
                interviewsResult.get(i).setDate(date, month, year, hour, min);

                // set state
                for (int j = 0; j < interviewsResult.get(i).getState().length; j++) {
                    // print out each student
                    System.out.println(interviewsResult.get(j).getMatches().get(j));
                    // accept or reject
                    System.out.println("Enter 1 for accept and 0 for reject");
                    System.out.print("Enter state: ");
                    int state = input.nextInt();

                    // set state
                    interviewsResult.get(i).setState(j, state + 1);

                }
                input.close();
            }
        }
    }

    public void displayAllInterviews(Student student) {
        if (interviewsResult.isEmpty()) {
            for (int i = 0; i < interviews.size(); i++) {
                for (int j = 0; j < interviews.get(i).getMatches().size(); j++) {
                    if (interviews.get(i).getMatches().get(j).getStudent().getId() == student.getId()) {

                        interviewsResult.add(interviews.get(i));

                    }
                }
            }
        }

        for (int i = 0; i < interviewsResult.size(); i++) {
            System.out.println("\n");
            System.out.println("Interview " + (i + 1) + ": " + interviewsResult.get(i).getId());
            System.out.println("Date: " + interviewsResult.get(i).getIdate() + "/"
                    + interviewsResult.get(i).getImonth() + "/" + interviewsResult.get(i).getIyear());
            System.out.println(
                    "Time: " + interviewsResult.get(i).getIhour() + ":" + interviewsResult.get(i).getImin());
            System.out.println("Job: " + interviewsResult.get(i).getMatches().get(0).getJob().getTitle());
            for (int j = 0; j < interviewsResult.get(i).getMatches().size(); j++) {
                if (interviewsResult.get(i).getMatches().get(j).getStudent().getId() == student.getId()) {
                    System.out.println("State: " + getDisplayState(interviewsResult.get(i).getState()[j]));
                }
                
            }
        }
    }
    private String getDisplayState(int i) {
        if(i == 0){
            return "Pending";
        }else if(i == 1){
            return "Rejected by company";
        }else if(i == 2){
            return "Scheduled";
        }else if(i == 3){
            return "Accepted by applicant";
        }else{
            return "Rejected by applicant";
        }
    }

    public void setInterviewResultState(String InterviewId, String studentId, int state) {
        for (int i = 0; i < interviewsResult.size(); i++) {
            if (interviewsResult.get(i).getId().equals(InterviewId)) {
                for (int j = 0; j < interviewsResult.get(i).getMatches().size(); j++) {
                    Match match = interviewsResult.get(i).getMatches().get(j);
                    if (match.getStudent().getId().equals(studentId) && interviewsResult.get(i).getState()[j] == 2) {
                        interviewsResult.get(i).setState(j, state);
                    }
                }
            }
        }
    }

    public boolean getInterviewsResult(String id) {
        boolean found = false;
        for (int i = 0; i < interviewsResult.size(); i++) {
            if (interviewsResult.get(i).getId().equals(id)) {
                System.out.println(interviewsResult.get(i));
                found = true;
            }
        }
        if (!found) {
            System.out.println("No interview found");
        }
        return found;
    }

    public void clearInterviewsRelated() {
        interviewsResult.clear();
    }

    public boolean resultsInterviewIsEmpty() {
        return interviewsResult.isEmpty();
    }
}