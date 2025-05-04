package entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import adt.ListInterface;
import entity.Match;

public class Interview implements Serializable{

    // Interview DateTime
    private LocalDateTime scheduledTime;
    private String id;
    private static int COUNTER = 0;

    // list of the Specific Job match
    private ListInterface<Match> matches;

    // state = pending / rejectedByCompany / sheduled / acceptedBy application //
    // reject applicationByStudent
    private int[] state;

    public String getId() {
        return id;
    }

    public LocalDateTime getScheduledTime() {
        return scheduledTime;
    }

    public ListInterface<Match> getMatches() {
        return matches;
    }

    public int[] getState() {
        int[] newState = new int[state.length];
        System.arraycopy(state, 0, newState, 0, state.length);
        return newState;
    }

    public Interview(LocalDateTime scheduledTime, ListInterface<Match> matches, int[] state) {
        COUNTER++;
        this.id = "I" + COUNTER;
        this.scheduledTime = scheduledTime;
        this.matches = matches;
        this.state = state;
    }

    public Interview(Interview interview) {
        this.id = interview.getId();
        this.scheduledTime = interview.getScheduledTime();
        this.matches = interview.getMatches();
        this.state = interview.getState();
    }

    @Override
    public String toString() {
        return "======Interview=====\n" + "id :" + id + "\n scheduledTime :" + scheduledTime
                + "Jobs :\n" + matches.get(0).getJobPosting().toString()
                + "\n Interview Applicants : \n" + "=====================\n" + getMatchingDetails();
    }

    private String getMatchingDetails() {
        String result = "";
        for (int i = 0; i < matches.size(); i++) {
            result += matches.get(i).getStudent().toString() + "\n";
            result += "State" + getDisplayState(state[i]) + "\n";
            result += "Match Score : " + matches.get(i).getScore() + "\n";
            result += "=============================================\n";

        }
        return result;
    }

    public String getDisplayState(int i) {
        if (i == 0) {
            return "Pending";
        } else if (i == 1) {
            return "Rejected by company";
        } else if (i == 2) {
            return "Scheduled";
        } else if (i == 3) {
            return "Accepted by applicant";
        } else {
            return "Rejected by applicant";
        }
    }

}
