package fr.halfaoui.jira.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author amirouche
 */
public class Sprint implements Serializable {
    private long id;
    private String name;
    private Date startDate;
    private Date endDate;
    private List<Issue> issues;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<Issue> getIssues() {
        return issues;
    }

    public void setIssues(List<Issue> issues) {
        this.issues = issues;
    }
}
