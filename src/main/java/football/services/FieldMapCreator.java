package football.services;


import java.util.HashMap;
import java.util.Map;

public class FieldMapCreator {

    public static Map<String, String> getMap(String line){
        Map<String, String> map = new HashMap<>();
        String[] pairs = line.split(";");
        for (String pair : pairs) {
            String[] keyValues = pair.split("=");
            if (keyValues.length > 1 && !keyValues[1].equals("-1")) {
                map.put(keyValues[0], keyValues[1]);
            }else {
                map.put(keyValues[0], "");
            }
        }
        return map;
    }
}
