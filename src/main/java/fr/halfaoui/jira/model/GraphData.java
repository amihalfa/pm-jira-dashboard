package fr.halfaoui.jira.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import fr.halfaoui.jira.technical.serializer.DateJsonSerializer;
import fr.halfaoui.jira.technical.serializer.DatePointMapSerializer;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author amirouche
 */
public class GraphData implements Serializable {
    private List<Date> availableDays;
    private Map<Date, Long> remainingPoints;
    private long maximum;

    @JsonSerialize(contentUsing = DateJsonSerializer.class)
    public List<Date> getAvailableDays() {
        return availableDays;
    }

    public void setAvailableDays(List<Date> availableDays) {
        this.availableDays = availableDays;
    }

    @JsonSerialize(using = DatePointMapSerializer.class)
    public Map<Date, Long> getRemainingPoints() {
        return remainingPoints;
    }

    public void setRemainingPoints(Map<Date, Long> remainingPoints) {
        this.remainingPoints = remainingPoints;
    }

    public long getMaximum() {
        return maximum;
    }

    public void setMaximum(long maximum) {
        this.maximum = maximum;
    }
}
