package fr.halfaoui.jira.model.ws;

/**
 * @author amirouche
 */
public class IssueWs {

    private String id;
    private String key;
    private IssueFieldsWs fields;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public IssueFieldsWs getFields() {
        return fields;
    }

    public void setFields(IssueFieldsWs fields) {
        this.fields = fields;
    }
}
