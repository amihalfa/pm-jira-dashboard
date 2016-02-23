package fr.halfaoui.jira.model.ws;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * @author amirouche
 */
public class IssueFieldsWs {

    @JsonProperty("customfield_10272")
    private Long complexity;

    @JsonProperty("customfield_10890")
    private Long subComplexity;

    private ResolutionWs resolution;

    private IssueWs parent;

    private AssigneeWs assignee;

    private Date resolutiondate;

    private StatusWs status;

    private String summary;

    public Long getComplexity() {
        return complexity;
    }

    public void setComplexity(Long complexity) {
        this.complexity = complexity;
    }

    public Long getSubComplexity() {
        return subComplexity;
    }

    public void setSubComplexity(Long subComplexity) {
        this.subComplexity = subComplexity;
    }

    public IssueWs getParent() {
        return parent;
    }

    public void setParent(IssueWs parent) {
        this.parent = parent;
    }

    public ResolutionWs getResolution() {
        return resolution;
    }

    public void setResolution(ResolutionWs resolution) {
        this.resolution = resolution;
    }

    public AssigneeWs getAssignee() {
        return assignee;
    }

    public void setAssignee(AssigneeWs assignee) {
        this.assignee = assignee;
    }

    public Date getResolutiondate() {
        return resolutiondate;
    }

    public void setResolutiondate(Date resolutiondate) {
        this.resolutiondate = resolutiondate;
    }

    public StatusWs getStatus() {
        return status;
    }

    public void setStatus(StatusWs status) {
        this.status = status;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
