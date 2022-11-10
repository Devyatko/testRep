package framework.apiutils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BaseRequest {
    public static Response get(String basePath) {
        return given()
                .spec(BaseSpecification.getRequestSpec())
                .when()
                .contentType(ContentType.JSON)
                .get(basePath);
    }

    public static Response post(String basePath) {
        return given()
                .spec(BaseSpecification.getRequestSpec())
                .when()
                .post(basePath);
    }

    public static Response postJson(String basePath, Object obj) {
        return given()
                .spec(BaseSpecification.getRequestSpec())
                .contentType("application/json")
                .accept("application/json")
                .body(obj)
                .when()
                .post(basePath);
    }

    public static Response postFormData(String basePath, Object obj) {
        return given()
                .spec(BaseSpecification.getRequestSpec())
                .body(obj)
                .when().post(basePath);
    }
}
