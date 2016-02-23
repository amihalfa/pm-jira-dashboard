package fr.halfaoui.jira.model;

import java.io.Serializable;

/**
 * @author amirouche
 */
public class Stats implements Serializable {

    private Long totalPoints;
    private Long donePoints;

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
}
