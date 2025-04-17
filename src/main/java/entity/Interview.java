package entity;

import adt.ListInterface;
import entity.Match;

public class Interview {

    // Interview DateTime
    private int Idate;
    private int Imonth;
    private int Iyear;
    private int Ihour;
    private int Imin;
    private String id;
    private static int COUNTER = 0;

    // list of the Specific Job match
    private ListInterface<Match> matches;

    // state = pending / rejectedByCompany / sheduled / acceptedBy application /
    // reject application
    private int[] state;

    public void setDate(int Idate, int Imonth, int Iyear, int Ihour, int Imin) {
        this.Idate = Idate;
        this.Imonth = Imonth;
        this.Iyear = Iyear;
        this.Ihour = Ihour;
        this.Imin = Imin;
    }
    public void setSAllState(int state) {
        for (int i = 0; i < this.state.length; i++) {
            this.state[i] = state;
        }
    }

    public void setState(int index, int state) {
        this.state[index] = state;
    }

    // constructor
    public Interview(ListInterface<Match> matches) {
        COUNTER++;
        this.id = "I" + COUNTER;
        // initialize state
        state = new int[matches.size()];
        this.matches = matches;
    }

    public Interview(ListInterface<Match> matches, int Idate, int Imonth, int Iyear, int Ihour, int Imin) {
        COUNTER++;
        this.id = "I" + COUNTER;
        // initialize state
        state = new int[matches.size()];
        this.matches = matches;
    }

    // setters
        

    // getters
    public int getIdate() {
        return Idate;
    }

    public ListInterface<Match> getMatches() {
        return matches;
    }

    public int getImonth() {
        return Imonth;
    }

    public int getIyear() {
        return Iyear;
    }

    public int getIhour() {
        return Ihour;
    }

    public int getImin() {
        return Imin;
    }

    public String getId() {
        return id;
    }

    public int[] getState() {
        return state;
    }

    @Override
    public String toString() {
        return "Total Applicant : " + matches.size() + "\n" + "\nInterview ID : " + id + "\nInterview Date : " + Idate
                + "/" + Imonth + "/" + Iyear + " at " + Ihour + ":" + Imin + "\nMatches : " + getDisplayMatches();
    }

    private String getDisplayMatches() {
        String result = "";
        for (int i = 0; i < matches.size(); i++) {
            result += "\n" + (i + 1) + ". " + matches.get(i).toString() + " State : " + getDisplayState(i);
        }

        return result;
    }

    private String getDisplayState(int i) {
        if(state[i] == 0){
            return "Pending";
        }else if(state[i] == 1){
            return "Rejected by company";
        }else if(state[i] == 2){
            return "Scheduled";
        }else if(state[i] == 3){
            return "Accepted by applicant";
        }else{
            return "Rejected by applicant";
        }
    }
}
