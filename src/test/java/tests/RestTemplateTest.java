package tests;


import model.webservices.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RestTemplateTest {

    @Test
    public void checkStatusCode() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<User[]> response = restTemplate.getForEntity("https://jsonplaceholder.typicode.com/users", User[].class);
        int actualStatusCode = response.getStatusCode().value();
        Assert.assertEquals(actualStatusCode, 200);
    }

    @Test
    public void checkResponseHeader() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<User[]> response = restTemplate.getForEntity("https://jsonplaceholder.typicode.com/users", User[].class);
        String actualContentTypeValue = response.getHeaders().getContentType().toString();
        Assert.assertEquals(actualContentTypeValue, "application/json;charset=utf-8");
    }

    @Test
    public void checkResponseBody() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<User[]> response = restTemplate.getForEntity("https://jsonplaceholder.typicode.com/users", User[].class);
        User[] users = response.getBody();
        Assert.assertEquals(users.length, 10);
    }
}
