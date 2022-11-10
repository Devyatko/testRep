package framework.apiutils;

import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

public class BaseResponse {
    public static int getStatusCode(Response res) {
        return res
                .then()
                .log().all()
                .extract().statusCode();
    }

    public static String getContentType(Response res) {
        return res
                .then()
                .extract().contentType();
    }

    public static String getString(Response res) {
        return res
                .then()
                .extract().asString();
    }

    public static <T> T getClass(Class<T> clazz, Response res) {
        return res
                .then()
                .extract().as(clazz);
    }

    public static <T> List getListOfObjectsFromJsonPath(Class<T> clazz, Response res, String path) {
        return res
                .then()
                .extract().jsonPath().getList(path, clazz);
    }

    public static Map getMapFromBody(Response res, String path) {
        return res
                .then()
                .extract().body().jsonPath().getMap(path);
    }

    public static List getListFromBody(Response res, String path) {
        return res
                .then()
                .extract().body().jsonPath().getList(path);
    }
}
