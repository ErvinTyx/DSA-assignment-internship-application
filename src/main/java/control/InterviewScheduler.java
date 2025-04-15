package control;

import adt.ListInterface;
import entity.Match;

public abstract class InterviewScheduler {

    public abstract void scheduleInterviews(ListInterface<Match> matches);

    public abstract void filterApplicantProfiles();
}
