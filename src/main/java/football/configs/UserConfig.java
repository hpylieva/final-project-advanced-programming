package football.configs;

import lombok.Getter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.*;

@Component
public class UserConfig implements Serializable {
    @Getter
    private List<String> columnNames;

    @Getter
    private Map<String, String> codes;

    @Getter
    private Map<String,String> teams = new HashMap<>();

    /* I am not a big football expert, sut I considered as actions which involve only one player
     *  the next: yellow/red card, kick to rod/gate, free kick*/
    @Getter
    private List<String> singlePlayerCodes = Arrays.asList("1","2","5","6","10");

    @Value("${columnNames}")
    private void setColumnNames(String[] columnNames) {
        // to get column names from property files
        this.columnNames = Arrays.asList(columnNames);
    }

    @PostConstruct
    public void initialize() {
        this.codes = readProperties("codes.properties");

        Map<String, String> teamsProp = readProperties("teams.properties");
        for(Object key : teamsProp.keySet()){
            String[] players =  teamsProp.get(key).split(",");
            for (String player: players) {
                teams.put( player,(String) key);
            }
        }

    }

    @SneakyThrows
    private Map<String, String> readProperties(String propertiesFileName) {
        Properties properties = new Properties();
        properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(propertiesFileName));

        HashMap<String, String> propertiesMap = new HashMap<>();
        Enumeration e = properties.propertyNames();

        while (e.hasMoreElements()) {
            String key = (String) e.nextElement();
            propertiesMap.put(key, properties.getProperty(key));
        }

        return propertiesMap;
    }

}
