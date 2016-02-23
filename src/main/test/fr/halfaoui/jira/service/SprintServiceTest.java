package fr.halfaoui.jira.service;

import fr.halfaoui.jira.model.Assignee;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * @author amirouche
 */
public class SprintServiceTest {

    @Test
    public void testTrigram() {
        Assignee assignee = new Assignee();
       // assignee.setDisplayName("Amirouh Halfaoui");
        assignee.setEmailAddress("amirouche_halfaoui@priceminister.com");

        System.out.print(assignee.getTrigram());
    }

}