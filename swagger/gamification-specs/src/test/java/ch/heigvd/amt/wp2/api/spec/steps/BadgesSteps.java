package ch.heigvd.amt.wp2.api.spec.steps;

import ch.heigvd.amt.wp2.ApiException;
import ch.heigvd.amt.wp2.ApiResponse;
import ch.heigvd.amt.wp2.api.BadgesApi;
import ch.heigvd.amt.wp2.api.dto.Badge;
import ch.heigvd.amt.wp2.api.dto.PointScale;
import ch.heigvd.amt.wp2.api.spec.helpers.Environment;
import com.squareup.okhttp.*;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BadgesSteps {

    private Environment environment;
    private BadgesApi badgesApi;
    private Badge badge;

    private ApiResponse lastApiResponse;
    private ApiException lastApiException;
    private boolean lastApiCallThrewException;
    private int lastStatusCode;

    private String apiKey = null;

    private String listBadges = null;

    private String badgejson = null;
    private String badgejsonPatched = null;


    public BadgesSteps(Environment environment) {
        this.environment = environment;
        this.badgesApi = environment.getBadgeApi();
    }

    @Given("^there is a Badges server$")
    public void there_is_a_Badges_server() throws Throwable {
        assertNotNull(badgesApi);
    }

    @Given("^I have authorization for Badge$")
    public void i_have_authorization_for_Badge() throws Throwable {
            apiKey = "test";
    }

    @Given("^I don't have authorization for Badge$")
    public void i_don_t_have_authorization_for_Badge() throws Throwable {
        apiKey = "";
    }

    @Given("^I have a badge payload with the name \"([^\"]*)\", description \"([^\"]*)\" and image \"([^\"]*)\"$")
    public void i_have_a_badge_payload_with_the_name_description_and_image(String arg1, String arg2, String arg3) throws Throwable {
        badge = new ch.heigvd.amt.wp2.api.dto.Badge();
        badge.setName(arg1);
        badge.setDescription(arg2);
        badge.setImage(arg3);
    }

    @When("^I POST it to the /badges endpoint$")
    public void i_POST_it_to_the_badges_endpoint() throws Throwable {
        // HTTP URL
        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme("http")
                .host("localhost")
                .addPathSegment("api")
                .addPathSegment("badges")
                .port(8080)
                .build();

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        String formBody = "{\"description\" : \"" +badge.getDescription()+"\"," +
                "\"image\" : \"" + badge.getImage()+ "\"," +
                "\"name\" : \"" + badge.getName()+ "\"}";

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

    @When("^I ask for a list of badges with a GET on the /badges endpoint$")
    public void i_ask_for_a_list_of_badges_with_a_GET_on_the_badges_endpoint() throws Throwable {

        // HTTP URL
        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme("http")
                .host("localhost")
                .addPathSegment("api")
                .addPathSegment("badges")
                .port(8080)
                .build();

        Request request = new Request.Builder()
                .url(httpUrl)
                .addHeader("apiKey",apiKey)
                .build();

        OkHttpClient client = new OkHttpClient();

        Response httpResponse = client.newCall(request).execute();

        badgejson = httpResponse.body().string();

        try {
            lastStatusCode = httpResponse.code();
        } catch (Exception e) {
            lastStatusCode = -1;
        }

    }

    @Then("^I receive the badges$")
    public void i_receive_the_badges() throws Throwable {
        assertNotNull(listBadges);
    }

    @When("^I ask for the badge with a GET on the /badges/\"([^\"]*)\" endpoint$")
    public void i_ask_for_the_badge_with_a_GET_on_the_badges_endpoint(String arg1) throws Throwable {

        // HTTP URL
        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme("http")
                .host("localhost")
                .addPathSegment("api")
                .addPathSegment("badges")
                .addQueryParameter("badgeName",arg1)
                .addPathSegment(arg1)
                .port(8080)
                .build();

        Request request = new Request.Builder()
                .url(httpUrl)
                .addHeader("apiKey",apiKey)
                .build();

        OkHttpClient client = new OkHttpClient();

        Response httpResponse = client.newCall(request).execute();

        badgejson =  httpResponse.body().string();

        try {
            lastStatusCode = httpResponse.code();
        } catch (Exception e) {
            lastStatusCode = -1;
        }

    }

    @Then("^I receive the badge$")
    public void i_receive_the_badge() throws Throwable {
        assertNotNull(badgejson);
    }



    @When("^I ask for the badge with a PATCH on the /badges/\"([^\"]*)\" endpoint$")
    public void i_ask_for_the_badge_with_a_PATCH_on_the_badges_endpoint(String arg1) throws Throwable {


        // HTTP URL
        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme("http")
                .host("localhost")
                .addPathSegment("api")
                .addPathSegment("badges")
                .addQueryParameter("badgeName",arg1)
                .addPathSegment(arg1)
                .port(8080)
                .build();

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        String formBody = "{\"description\" : \"" + badge.getDescription()+ "\"," +
                "\"image\" : \"" + badge.getImage()+ "\"";

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

        badgejsonPatched =  formBody;

        try {
            lastStatusCode = httpResponse.code();
        } catch (Exception e) {
            lastStatusCode = -1;
        }
    }

    @Then("^I see if the badge has been patched$")
    public void i_see_if_the_badge_has_been_patched() throws Throwable {

        assertNotNull(badgejson);
        assertNotNull(badgejsonPatched);
    }

    @Then("^I receive a (\\d+) status code for badge$")
    public void i_receive_a_status_code_for_badge(int arg1) throws Throwable {
        assertEquals(arg1, lastStatusCode);
    }
}