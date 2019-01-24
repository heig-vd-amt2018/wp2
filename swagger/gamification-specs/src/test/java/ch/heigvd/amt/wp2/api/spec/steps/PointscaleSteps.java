package ch.heigvd.amt.wp2.api.spec.steps;

import ch.heigvd.amt.wp2.ApiException;
import ch.heigvd.amt.wp2.ApiResponse;
import ch.heigvd.amt.wp2.api.PointScalesApi;
import ch.heigvd.amt.wp2.api.spec.helpers.Environment;
import ch.heigvd.amt.wp2.api.dto.PointScale;
import com.squareup.okhttp.*;
import com.sun.deploy.net.HttpRequest;
import com.sun.deploy.net.HttpResponse;
import com.sun.net.httpserver.HttpHandler;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

//import org.springframework.http.HttpHeaders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PointscaleSteps {

    private Environment environment;
    private PointScalesApi pointScalesApi;
    private PointScale pointScale;

    private ApiResponse lastApiResponse;
    private ApiException lastApiException;
    private boolean lastApiCallThrewException;
    private int lastStatusCode;
    private String apiKey = null;

    public PointscaleSteps(Environment environment) {
        this.environment = environment;
        this.pointScalesApi = environment.getPointScalesApi();
    }

    @Given("^I have authorization$")
    public void i_have_authorization() throws Throwable {
        apiKey = "test";
    }

    @Given("^there is a Pointscales server$")
    public void there_is_a_Pointscales_server() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        assertNotNull(pointScalesApi);
    }

    @Given("^I have a pointscale payload$")
    public void i_have_a_pointscale_payload() throws Throwable {
        pointScale = new ch.heigvd.amt.wp2.api.dto.PointScale();
        pointScale.setDescription("test description");
        pointScale.setName("test name");
    }

    @When("^I POST it to the /pointscales endpoint$")
    public void i_POST_it_to_the_pointscales_endpoint() throws Throwable {

        // HTTP URL
        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme("http")
                .host("localhost")
                .addPathSegment("api")
                .addPathSegment("pointScales")
                .port(8080)
                .build();

       MediaType JSON = MediaType.parse("application/json; charset=utf-8");
       String formBody = "{\"name\" : \" " +pointScale.getName()+"\"," +
               "\"description\" : \"" + pointScale.getDescription()+ "\"}";

        //RequestBody body = RequestBody.create(JSON, pointScale.toString());
        RequestBody body = RequestBody.create(JSON, formBody);

        Request request = new Request.Builder()
                .url(httpUrl)
                .addHeader("apiKey",apiKey)
                .addHeader("Content-Type","application/json")
                .post(body)
                .build();

        OkHttpClient client = new OkHttpClient();
        client.newCall(request);

        //client.newCall
        Response httpResponse = client.newCall(request).execute();

        try {
            lastStatusCode = httpResponse.code();
        } catch (Exception e) {

            lastStatusCode = -1;
        }
    }

    @Then("^I receive a (\\d+) status code$")
    public void i_receive_a_status_code(int arg1) throws Throwable {
        assertEquals(201, lastStatusCode);
    }
}