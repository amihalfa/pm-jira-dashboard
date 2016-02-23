package fr.halfaoui.jira.model.ws;

import java.util.List;

/**
 * @author amirouche
 */
public class IssuesResultWs {

    private List<IssueWs> issues;

    public List<IssueWs> getIssues() {
        return issues;
    }

    public void setIssues(List<IssueWs> issues) {
        this.issues = issues;
    }
}
