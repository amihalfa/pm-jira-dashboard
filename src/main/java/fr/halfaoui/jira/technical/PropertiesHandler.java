package fr.halfaoui.jira.technical;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.Properties;

/**
 * @author amirouche
 */
@Component
public class PropertiesHandler {

    @Autowired
    private Properties properties;

    public String getString(String property){
        return properties.getProperty(property);
    }

    public String getString(String property, Object... args){
        return MessageFormat.format(getString(property), args);
    }

}
