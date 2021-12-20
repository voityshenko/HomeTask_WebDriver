package tests;

import com.google.gson.Gson;
import model.webservices.User;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

public class ApacheHTTPClientTest {

    @Test
    public void checkStatusCode() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("https://jsonplaceholder.typicode.com/users");
        CloseableHttpResponse response = httpClient.execute(httpGet);

        int actualStatusCode = response.getStatusLine().getStatusCode();
        Assert.assertEquals(actualStatusCode, 200);
    }

    @Test
    public void checkResponseHeader() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("https://jsonplaceholder.typicode.com/users");
        CloseableHttpResponse response = httpClient.execute(httpGet);

        HttpEntity entity = response.getEntity();
        String actualContentTypeValue = entity.getContentType().getValue();
        Assert.assertEquals(actualContentTypeValue, "application/json; charset=utf-8");
    }

    @Test
    public void checkResponseBody() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("https://jsonplaceholder.typicode.com/users");
        CloseableHttpResponse response = httpClient.execute(httpGet);

        HttpEntity entity = response.getEntity();
        Reader reader = new InputStreamReader(entity.getContent(), StandardCharsets.UTF_8);
        Gson gson = new Gson();
        User[] users = gson.fromJson(reader, User[].class);
        Assert.assertEquals(users.length, 10);
    }
}
