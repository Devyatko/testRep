package framework.baseutils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class ConfigManager {
    private static final String FILE_PATH = "src/test/java/project/data/";

    public static String readerStr(String dataName, String fileName) {
        String stringData = null;
        try {
            JSONParser parser = new JSONParser();
            Reader reader = new FileReader(FILE_PATH+fileName);
            JSONObject sampleObject = (JSONObject) parser.parse(reader);
            stringData = (String) sampleObject.get(dataName);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return stringData;
    }

    public static Long readerLong(String dataName, String fileName) {
        Long longData = null;
        try {
            JSONParser parser = new JSONParser();
            Reader reader = new FileReader(FILE_PATH+fileName);
            JSONObject sampleObject = (JSONObject) parser.parse(reader);
            longData = (Long) sampleObject.get(dataName);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return longData;
    }
}
