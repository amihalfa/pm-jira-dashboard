package fr.halfaoui.jira.model;

import java.io.Serializable;

/**
 * @author amirouche
 */
public class Stats implements Serializable {

    private Long totalPoints;
    private Long donePoints;
    private Long totalTasks;
    private Long doneTasks;

    public Long getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(Long totalPoints) {
        this.totalPoints = totalPoints;
    }

    public Long getDonePoints() {
        return donePoints;
    }

    public void setDonePoints(Long donePoints) {
        this.donePoints = donePoints;
    }

    public double getPercentage(){
        return (double) donePoints / totalPoints;
    }

    public Long getTotalTasks() {
        return totalTasks;
    }

    public void setTotalTasks(Long totalTasks) {
        this.totalTasks = totalTasks;
    }

    public Long getDoneTasks() {
        return doneTasks;
    }

    public void setDoneTasks(Long doneTasks) {
        this.doneTasks = doneTasks;
    }
}
