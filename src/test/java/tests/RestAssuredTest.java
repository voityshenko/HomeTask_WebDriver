package tests;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import model.webservices.User;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class RestAssuredTest {

    @BeforeTest
    public void initTest() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test
    public void checkStatusCode() {
        Response statusCodeResponse = RestAssured.when().get("/users").andReturn();
        Assert.assertTrue(statusCodeResponse.getStatusLine().contains("200 OK"));
    }

    @Test
    public void checkResponseHeader() {
        Response responseHeader = RestAssured.when().get("/users").andReturn();
        Assert.assertTrue(responseHeader.getHeaders().hasHeaderWithName("Content-Type"));
        Assert.assertEquals(responseHeader.getHeader("Content-Type"), "application/json; charset=utf-8");
    }

    @Test
    public void checkResponseBody() {
        Response responseBody = RestAssured.when().get("/users").andReturn();
        User[] users = responseBody.getBody().as(User[].class);
        Assert.assertEquals(users.length, 10);
    }
}
