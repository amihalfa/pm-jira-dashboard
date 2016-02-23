package fr.halfaoui.jira.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author amirouche
 */
public class Issue implements Serializable {

    private String key;
    private long complexity;
    private List<Issue> children;
    private IssueStatus status;
    private Date resolutionDate;
    private Assignee assignee;
    private String title;

    public Issue(){
        children = new ArrayList<>();
    }

    public List<Issue> getChildren() {
        return children;
    }

    public long getComplexity() {
        return complexity;
    }

    public void setComplexity(long complexity) {
        this.complexity = complexity;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setStatus(IssueStatus status) {
        this.status = status;
    }

    public boolean isDone() {
        return IssueStatus.DONE.equals(status);
    }

    public boolean isInProgress() {
        return IssueStatus.IN_PROGRESS.equals(status);
    }

    public boolean isOpen() {
        return IssueStatus.OPEN.equals(status);
    }

    public Date getResolutionDate() {
        return resolutionDate;
    }

    public void setResolutionDate(Date resolutionDate) {
        this.resolutionDate = resolutionDate;
    }

    public IssueStatus getStatus() {
        return status;
    }

    public Assignee getAssignee() {
        return assignee;
    }

    public void setAssignee(Assignee assignee) {
        this.assignee = assignee;
    }

    public boolean isTask() {
        return children.isEmpty();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
