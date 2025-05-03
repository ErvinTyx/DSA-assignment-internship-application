package control;

import java.time.LocalDateTime;
import java.util.Comparator;
import adt.*;
import boundary.InterviewUI;
import entity.Interview;
import entity.JobPosting;
import entity.Match;
import utility.MessageUI;
import utility.SearchUtil;
import dao.InterviewDAO;

public class InterviewSchedulerManager {
    private ListInterface<Interview> interviews = new ArrayList<>();
    private InterviewUI interviewUI = new InterviewUI();
    private InterviewDAO interviewDAO = new InterviewDAO();
    private MatchingEngine matchingEngine;

    public InterviewSchedulerManager(MatchingEngine matchingEngine) {
        interviews = interviewDAO.retrieveFromFile();
        this.matchingEngine = matchingEngine;
    }

    public void runInterviewScheduler(ListInterface<JobPosting> companyJobs) {
        ListInterface<Interview> companyInterviews = getInterviewsFromCompany(companyJobs);
        int choice = 0;

        do {
            choice = interviewUI.displayInterviewMenuOptions();
            if (choice == 1) {
                // view interviewers
                displayInterviews(listInterviews(companyInterviews));

            } else if (choice == 2) {
                // schedule interview for a job
                // select a job

                ListInterface<Interview> addedInterview = scheduleInterview(companyJobs);
                for (int i = 0; i < addedInterview.size(); i++) {
                    companyInterviews.add(addedInterview.get(i));
                }

                displayInterview(ListAllInterview(companyInterviews));
            } else if (choice == 3) {
                // return to company menu
                MessageUI.displayExitingMessageInterviewScheduler();
            }
        } while (choice != 3);
        interviewDAO.saveToFile(interviews);
    }

    public void runInterviewScheduler(String studentId) {
        ListInterface<Integer> interviewIndex = new ArrayList<>();
        ListInterface<Integer> matchIndex = new ArrayList<>();
        // getting specific information
        for (int i = 0; i < interviews.size(); i++) {
            for (int j = 0; j < interviews.get(i).getMatches().size(); j++) {
                if (interviews.get(i).getMatches().get(j).getStudent().getId().equals(studentId)) {
                    interviewIndex.add(i);
                    matchIndex.add(j);
                }
            }
        }
        int choice = 0;
        do {
            choice = interviewUI.displayInterviewMenuStudentOptions();
            if (choice == 1) {
                // view interviewers for student
                displayInterviews(ListAllInterview(interviewIndex, matchIndex));
            } else if (choice == 2) {
                // set interview status
                setInterviewStatus(interviewIndex, matchIndex);
            } else if (choice == 3) {
                // return to student menu
                MessageUI.displayExitingMessageInterviewScheduler();
            }
        } while (choice != 3);
        interviewDAO.saveToFile(interviews);
    }

    public void setInterviewStatus(ListInterface<Integer> interviewIndex, ListInterface<Integer> matchIndex) {
        int choice = 0;
        do {
            displayInterviews(ListAllInterview(interviewIndex, matchIndex));
            choice = interviewUI.inputSelectedInterview();
            if (choice != 0 && choice <= interviewIndex.size()) {
                Interview interview = interviews.get(choice);
                // display specific interview
                displayInterview(listInterview(interview ,matchIndex,choice));
                // check whether it is company accept
                if(interview.getState()[matchIndex.get(choice)] ==2){
                    interview.getState()[matchIndex.get(choice)] = interviewUI.acceptApplicant();
                }
                displayInterview(listInterview(interview ,matchIndex,choice));
            }
        } while (choice != 0);
    }

    public String listInterview(Interview interview, ListInterface<Integer> matchIndex,int choice) {
        String result = "";
        result += "\nInterviews\n";
        result += "=====================\n";
        result += "Interview Id:" + interview.getId() + "\n";
        result += "Interview Scheduled Time:" + interview.getScheduledTime() + "\n";
        result += "Status:" + interview.getDisplayState(interview.getState()[matchIndex.get(choice)]) + "\n";
        result += "\nMatch Details  :\n"
                + interview.getMatches().get(matchIndex.get(choice)).toString();
        result += "\n";
        return result;
    }

    public String ListAllInterview(ListInterface<Integer> interviewIndex, ListInterface<Integer> matchIndex) {

        String result = "";
        for (int i = 0; i < interviewIndex.size(); i++) {
            result += "\nInterviews" + (1 + i) + "\n";
            result += "=====================\n";
            Interview interview = interviews.get(interviewIndex.get(i));
            result += "Interview Id:" + interview.getId() + "\n";
            result += "Interview Scheduled Time:" + interview.getScheduledTime() + "\n";
            result += "Status:" + interview.getDisplayState(interview.getState()[matchIndex.get(i)]) + "\n";
            result += "\nMatch Details  :\n"
                    + interviews.get(interviewIndex.get(i)).getMatches().get(matchIndex.get(i)).toString();
            result += "\n";
        }
        return result;
    }

    public void displayInterviews(ListInterface<Interview> companyInterviews, ListInterface<Integer> interviewIndex,
            ListInterface<Integer> matchIndex) {
        String info = "";
        for (int i = 0; i < interviewIndex.size(); i++) {
            info += companyInterviews.get(interviewIndex.get(i)).getMatches().get(matchIndex.get(i)).toString();
        }
        displayInterview(info);
    }

    public void displayInterviews(String info) {
        interviewUI.displayInterview(info);
    }

    public String listInterviews(ListInterface<Interview> companyInterviews) {
        String info = "";
        for (int i = 0; i < companyInterviews.size(); i++) {
            info += companyInterviews.get(i).toString();
        }
        return info;
    }

    private ListInterface<Interview> scheduleInterview(ListInterface<JobPosting> companyJobs) {
        int choice = 0;
        ListInterface<Interview> interview = new ArrayList<>();
        do {
            // select a job
            choice = interviewUI.displayJobs(displayJobs(companyJobs));
            // get interview date and time
            LocalDateTime scheduledTime = interviewUI.getInterviewDateAndTime();

            // retrieve all applicants from a selected job
            ListInterface<Match> matches = getAllMatchesBySeletedJobPosting(companyJobs.get(choice));
            // TODO : implement sorting algorithm
            // sort high to low

            // accept applicants
            int[] array = new int[matches.size()];
            for (int i = 0; i < matches.size(); i++) {
                // print out applicant match information
                matchingEngine.displayMatches(matches.get(i).toString());
                int accept = interviewUI.acceptApplicant();
                array[i] = accept;
            }
            this.interviews.add(new Interview(scheduledTime, matches, array));
            interview.add(new Interview(scheduledTime, matches, array));
            // save interview
        } while (choice != 0);
        return interview;
    }

    private ListInterface<Match> getAllMatchesBySeletedJobPosting(JobPosting jobPosting) {
        ListInterface<Match> matches = matchingEngine.getMatchesBySeletedJob(jobPosting);
        return matches;
    }

    public String displayJobs(ListInterface<JobPosting> companyJobs) {
        String info = "";
        for (int i = 0; i < companyJobs.size(); i++) {
            info += (i + 1) + ". " + companyJobs.get(i).toString();
        }
        return info;
    }

    private void displayInterview(String info) {
        interviewUI.displayInterview(info);
    }

    private String ListAllInterview(ListInterface<Interview> interviews) {
        String info = "";
        for (int i = 0; i < interviews.size(); i++) {
            info += interviews.get(i).toString();
        }
        return info;
    }

    /**
     * This method takes a list of job postings and returns all the interviews for
     * these job postings.
     * 
     * @param companyJobs a list of job postings
     * @return a list of interviews for the given job postings
     */
    private ListInterface<Interview> getInterviewsFromCompany(ListInterface<JobPosting> companyJobs) {
        ListInterface<Interview> companyInterview = new ArrayList<>();
        for (int i = 0; i < interviews.size(); i++) {
            Interview interview = interviews.get(i);
            for (int j = 0; j < interview.getMatches().size(); j++) {
                Match match = interview.getMatches().get(j);
                if (companyJobs.contains(match.getJobPosting())) {
                    companyInterview.add(new Interview(interview));
                }
            }
        }

        return companyInterview;
    }

}