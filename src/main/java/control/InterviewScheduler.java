package control;

import adt.ArrayList;
import entity.Match;
public abstract class InterviewScheduler {
    
    public abstract void scheduleInterviews(ArrayList<Match> matches);

    public abstract void filterApplicantProfiles();
}
