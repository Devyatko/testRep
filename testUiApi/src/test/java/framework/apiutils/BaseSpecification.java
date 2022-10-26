package framework.apiutils;

import framework.baseutils.ConfigManager;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class BaseSpecification {
    private final static String URL = ConfigManager.readerStr("url", "configdata.json");

    public static RequestSpecification getRequestSpec(){
        RequestSpecBuilder builder = new RequestSpecBuilder()
                .setUrlEncodingEnabled(false)
                .setBaseUri(URL);
        return builder.build();
    }
}
