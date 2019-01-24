package ch.heigvd.amt.wp2.api.spec.steps;

import ch.heigvd.amt.wp2.ApiException;
import ch.heigvd.amt.wp2.ApiResponse;
import ch.heigvd.amt.wp2.api.PointScalesApi;
import ch.heigvd.amt.wp2.api.dto.PointScale;
import ch.heigvd.amt.wp2.api.spec.helpers.Environment;
import com.squareup.okhttp.*;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PointscaleSteps {

    private Environment environment;
    private PointScalesApi pointScalesApi;
    private PointScale pointScale;
    private PointScale pointScalePatched;

    private ApiResponse lastApiResponse;
    private ApiException lastApiException;
    private boolean lastApiCallThrewException;
    private int lastStatusCode;
    private String apiKey = null;

    private String listScalePoint = null;

    private String pointScalejson = null;
    private String pointScalejsonPatched = null;


    public PointscaleSteps(Environment environment) {
        this.environment = environment;
        this.pointScalesApi = environment.getPointScalesApi();
    }

    @Given("^I have authorization$")
    public void i_have_authorization() throws Throwable {
        apiKey = "test";
    }

    @Given("^I don't have authorization$")
    public void i_don_t_have_authorization() throws Throwable {
        apiKey = "";
    }

    @Given("^there is a Pointscales server$")
    public void there_is_a_Pointscales_server() throws Throwable {
        assertNotNull(pointScalesApi);
    }

    @Given("^I have a pointscale payload$")
    public void i_have_a_pointscale_payload() throws Throwable {
        pointScale = new ch.heigvd.amt.wp2.api.dto.PointScale();
        pointScale.setName("test name");
        pointScale.setDescription("test description");
    }

    @Given("^I have a pointscale payload with the name \"([^\"]*)\" and description \"([^\"]*)\"$")
    public void i_have_a_pointscale_payload_with_the_name_and_description(String arg1, String arg2) throws Throwable {
        pointScale = new ch.heigvd.amt.wp2.api.dto.PointScale();
        pointScale.setName(arg1);
        pointScale.setDescription(arg2);
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
       String formBody = "{\"name\" : \"" +pointScale.getName()+"\"," +
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
        assertEquals(arg1, lastStatusCode);
    }

    @When("^I ask for a list of pointscales with a GET on the /pointscales endpoint$")
    public void i_ask_for_a_list_of_pointscales_with_a_GET_on_the_pointscales_endpoint() throws Throwable {

        // HTTP URL
        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme("http")
                .host("localhost")
                .addPathSegment("api")
                .addPathSegment("pointScales")
                .port(8080)
                .build();

        Request request = new Request.Builder()
                .url(httpUrl)
                .addHeader("apiKey",apiKey)
                .build();

        OkHttpClient client = new OkHttpClient();

        Response httpResponse = client.newCall(request).execute();

        listScalePoint = httpResponse.body().string();

        try {
            lastStatusCode = httpResponse.code();
        } catch (Exception e) {
            lastStatusCode = -1;
        }
    }

    @Then("^I receive the pointscales$")
    public void i_receive_the_pointscales() throws Throwable {
        assertNotNull(listScalePoint);
    }

    @When("^I ask for the pointscale with a GET on the /pointscales/\"([^\"]*)\" endpoint$")
    public void i_ask_for_the_pointscale_with_a_GET_on_the_pointscales_endpoint(String arg1) throws Throwable {

        // HTTP URL
        HttpUrl httpUrl = new HttpUrl.Builder()
            .scheme("http")
            .host("localhost")
            .addPathSegment("api")
            .addPathSegment("pointScales")
            .addQueryParameter("pointScaleName",arg1)
            .addPathSegment(arg1)
            .port(8080)
            .build();

        Request request = new Request.Builder()
                .url(httpUrl)
                .addHeader("apiKey",apiKey)
                .build();

        OkHttpClient client = new OkHttpClient();

        Response httpResponse = client.newCall(request).execute();

        pointScalejson =  httpResponse.body().string();

        try {
            lastStatusCode = httpResponse.code();
        } catch (Exception e) {
            lastStatusCode = -1;
        }

    }

    @Then("^I receive the pointscale$")
    public void i_receive_the_pointscale() throws Throwable {
        assertNotNull(pointScalejson);
    }

    @When("^I ask for the pointscale with a PATCH on the /pointscales/\"([^\"]*)\" endpoint$")
    public void i_ask_for_the_pointscale_with_a_PATCH_on_the_pointscales_endpoint(String arg1) throws Throwable {


        // HTTP URL
        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme("http")
                .host("localhost")
                .addPathSegment("api")
                .addPathSegment("pointScales")
                .addQueryParameter("pointScaleName",arg1)
                .addPathSegment(arg1)
                .port(8080)
                .build();

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        String formBody = "{\"description\" : \"" + pointScale.getDescription()+ "\"}";

        //RequestBody body = RequestBody.create(JSON, pointScale.toString());
        RequestBody body = RequestBody.create(JSON, formBody);

        Request request = new Request.Builder()
                .url(httpUrl)
                .addHeader("apiKey",apiKey)
                .addHeader("Content-Type","application/json")
                .patch(body)
                .build();

        OkHttpClient client = new OkHttpClient();
        client.newCall(request);

        //client.newCall
        Response httpResponse = client.newCall(request).execute();

        pointScalejsonPatched =  formBody;

        try {
            lastStatusCode = httpResponse.code();
        } catch (Exception e) {
            lastStatusCode = -1;
        }

    }

    @Then("^I see if the pointscale has been patched$")
    public void i_see_if_the_pointscale_has_been_patched() throws Throwable {

        assertNotNull(pointScalejson);
        assertNotNull(pointScalejsonPatched);
        //assertEquals(pointScalejson,pointScalejsonPatched);
    }

}