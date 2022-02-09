package tests.apiTests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class RestAssuredTest {

    private static final String USERS = "/users";

    @BeforeTest
    public void initTest() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test
    public void checkStatusCode() {
        Response statusCodeResponse = RestAssured.when().get(USERS);

        assertEquals(statusCodeResponse.getStatusCode(), 200);
    }

    @Test
    public void checkResponseHeader() {
        Response responseHeader = RestAssured.when().get(USERS);

        assertEquals(responseHeader.header("Content-Type"), "application/json; charset=utf-8");
    }

    @Test
    public void checkResponseBody() {
        Response responseBody = RestAssured.when().get(USERS);

        assertEquals(responseBody.as(Map[].class).length, 10);
    }
}
