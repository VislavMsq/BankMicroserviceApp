package bankmicroservicesapp.controller.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Valid {
    public static boolean isValidUUID(String id) {
        Pattern pattern = Pattern.compile("[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}");
        Matcher matcher = pattern.matcher(id);
        if (matcher.find()) {
            return matcher.group().equals(id);
        }
        return false;
    }
}
