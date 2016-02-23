package fr.halfaoui.jira.technical.util;

/**
 * @author amirouche
 */
public class StringUtil {

    private StringUtil(){

    }

    public static String getTrigram(String name, char separator) {
        if (name == null) {
            return null;
        }
        if ("".equals(name.trim())) {
            return "";
        }
        if (name.contains("@")) {
            name = name.substring(0, name.indexOf('@'));
        }
        int spaceIndex = name.indexOf(separator);
        if (spaceIndex == -1) {
            return "";
        }
        String end = name.substring(spaceIndex + 1, Math.min(spaceIndex + 3, name.length()));
        return (name.charAt(0) + end).toUpperCase();
    }
}
