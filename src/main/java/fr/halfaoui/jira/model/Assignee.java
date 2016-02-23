package fr.halfaoui.jira.model;

import fr.halfaoui.jira.technical.util.StringUtil;

import java.io.Serializable;

/**
 * @author amirouche
 */
public class Assignee implements Serializable {

    private String emailAddress;
    private String displayName;
    private String trigram;


    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        updateTrigram();
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
        updateTrigram();
    }

    private void updateTrigram() {
        String trigramName = StringUtil.getTrigram(displayName, ' ');
        String trigramEmail = StringUtil.getTrigram(emailAddress, '.');
        if (trigramName != null && trigramName.length() == 3) {
            trigram = trigramName;
        } else if (trigramEmail != null && trigramEmail.length() == 3) {
            trigram = trigramEmail;
        }
    }

    public String getTrigram() {
        return trigram;
    }
}
