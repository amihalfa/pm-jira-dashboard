package fr.halfaoui.jira.service;

import fr.halfaoui.jira.model.*;
import fr.halfaoui.jira.model.ws.*;
import fr.halfaoui.jira.technical.PropertiesHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * @author amirouche
 */
@Service
public class SprintService {

    public static final int IN_PROGRESS_STATUS = 3;
    private static final String SPRINT_URL_PROPERTY = "jira.sprint.url";
    private static final String ISSUES_URL_PROPERTY = "jira.issues.url";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PropertiesHandler properties;

    public Sprint getActiveSprint(long boardId) {
        SprintResultWs result = restTemplate.getForObject(properties.getString(SPRINT_URL_PROPERTY, boardId), SprintResultWs.class);
        if(result.getValues().size() > 0) {
            SprintWs sprintws = result.getValues().get(0);
            Sprint sprint = new Sprint();
            sprint.setId(sprintws.getId());
            sprint.setName(sprintws.getName());
            sprint.setStartDate(sprintws.getStartDate());
            sprint.setEndDate(sprintws.getEndDate());
            sprint.setIssues(getIssues(boardId, sprintws.getId()));
            return sprint;
        }
        return null;
    }

    public List<Issue> getIssues(long boardId, long sprintId) {
        IssuesResultWs issuesWs = restTemplate.getForObject(properties.getString(ISSUES_URL_PROPERTY, boardId, sprintId), IssuesResultWs.class);

        Map<String, Issue> issues = new HashMap<>();
        List<IssueWs> subIssues = new ArrayList<>();

        for(IssueWs issueWs : issuesWs.getIssues()) {
            IssueWs parent = issueWs.getFields().getParent();
            if(parent == null) {
                issues.put(issueWs.getKey(), convertFromWs(issueWs));
            } else {
                subIssues.add(issueWs);
            }
        }
        for(IssueWs issueWs : subIssues) {
            IssueWs parentWs = issueWs.getFields().getParent();
            Issue parent = issues.get(parentWs.getKey());
            if (parent != null) {
                parent.getChildren().add(convertFromWs(issueWs));
            }
        }
        return new ArrayList<>(issues.values());
    }



    public static Issue convertFromWs(IssueWs issueWs) {
        Issue issue = new Issue();
        issue.setKey(issueWs.getKey());
        if(issueWs.getFields().getComplexity() != null) {
            issue.setComplexity(issueWs.getFields().getComplexity());
        } else if(issueWs.getFields().getSubComplexity() != null) {
            issue.setComplexity(issueWs.getFields().getSubComplexity());
        } else {
            issue.setComplexity(0L);
        }
        issue.setResolutionDate(issueWs.getFields().getResolutiondate());
        if (issue.getResolutionDate() != null) {
            issue.setStatus(IssueStatus.DONE);
        } else if(issueWs.getFields().getStatus().getId() == IN_PROGRESS_STATUS) {
            issue.setStatus(IssueStatus.IN_PROGRESS);
        } else {
            issue.setStatus(IssueStatus.OPEN);
        }
        issue.setTitle(issueWs.getFields().getSummary());
        issue.setAssignee(convertFromWs(issueWs.getFields().getAssignee()));
        return issue;
    }

    public static Assignee convertFromWs(AssigneeWs assigneeWs) {
        Assignee assignee = new Assignee();
        assignee.setEmailAddress(assigneeWs.getEmailAddress());
        assignee.setDisplayName(assigneeWs.getDisplayName());
        return assignee;
    }
}
